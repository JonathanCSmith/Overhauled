package me.jonathansmith.overhauled.api.nexus.dimension;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Contract for the dimension registry. Dimensions will be automatically deployed
 */
public interface IDimensionNexus extends INexus {

    /**
     * @param dimensionProvider the provider to register with the nexus for automatic deployment
     */
    void registerDimensionProvider(IDimensionProvider dimensionProvider);

    /**
     * @param dimensionID the id to check
     * @return whether or not the provided dimension should behave under the overhauled behaviour framework
     */
    boolean doesDimensionEmployOverhauledBehaviours(int dimensionID);
}
