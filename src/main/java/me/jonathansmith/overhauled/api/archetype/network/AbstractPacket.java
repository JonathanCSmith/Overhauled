package me.jonathansmith.overhauled.api.archetype.network;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetHandlerPlayServer;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public abstract class AbstractPacket implements IMessage {

    public abstract boolean hasServerSideInteraction();

    public abstract void handleOnServer(NetHandlerPlayServer serverHandler);

    public abstract boolean hasClientSideInteraction();

    public abstract void handleOnClient(NetHandlerPlayClient clientHandler);
}
