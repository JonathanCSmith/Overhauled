package me.jonathansmith.overhauled.core.nexus.dimension;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import me.jonathansmith.overhauled.api.delegate.event.CommonPostInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.dimension.IDimension;
import me.jonathansmith.overhauled.api.nexus.dimension.IDimensionNexus;
import me.jonathansmith.overhauled.api.nexus.dimension.IDimensionProvider;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 * <p/>
 * Dimension registry. Register dimensions here.
 */
public class DimensionNexus implements IDimensionNexus {

    private static DimensionNexus instance;

    public static DimensionNexus getInstance() {
        if (DimensionNexus.instance == null) {
            DimensionNexus.instance = new DimensionNexus();
        }

        return DimensionNexus.instance;
    }

    private final ArrayList<IDimensionProvider> dimension_providers = new ArrayList<>();
    private final HashMap<Integer, IDimension>  dimension__map      = new HashMap<>();

    private DimensionNexus() {
    }

    @Override
    public String getUniqueIdentifier() {
        return "Dimension Nexus";
    }

    @Override
    public void registerDimensionProvider(IDimensionProvider dimensionProvider) {
        this.dimension_providers.add(dimensionProvider);
    }

    @Override
    public boolean doesDimensionEmployOverhauledBehaviours(int dimensionID) {
        IDimension dimension = this.dimension__map.get(dimensionID);
        return dimension != null && dimension.doesDimensionEmployOverhauledBehaviours(dimensionID);
    }

    @SubscribeEvent
    public void handlePostInitialisationEvent(CommonPostInitialisationEvent event) {
        for (IDimensionProvider dimensionProvider : this.dimension_providers) {
            for (IDimension dimension : dimensionProvider.getDimensions()) {
                if (!dimension.isDimensionEnabled()) {
                    continue;
                }

                int id = DimensionManager.getNextFreeDimId();
                dimension.assignDimensionID(id);
                DimensionManager.registerProviderType(id, dimension.getOverworldWorldProviderClass(), dimension.shouldSpawnStayInMemory());
                DimensionManager.registerDimension(id, id);
                this.dimension__map.put(id, dimension);
            }
        }
    }
}
