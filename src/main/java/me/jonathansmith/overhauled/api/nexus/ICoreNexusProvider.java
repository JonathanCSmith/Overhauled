package me.jonathansmith.overhauled.api.nexus;

import me.jonathansmith.overhauled.api.nexus.achievement.IAchievementNexus;
import me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationNexus;
import me.jonathansmith.overhauled.api.nexus.trait.ITraitNexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Provider for any nexus registered with the API as well as certain core nexus implementations
 */
public interface ICoreNexusProvider {

    /**
     * @return the singleton instance of IConfigurationNexus, allowing module specific configurations to be generated.
     */
    IConfigurationNexus getConfigurationNexus();

    /**
     * @return the current implementation of the trait nexus
     */
    ITraitNexus getTraitNexus();

    /**
     * @return the singleton instance of IAchievementNexus, allowing module specific achievements to be deployed automatically
     */
    IAchievementNexus getAchievementNexus();

    /**
     * @param name the unique string identifier for a nexus
     *
     * @return the nexus registered with the provided unique identifier
     */
    INexus getNexusByName(String name);
}
