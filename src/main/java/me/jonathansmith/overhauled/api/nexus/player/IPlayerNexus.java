package me.jonathansmith.overhauled.api.nexus.player;

import net.minecraft.entity.player.EntityPlayer;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Contract for the player registry.
 */
public interface IPlayerNexus extends INexus {

    /**
     * @param extendedPlayerClass the extended player to register with the nexus for automatic deployment
     */
    void registerPlayerExtender(Class<? extends IExtendedPlayer> extendedPlayerClass);

    /**
     * @param thePlayer to find an extended player for
     * @param playerExtender the type of extended player to find
     * @return an instance of {@link IExtendedPlayer} corresponding to the above
     */
    IExtendedPlayer getExtendedPlayer(EntityPlayer thePlayer, Class<? extends IExtendedPlayer> playerExtender);

    /**
     * @param playerEventListener the event listener to register to the event bus - a listener can subscribe to all published player events
     */
    void registerPlayerEventListener(IPlayerEventListener playerEventListener);
}
