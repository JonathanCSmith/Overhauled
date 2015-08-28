package me.jonathansmith.overhauled.core.nexus;

import java.util.LinkedHashMap;

import me.jonathansmith.overhauled.api.nexus.ICoreNexusProvider;
import me.jonathansmith.overhauled.api.nexus.INexus;
import me.jonathansmith.overhauled.api.nexus.achievement.IAchievementNexus;
import me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationNexus;
import me.jonathansmith.overhauled.api.nexus.trait.ITraitNexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * Implementation of the {@link ICoreNexusProvider} which aims to centralise access to nexus' for
 * external mods. Note, because anyone can reflect into this, we should not use the methods internally.
 */
public class CoreNexusProvider implements ICoreNexusProvider {

    private final LinkedHashMap<String, INexus> nexus_list = new LinkedHashMap<>();

    private IConfigurationNexus configurationNexus;
    private ITraitNexus         traitNexus;
    private IAchievementNexus   achievementNexus;

    @Override
    public IConfigurationNexus getConfigurationNexus() {
        return this.configurationNexus;
    }

    @Override
    public ITraitNexus getTraitNexus() {
        return this.traitNexus;
    }

    @Override
    public IAchievementNexus getAchievementNexus() {
        return this.achievementNexus;
    }

    @Override
    public INexus getNexusByName(String name) {
        return this.nexus_list.get(name);
    }

    public void registerNexus(INexus nexus) {
        String key = nexus.getUniqueIdentifier();
        switch (key) {
            case "Configuration Nexus":
                this.configurationNexus = (IConfigurationNexus) nexus;
                break;

            case "Trait Nexus":
                this.traitNexus = (ITraitNexus) nexus;
                break;

            case "AchievementNexus":
                this.achievementNexus = (IAchievementNexus) nexus;
                break;

        }

        this.nexus_list.put(key, nexus);
    }
}
