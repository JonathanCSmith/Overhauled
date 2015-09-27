package me.jonathansmith.overhauled.api.nexus.network;

import net.minecraft.entity.player.EntityPlayerMP;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Core contract for the network registry. Provides a method to register packets and their associated properties for
 * automatic deployment
 */
public interface INetworkNexus extends INexus {

    /**
     * @param packetHandler the packet handler to register for deployment
     */
    void registerPacket(IPacketHandler packetHandler);

    /**
     * @param message the message to send to the server
     */
    void sendToServer(IMessage message);

    /**
     * @param message the message to send to all clients
     */
    void sendToAll(IMessage message);

    /**
     * @param message the message to send
     * @param targetPoint the point around which to send
     */
    void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint targetPoint);

    /**
     * @param message the message to send
     * @param dimensionID the dimension in which clients must be present in order to receive the message
     */
    void sendToDimension(IMessage message, int dimensionID);

    /**
     * @param message the message to send
     * @param entityPlayer the client to receive the message
     */
    void sendTo(IMessage message, EntityPlayerMP entityPlayer);

    /**
     * @param runnable the runnable to be called on the main engine thread (i.e. not the network thread)
     * @param ctx the current context of the message on the network thread
     */
    void submitTaskFromNetworkThreadToMainThread(Runnable runnable, MessageContext ctx);
}
