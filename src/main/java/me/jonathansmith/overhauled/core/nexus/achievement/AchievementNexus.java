package me.jonathansmith.overhauled.core.nexus.achievement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;

import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.google.common.collect.LinkedListMultimap;
import me.jonathansmith.overhauled.api.delegate.event.CommonPreInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.achievement.IAchievementNexus;
import me.jonathansmith.overhauled.api.nexus.achievement.IAchievementProvider;

/**
 * Created by Jonathan Charles Smith on 28/08/15.
 * <p/>
 * Overhauled implementation of the {@link IAchievementNexus}
 */
public class AchievementNexus implements IAchievementNexus {

    private static AchievementNexus instance;

    public static AchievementNexus getInstance() {
        if (AchievementNexus.instance == null) {
            AchievementNexus.instance = new AchievementNexus();
        }

        return AchievementNexus.instance;
    }

    private final ArrayList<IAchievementProvider>         achievement_providers = new ArrayList<>();
    private final LinkedHashMap<String, Achievement>      achievements          = new LinkedHashMap<>();
    private final LinkedListMultimap<String, Achievement> achievemenet_pages    = LinkedListMultimap.create();

    private AchievementNexus() {
    }

    @Override
    public String getUniqueIdentifier() {
        return "Achievement Nexus";
    }

    @Override
    public void registerAchievementProvider(IAchievementProvider achievementProvider) {
        this.achievement_providers.add(achievementProvider);
    }

    @Override
    public void triggerAchievement(String achievementId, EntityPlayerMP thePlayer) {
        Achievement achievement = this.achievements.get(achievementId);
        if (achievement != null) {
            thePlayer.addStat(achievement, 1);
        }
    }

    // Note it is important that this is a low priority event receiver to enable registration from other mods
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void handlePreInitialisationEvent(CommonPreInitialisationEvent event) {
        for (IAchievementProvider achievementProvider : this.achievement_providers) {
            Achievement achievement = achievementProvider.buildAchievement();
            this.achievements.put(achievement.statId, achievement);

            if (achievementProvider.requiresAchievementPage()) {
                this.achievemenet_pages.put(achievementProvider.getAchievementPageName(), achievement);
            }
        }

        for (String entry : this.achievemenet_pages.keySet()) {
            LinkedList<Achievement> achievementLinkedList = (LinkedList<Achievement>) this.achievemenet_pages.get(entry);
            AchievementPage.registerAchievementPage(new AchievementPage(entry, (Achievement[]) achievementLinkedList.toArray()));
        }
    }
}
