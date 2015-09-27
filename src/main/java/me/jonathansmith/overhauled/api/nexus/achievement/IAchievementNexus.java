package me.jonathansmith.overhauled.api.nexus.achievement;

import net.minecraft.entity.player.EntityPlayerMP;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 28/08/15.
 *
 * Contract employed by all achievement nexus implementations
 */
public interface IAchievementNexus extends INexus {

    /**
     * Use to register an achievement with the achievement registry
     * @param achievementProvider
     */
    void registerAchievementProvider(IAchievementProvider achievementProvider);

    /**
     * Use to trigger an achievement for a player
     *
     * @param achievementId
     * @param thePlayer
     */
    void triggerAchievement(String achievementId, EntityPlayerMP thePlayer);
}
