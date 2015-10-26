package me.jonathansmith.overhauled.api.archetype.network;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import me.jonathansmith.overhauled.api.nexus.network.IPacketHandler;
import me.jonathansmith.overhauled.api.nexus.network.ReceivingSide;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 * <p/>
 * Abstract packet manager to help reduce boilerplate code
 */
public abstract class AbstractPacketManager<T extends AbstractPacket> implements IPacketHandler<T> {

    private final Class<T>      packet_class;
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
    public T onMessage(final T message, final MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            if (message.hasServerSideInteraction()) {
                MinecraftServer.getServer().addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        message.handleOnServer(ctx.getServerHandler());
                    }
                });
            }

            return this.getMessageReplyFromServer(message);
        }

        else {
            if (message.hasClientSideInteraction()) {
                Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        message.handleOnClient(ctx.getClientHandler());
                    }
                });
            }

            return this.getMessageReplyFromClient(message);
        }
    }

    /**
     * @param message source message with the correct type
     * @return the message when it has been successfully decoded on the client side
     */
    public T getMessageReplyFromClient(final T message) {
        return null;
    }

    /**
     * @param message source message with the correct type
     * @return the message when it has been successfully decoded on the server side
     */
    public T getMessageReplyFromServer(final T message) {
        return null;
    }
}
