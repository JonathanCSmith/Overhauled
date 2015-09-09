package me.jonathansmith.overhauled.archetype.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import me.jonathansmith.overhauled.api.nexus.network.IPacketHandler;
import me.jonathansmith.overhauled.api.nexus.network.ReceivingSide;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Abstract packet manager to help reduce boilerplate code
 */
public abstract class AbstractPacketManager<T extends IMessage> implements IPacketHandler<T> {

    private final Class<T> packet_class;
    private final ReceivingSide receiving_side;

    public AbstractPacketManager(Class<T> packetClass, ReceivingSide receivingSide) {
        this.packet_class = packetClass;
        this.receiving_side = receivingSide;
    }

    @Override
    public Class<T> getPacketClass() {
        return packet_class;
    }

    @Override
    public ReceivingSide getReceivingSide() {
        return this.receiving_side;
    }

    @Override
    public T onMessage(T message, MessageContext ctx) {
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT:
                return this.handleClientSideOnNetworkThread(message, Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft());

            case SERVER:
                return this.handleServerSideOnNetworkThread(message, ctx.getServerHandler().playerEntity, ctx.getServerHandler().playerEntity.getServerForPlayer(), ctx.getServerHandler().playerEntity.getServerForPlayer());
        }

        return null;
    }

    /**
     * @param message source message with the correct type
     * @param thePlayer the player associated with this message
     * @param theWorld the world associated with this message
     * @param worldThread the correct engine thread to submit jobs to from the network thread
     * @return the message when it has been successfully decoded on the client side
     */
    public T handleClientSideOnNetworkThread(final T message, final EntityPlayer thePlayer, final World theWorld, IThreadListener worldThread) {
        return message;
    }

    /**
     * @param message source message with the correct type
     * @param thePlayer the player associated with this message
     * @param theWorld the world associated with this message
     * @param worldThread the correct engine thread to submit jobs to from the network thread
     * @return the message when it has been successfully decoded on the server side
     */
    public T handleServerSideOnNetworkThread(final T message, final EntityPlayer thePlayer, final World theWorld, IThreadListener worldThread) {
        return message;
    }
}
