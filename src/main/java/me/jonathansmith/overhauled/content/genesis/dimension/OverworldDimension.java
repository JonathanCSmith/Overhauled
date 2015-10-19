package me.jonathansmith.overhauled.content.genesis.dimension;

import net.minecraft.world.WorldProvider;

import me.jonathansmith.overhauled.api.nexus.dimension.IDimension;
import me.jonathansmith.overhauled.content.genesis.dimension.overworld.Overworld;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Overworld replacer for the overhauled mod
 */
public class OverworldDimension implements IDimension {

    private final Class<? extends WorldProvider> overworldProviderClass = Overworld.class;

    private int dimensionIdentifier;

    @Override
    public boolean isDimensionEnabled() {
        return true;
    }

    @Override
    public void assignDimensionID(int dimensionIdentifier) {
        this.dimensionIdentifier = dimensionIdentifier;
    }

    @Override
    public int getAssignedDimensionID() {
        return this.dimensionIdentifier;
    }

    @Override
    public boolean doesDimensionEmployOverhauledBehaviours(int dimensionID) {
        return true;
    }

    @Override
    public Class<? extends WorldProvider> getOverworldWorldProviderClass() {
        return this.overworldProviderClass;
    }

    @Override
    public boolean shouldSpawnStayInMemory() {
        return true; // TODO: Revist, if implemented properly we may be able to drop this
    }
}
