package me.jonathansmith.overhauled.api.nexus.player;

import net.minecraft.entity.player.EntityPlayer;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Common parent class for all player events posted on the player event bus
 */
public abstract class PlayerRegistryEvent extends Event {

    private final PlayerEventType payload;
    private final Side         side;
    private final EntityPlayer player;

    public PlayerRegistryEvent(PlayerEventType payload, Side side, EntityPlayer player) {
        this.payload = payload;
        this.side = side;
        this.player = player;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    /**
     * Event fired when a player joins the game
     */
    public static class PlayerJoinEvent extends PlayerRegistryEvent {
        public PlayerJoinEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.JOIN, side, player);
        }
    }

    /**
     * Event fired when a player finishes joining the game
     */
    public static class PlayerJoinCompleteEvent extends PlayerRegistryEvent {
        public PlayerJoinCompleteEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.JOIN_COMPLETE, side, player);
        }
    }

    /**
     * Event fired when a player leaves the game
     */
    public static class PlayerLeaveEvent extends PlayerRegistryEvent {
        public PlayerLeaveEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.LEAVE, side, player);
        }
    }

    /**
     * Event fired when a player finishes leaving the game
     */
    public static class PlayerLeaveCompleteEvent extends PlayerRegistryEvent {
        public PlayerLeaveCompleteEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.LEAVE_COMPLETE, side, player);
        }
    }

    /**
     * Event fired when a player respawns
     */
    public static class PlayerRespawnEvent extends PlayerRegistryEvent {
        public PlayerRespawnEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.RESPAWN, side, player);
        }
    }

    /**
     * Event fired when a player finishes respawning
     */
    public static class PlayerRespawnCompleteEvent extends PlayerRegistryEvent {
        public PlayerRespawnCompleteEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.RESPAWN_COMPLETE, side, player);
        }
    }

    /**
     * Event fired when a player changes dimension
     */
    public static class PlayerChangeDimensionEvent extends PlayerRegistryEvent {

        private final int fromDim;
        private final int toDim;

        public PlayerChangeDimensionEvent(Side side, EntityPlayer player, int fromDim, int toDim) {
            super(side == Side.SERVER ? PlayerEventType.CHANGE_DIMENSION_COMPLETE : PlayerEventType.CHANGE_DIMENSION, side, player);

            this.fromDim = fromDim;
            this.toDim = toDim;
        }

        public int getFromDim() {
            return this.fromDim;
        }

        public int getToDim() {
            return this.toDim;
        }
    }

    /**
     * Event fired when the player tick begins
     */
    public static class PlayerTickStartEvent extends PlayerRegistryEvent {
        public PlayerTickStartEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.TICK_START, side, player);
        }
    }

    /**
     * Event fired when the player tick ends
     */
    public static class PlayerTickEndEvent extends PlayerRegistryEvent {
        public PlayerTickEndEvent(Side side, EntityPlayer player) {
            super(PlayerEventType.TICK_END, side, player);
        }
    }
}
