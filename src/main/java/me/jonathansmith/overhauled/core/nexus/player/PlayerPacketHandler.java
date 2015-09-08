package me.jonathansmith.overhauled.core.nexus.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.relauncher.Side;

import me.jonathansmith.overhauled.api.nexus.network.ReceivingSide;
import me.jonathansmith.overhauled.api.nexus.player.PlayerEventType;
import me.jonathansmith.overhauled.api.nexus.player.PlayerRegistryEvent;
import me.jonathansmith.overhauled.archetype.network.AbstractPacketManager;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * {@link me.jonathansmith.overhauled.api.nexus.player.IPlayerNexus}'s personal packet for syncing client and server player information
 */
public class PlayerPacketHandler extends AbstractPacketManager<PlayerPacket> {

    private final EventBus player_event_bus;

    public PlayerPacketHandler(EventBus eventBus) {
        super(PlayerPacket.class, ReceivingSide.BOTH);

        this.player_event_bus = eventBus;
    }

    @Override
    public PlayerPacket handleClientSide(final PlayerPacket message, final EntityPlayer thePlayer, final World theWorld, IThreadListener worldThread) {
        switch (message.getPayload()) {
            case JOIN:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerJoinEvent(Side.CLIENT, thePlayer));
                    }
                });

                return new PlayerPacket(PlayerEventType.JOIN);

            case LEAVE:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerLeaveEvent(Side.CLIENT, thePlayer));
                    }
                });
                return new PlayerPacket(PlayerEventType.LEAVE);

            case RESPAWN:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerRespawnEvent(Side.CLIENT, thePlayer));

                    }
                });
                return new PlayerPacket(PlayerEventType.RESPAWN);

            case CHANGE_DIMENSION:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerChangeDimensionEvent(Side.CLIENT, thePlayer, message.getFromDim(), message.getToDim()));
                    }
                });
                return new PlayerPacket(PlayerEventType.CHANGE_DIMENSION);
        }

        return null;
    }

    @Override
    public PlayerPacket handleServerSide(final PlayerPacket message, final EntityPlayer thePlayer, final World theWorld, IThreadListener worldThread) {
        switch (message.getPayload()) {
            case JOIN:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerJoinCompleteEvent(Side.SERVER, thePlayer));
                    }
                });
                break;

            case LEAVE:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerLeaveCompleteEvent(Side.SERVER, thePlayer));
                    }
                });
                break;

            case RESPAWN:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerRespawnCompleteEvent(Side.SERVER, thePlayer));
                    }
                });
                break;

            case CHANGE_DIMENSION:
                worldThread.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        PlayerPacketHandler.this.player_event_bus.post(new PlayerRegistryEvent.PlayerChangeDimensionEvent(Side.SERVER, thePlayer, message.getFromDim(), message.getToDim()));
                    }
                });
                break;
        }

        return null;
    }
}
