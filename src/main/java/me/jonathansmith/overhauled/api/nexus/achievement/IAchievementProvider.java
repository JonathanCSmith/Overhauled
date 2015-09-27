package me.jonathansmith.overhauled.api.nexus.achievement;

import net.minecraft.stats.Achievement;

/**
 * Created by Jonathan Charles Smith on 28/08/15.
 *
 * Object able to provide an achievement to an achievement nexus
 */
public interface IAchievementProvider {

    /**
     * @return your constructed achievement.
     *
     * Note: the first parameter used to construct tha achievements can then be
     * used to reference it within the registry
     */
    Achievement buildAchievement();

    /**
     * @return whether or not this achievement is to be displayed on a custom achievement page
     */
    boolean requiresAchievementPage();

    /**
     * @return the name of this achievement page. Only one page per name is used so it is safe to use a common name
     */
    String getAchievementPageName();
}
