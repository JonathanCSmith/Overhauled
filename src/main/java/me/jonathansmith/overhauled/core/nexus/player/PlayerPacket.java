package me.jonathansmith.overhauled.core.nexus.player;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import io.netty.buffer.ByteBuf;
import me.jonathansmith.overhauled.api.nexus.player.PlayerEventType;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Packet for syncing player action methods between the client and server. May become redundant
 */
public class PlayerPacket implements IMessage {

    private PlayerEventType payload;

    // Change dimension payload
    private int fromDim;
    private int toDim;

    public PlayerPacket() {
    }

    public PlayerPacket(PlayerEventType payload) {
        this.payload = payload;
    }

    public PlayerPacket(PlayerEventType payload, int fromDim, int toDim) {
        this(payload);
        this.fromDim = fromDim;
        this.toDim = toDim;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.payload = PlayerEventType.values()[buf.readInt()];

        if (this.payload == PlayerEventType.CHANGE_DIMENSION) {
            this.fromDim = buf.readInt();
            this.toDim = buf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.payload.ordinal());

        if (this.payload == PlayerEventType.CHANGE_DIMENSION) {
            buf.writeInt(this.fromDim);
            buf.writeInt(this.toDim);
        }
    }

    public PlayerEventType getPayload() {
        return this.payload;
    }

    public int getFromDim() {
        return this.fromDim;
    }

    public int getToDim() {
        return this.toDim;
    }
}
