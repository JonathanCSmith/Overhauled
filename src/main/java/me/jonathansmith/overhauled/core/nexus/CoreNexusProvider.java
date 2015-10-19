package me.jonathansmith.overhauled.core.nexus;

import java.util.LinkedHashMap;

import me.jonathansmith.overhauled.api.nexus.ICoreNexusProvider;
import me.jonathansmith.overhauled.api.nexus.INexus;
import me.jonathansmith.overhauled.api.nexus.gameobject.IGameObjectNexus;
import me.jonathansmith.overhauled.api.nexus.trait.ITraitNexus;
import me.jonathansmith.overhauled.api.nexus.achievement.IAchievementNexus;
import me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationNexus;
import me.jonathansmith.overhauled.api.nexus.dimension.IDimensionNexus;
import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblockNexus;
import me.jonathansmith.overhauled.api.nexus.network.INetworkNexus;
import me.jonathansmith.overhauled.api.nexus.player.IPlayerNexus;
import me.jonathansmith.overhauled.api.nexus.world.IWorldNexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * Implementation of the {@link ICoreNexusProvider} which aims to centralise access to nexus' for
 * external mods. Note, because anyone can reflect into this, we should not use the methods internally.
 */
public class CoreNexusProvider implements ICoreNexusProvider {

    private final LinkedHashMap<String, INexus> nexus_list = new LinkedHashMap<>();

    private IConfigurationNexus configurationNexus;
    private INetworkNexus networkNexus;
    private IPlayerNexus playerNexus;
    private IGameObjectNexus gameObjectNexus;
    private IMultiblockNexus multiblockNexus;
    private ITraitNexus traitNexus;
    private IAchievementNexus achievementNexus;
    private IWorldNexus worldNexus;
    private IDimensionNexus dimensionNexus;

    @Override
    public IConfigurationNexus getConfigurationNexus() {
        return this.configurationNexus;
    }

    @Override
    public INetworkNexus getNetworkNexus() {
        return this.networkNexus;
    }

    @Override
    public IPlayerNexus getPlayerNexus() {
        return this.playerNexus;
    }

    @Override
    public IGameObjectNexus getGameObjectNexus() {
        return this.gameObjectNexus;
    }

    @Override
    public IMultiblockNexus getMultiblockNexus() {
        return this.multiblockNexus;
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
    public IWorldNexus getWorldNexus() {
        return this.worldNexus;
    }

    @Override
    public IDimensionNexus getDimensionNexus() {
        return this.dimensionNexus;
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

            case "Network Nexus":
                this.networkNexus = (INetworkNexus) nexus;
                break;

            case "Player Nexus":
                this.playerNexus = (IPlayerNexus) nexus;
                break;

            case "Game Object Nexus":
                this.gameObjectNexus = (IGameObjectNexus) nexus;

            case "Multiblock Nexus":
                this.multiblockNexus = (IMultiblockNexus) nexus;
                break;

            case "Trait Nexus":
                this.traitNexus = (ITraitNexus) nexus;
                break;

            case "Achievement Nexus":
                this.achievementNexus = (IAchievementNexus) nexus;
                break;

            case "World Nexus":
                this.worldNexus = (IWorldNexus) nexus;
                break;

            case "Dimension Nexus":
                this.dimensionNexus = (IDimensionNexus) nexus;
                break;
        }

        this.nexus_list.put(key, nexus);
    }
}
