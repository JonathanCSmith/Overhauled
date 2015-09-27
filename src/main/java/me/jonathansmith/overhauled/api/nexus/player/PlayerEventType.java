package me.jonathansmith.overhauled.api.nexus.player;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Types of information that can be propagated by player events
 */
public enum PlayerEventType {
    JOIN,
    JOIN_COMPLETE,
    LEAVE,
    LEAVE_COMPLETE,
    RESPAWN,
    RESPAWN_COMPLETE,
    CHANGE_DIMENSION,
    CHANGE_DIMENSION_COMPLETE,
    TICK_END,
    TICK_START
}
