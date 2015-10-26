package me.jonathansmith.overhauled.api.nexus.world;

import net.minecraft.world.WorldType;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 * <p/>
 * Contract for objects that wish to be registered as world providers for the WorldRegistry
 */
public interface IWorldProvider {

    /**
     * @return whether or not this world provider is enabled (allows dynamic world providers)
     */
    boolean isWorldEnabled();

    /**
     * @return the world type associated with this provided
     */
    WorldType getWorldType();

    /**
     * Hook to schedule building of the world provider
     */
    void buildWorld();
}
