package me.jonathansmith.overhauled.api.nexus;

<<<<<<< HEAD
import me.jonathansmith.overhauled.api.nexus.trait.ITraitNexus;
=======
import me.jonathansmith.overhauled.api.nexus.achievement.IAchievementNexus;
import me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationNexus;
import me.jonathansmith.overhauled.api.nexus.dimension.IDimensionNexus;
import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblockNexus;
import me.jonathansmith.overhauled.api.nexus.network.INetworkNexus;
import me.jonathansmith.overhauled.api.nexus.player.IPlayerNexus;
import me.jonathansmith.overhauled.api.nexus.trait.ITraitNexus;
import me.jonathansmith.overhauled.api.nexus.world.IWorldNexus;
>>>>>>> origin/master

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Provider for any nexus registered with the API as well as certain core nexus implementations
 */
public interface ICoreNexusProvider {

    /**
<<<<<<< HEAD
=======
     * @return the singleton instance of IConfigurationNexus, allowing module specific configurations to be generated.
     */
    IConfigurationNexus getConfigurationNexus();

    /**
     * @return the singleton instance of INetworkNexus that allows packet and handler registration into the overhauled pipeline
     */
    INetworkNexus getNetworkNexus();

    /**
     * @return the singleton instance of IPlayerNexus which allows players to be extended and tracked
     */
    IPlayerNexus getPlayerNexus();

    /**
     * @return the singleton instance of IMultiblockNexus which allows multiple multiblock types to be registered and handled
     */
    IMultiblockNexus getMultiblockNexus();

    /**
>>>>>>> origin/master
     * @return the current implementation of the trait nexus
     */
    ITraitNexus getTraitNexus();

    /**
<<<<<<< HEAD
=======
     * @return the singleton instance of IAchievementNexus, allowing module specific achievements to be deployed automatically
     */
    IAchievementNexus getAchievementNexus();

    /**
     * @return the singleton instance of IWorldNexus, allowing registration of unique worlds that encapsulate a single or multiple dimensions
     */
    IWorldNexus getWorldNexus();

    /**
     * @return the singleton instance of IDimensionNexus, allowing registration of unique dimensions that can be deployed automatically
     */
    IDimensionNexus getDimensionNexus();

    /**
>>>>>>> origin/master
     * @param name the unique string identifier for a nexus
     *
     * @return the nexus registered with the provided unique identifier
     */
    INexus getNexusByName(String name);
}
