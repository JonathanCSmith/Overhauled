package me.jonathansmith.overhauled.core.nexus.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import me.jonathansmith.overhauled.api.delegate.event.CommonInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.network.INetworkNexus;
import me.jonathansmith.overhauled.api.nexus.network.IPacketHandler;
import me.jonathansmith.overhauled.core.CoreProperties;
import me.jonathansmith.overhauled.core.log.LogHandler;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Implementation of the network nexus that handles all packet setup. Setup is performed at the end of the
 * initialisation stage so packets should be registered before this time
 */
public class NetworkNexus implements INetworkNexus {

    private static NetworkNexus instance;

    public static NetworkNexus getInstance() {
        if (NetworkNexus.instance == null) {
            NetworkNexus.instance = new NetworkNexus();
        }

        return NetworkNexus.instance;
    }

    private final SimpleNetworkWrapper       network_wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(CoreProperties.NETWORK_NAME);
    private final LinkedList<IPacketHandler> packet_handlers = new LinkedList<>();

    private NetworkNexus() {}

    @Override
    public String getUniqueIdentifier() {
        return "Network Nexus";
    }


    @Override
    public void registerPacket(IPacketHandler packetHandler) {
        this.packet_handlers.add(packetHandler);
    }

    @Override
    public void sendToServer(IMessage message) {
        if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT) {
            LogHandler.getInstance().error("Attempted to send: " + message.getClass().getCanonicalName() + " from the server to the server. Message was not sent.");
            return;
        }

        this.network_wrapper.sendToServer(message);
    }

    @Override
    public void sendToAll(IMessage message) {
        if (FMLCommonHandler.instance().getEffectiveSide() != Side.SERVER) {
            LogHandler.getInstance().error("Attempted to send: " + message.getClass().getCanonicalName() + " from the client to other clients. Message was not sent.");
            return;
        }

        this.network_wrapper.sendToAll(message);
    }

    @Override
    public void sendToAllAround(IMessage message, net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint targetPoint) {
        if (FMLCommonHandler.instance().getEffectiveSide() != Side.SERVER) {
            LogHandler.getInstance().error("Attempted to send: " + message.getClass().getCanonicalName() + " from the client to other clients. Message was not sent.");
            return;
        }

        this.network_wrapper.sendToAllAround(message, targetPoint);
    }

    @Override
    public void sendToDimension(IMessage message, int dimensionID) {
        if (FMLCommonHandler.instance().getEffectiveSide() != Side.SERVER) {
            LogHandler.getInstance().error("Attempted to send: " + message.getClass().getCanonicalName() + " from the client to other clients. Message was not sent.");
            return;
        }

        this.network_wrapper.sendToDimension(message, dimensionID);
    }

    @Override
    public void sendTo(IMessage message, EntityPlayerMP entityPlayer) {
        this.network_wrapper.sendTo(message, entityPlayer);
    }

    @Override
    @Deprecated
    public void submitTaskFromNetworkThreadToMainThread(Runnable runnable, MessageContext ctx) {
        switch (ctx.side) {
            case CLIENT:
                IThreadListener threadListener = (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
                threadListener.addScheduledTask(runnable);
                break;

            case SERVER:
                IThreadListener threadListenerAlt = Minecraft.getMinecraft();
                threadListenerAlt.addScheduledTask(runnable);
                break;
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void handleInitialisation(CommonInitialisationEvent event) {
        Collections.sort(this.packet_handlers, new Comparator<IPacketHandler>() {
            @Override
            public int compare(IPacketHandler p1, IPacketHandler p2) {
                int com = String.CASE_INSENSITIVE_ORDER.compare(p1.getClass().getCanonicalName(), p2.getClass().getCanonicalName());
                if (com == 0) {
                    com = p1.getClass().getCanonicalName().compareTo(p2.getClass().getCanonicalName());
                }

                return com;
            }
        });

        int id = 0;
        for (IPacketHandler packetManager : this.packet_handlers) {
            switch (packetManager.getReceivingSide()) {
                case BOTH:
                    this.network_wrapper.registerMessage(packetManager, packetManager.getPacketClass(), id++, Side.CLIENT);
                    this.network_wrapper.registerMessage(packetManager, packetManager.getPacketClass(), id++, Side.SERVER);
                    break;

                case CLIENT:
                    this.network_wrapper.registerMessage(packetManager, packetManager.getPacketClass(), id++, Side.CLIENT);
                    break;

                case SERVER:
                    this.network_wrapper.registerMessage(packetManager, packetManager.getPacketClass(), id++, Side.SERVER);
                    break;
            }
        }
    }
}
