package me.jonathansmith.overhauled.core.nexus.world;

import java.util.HashSet;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import me.jonathansmith.overhauled.api.delegate.event.CommonPostInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.world.IWorldNexus;
import me.jonathansmith.overhauled.api.nexus.world.IWorldProvider;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Implementation of the world nexus for automatic deployment of world content
 */
public class WorldNexus implements IWorldNexus {

    private static WorldNexus instance;

    public static WorldNexus getInstance() {
        if (WorldNexus.instance == null) {
            WorldNexus.instance = new WorldNexus();
        }

        return WorldNexus.instance;
    }

    private final HashSet<IWorldProvider> world_providers = new HashSet<>();

    private WorldNexus() {
    }

    @Override
    public String getUniqueIdentifier() {
        return "World Nexus";
    }

    @Override
    public void registerWorldProvider(IWorldProvider worldProvider) {
        this.world_providers.add(worldProvider);
    }

    // Highest priority is to ensure world redirection has a valid ide prior to loading the dimension registry
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void handlePostInitialisationEvent(CommonPostInitialisationEvent event) {
        for (IWorldProvider worldProvider : this.world_providers) {
            if (!worldProvider.isEnabled()) {
                continue;
            }

            worldProvider.build();
        }
    }

}
