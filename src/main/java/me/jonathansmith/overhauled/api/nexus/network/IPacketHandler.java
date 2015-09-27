package me.jonathansmith.overhauled.api.nexus.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Class representing an object handling the proposed packet
 */
public interface IPacketHandler<T extends IMessage> extends IMessageHandler<T, IMessage> {

    /**
     * @return the packet handled by this class
     */
    Class<T> getPacketClass();

    /**
     * @return the side which is targetted to recieve this packet
     */
    ReceivingSide getReceivingSide();
}
