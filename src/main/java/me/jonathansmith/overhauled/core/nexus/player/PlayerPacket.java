package me.jonathansmith.overhauled.core.nexus.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetHandlerPlayServer;

import net.minecraftforge.fml.relauncher.Side;

import io.netty.buffer.ByteBuf;
import me.jonathansmith.overhauled.api.nexus.player.PlayerEventType;
import me.jonathansmith.overhauled.api.nexus.player.PlayerRegistryEvent;
import me.jonathansmith.overhauled.api.archetype.network.AbstractPacket;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Packet for syncing player action methods between the client and server. May become redundant
 */
public class PlayerPacket extends AbstractPacket {

    private PlayerEventType playerEventType;

    // Change dimension payload
    private int fromDim;
    private int toDim;

    public PlayerPacket() {
    }

    public PlayerPacket(PlayerEventType playerEventType) {
        this.playerEventType = playerEventType;
    }

    public PlayerPacket(PlayerEventType playerEventType, int fromDim, int toDim) {
        this(playerEventType);
        this.fromDim = fromDim;
        this.toDim = toDim;
    }

    @Override
    public boolean hasServerSideInteraction() {
        return true;
    }

    @Override
    public void handleOnServer(NetHandlerPlayServer serverHandler) {
        switch (this.playerEventType) {
            case JOIN:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerJoinCompleteEvent(Side.SERVER, serverHandler.playerEntity));
                break;

            case LEAVE:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerLeaveCompleteEvent(Side.SERVER, serverHandler.playerEntity));
                break;

            case RESPAWN:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerRespawnCompleteEvent(Side.SERVER, serverHandler.playerEntity));
                break;

            case CHANGE_DIMENSION:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerChangeDimensionEvent(Side.SERVER, serverHandler.playerEntity, this.fromDim, this.toDim));
                break;
        }
    }

    @Override
    public boolean hasClientSideInteraction() {
        return true;
    }

    @Override
    public void handleOnClient(NetHandlerPlayClient clientHandler) {
        switch (this.playerEventType) {
            case JOIN:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerJoinEvent(Side.CLIENT, Minecraft.getMinecraft().thePlayer));
                break;

            case LEAVE:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerLeaveEvent(Side.CLIENT, Minecraft.getMinecraft().thePlayer));
                break;

            case RESPAWN:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerRespawnEvent(Side.CLIENT, Minecraft.getMinecraft().thePlayer));
                break;

            case CHANGE_DIMENSION:
                PlayerNexus.getInstance().post(new PlayerRegistryEvent.PlayerChangeDimensionEvent(Side.CLIENT, Minecraft.getMinecraft().thePlayer, this.fromDim, this.toDim));
                break;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerEventType = PlayerEventType.values()[buf.readInt()];

        if (this.playerEventType == PlayerEventType.CHANGE_DIMENSION) {
            this.fromDim = buf.readInt();
            this.toDim = buf.readInt();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.playerEventType.ordinal());

        if (this.playerEventType == PlayerEventType.CHANGE_DIMENSION) {
            buf.writeInt(this.fromDim);
            buf.writeInt(this.toDim);
        }
    }

    public PlayerEventType getPlayerEventType() {
        return this.playerEventType;
    }

    public int getFromDim() {
        return this.fromDim;
    }

    public int getToDim() {
        return this.toDim;
    }
}
