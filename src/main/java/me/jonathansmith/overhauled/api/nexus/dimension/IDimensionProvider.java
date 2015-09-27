package me.jonathansmith.overhauled.api.nexus.dimension;

import net.minecraft.world.WorldProvider;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 * <p/>
 * Allows for registration with the {@link me.jonathansmith.overhauled.core.nexus.dimension.DimensionNexus}
 * which in turn fully deploys dimensions automatically
 */
public interface IDimensionProvider {

    /**
     * @return whether or not this dimension is enabled
     */
    boolean isDimensionEnabled();

    /**
     * Set the engine assigned dimension ID. YOU SHOULD STORE THIS FOR RETRIEVAL THROUGH {@link IDimensionProvider#getAssignedDimensionID()}
     *
     * @param dimensionID the dimension id
     */
    void assignDimensionID(int dimensionID);

    /**
     * @return the engine assigned dimension ID. DO NOT CHANGE THIS ON YOUR OWN
     */
    int getAssignedDimensionID();

    /**
     * @return whether or not this world type uses a custom overworld
     */
    boolean doesDimensionEmployOverhauledBehaviours();

    /**
     * @return the class you wish to use as your world provider.
     */
    Class<? extends WorldProvider> getOverworldWorldProviderClass();

    /**
     * @return whether this world uses spawn chunks that stay in memory. If you replace the overworld, this should be true
     */
    boolean shouldSpawnStayInMemory();
}
