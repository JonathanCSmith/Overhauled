package me.jonathansmith.overhauled.content.genesis.universe;

import java.util.LinkedList;

import net.minecraft.world.WorldType;

import me.jonathansmith.overhauled.api.OverhauledAPI;
import me.jonathansmith.overhauled.api.nexus.dimension.IDimension;
import me.jonathansmith.overhauled.api.nexus.dimension.IDimensionProvider;
import me.jonathansmith.overhauled.api.nexus.world.IWorldProvider;
import me.jonathansmith.overhauled.content.genesis.dimension.OverworldDimension;

/**
 * Created by Jonathan Charles Smith on 28/08/15.
 *
 * TODO
 */
public class UniverseProvider implements IWorldProvider, IDimensionProvider {

    private final LinkedList<IDimension> dimensions = new LinkedList<>();

    private UniverseType universeType;

    public void build() {
        OverhauledAPI.getNexusProvider().getWorldNexus().registerWorldProvider(this);
        OverhauledAPI.getNexusProvider().getDimensionNexus().registerDimensionProvider(this);
        this.dimensions.add(new OverworldDimension());
    }

    @Override
    public boolean isWorldEnabled() {
        return true;
    }

    @Override
    public WorldType getWorldType() {
        return this.universeType;
    }

    @Override
    public void buildWorld() {
        this.universeType = new UniverseType();
    }

    @Override
    public LinkedList<IDimension> getDimensions() {
        return this.dimensions;
    }
}
