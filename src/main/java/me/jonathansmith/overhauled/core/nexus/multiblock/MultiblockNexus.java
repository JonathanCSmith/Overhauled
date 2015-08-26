package me.jonathansmith.overhauled.core.nexus.multiblock;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblockHandler;
import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblockNexus;

/**
 * Created by Jonathan Charles Smith on 26/08/15.
 * <p/>
 * Overhauled implementation of {@link IMultiblockNexus}
 */
public class MultiblockNexus implements IMultiblockNexus {

    private static MultiblockNexus instance;

    public static MultiblockNexus getInstance() {
        if (MultiblockNexus.instance == null) {
            MultiblockNexus.instance = new MultiblockNexus();
        }

        return MultiblockNexus.instance;
    }

    private final ArrayList<IMultiblockHandler>             multiblock_handler_list = new ArrayList<>();
    private final LinkedHashMap<String, IMultiblockHandler> block_to_handler_map    = new LinkedHashMap<>();


    private MultiblockNexus() {
    }

    @Override
    public String getUniqueIdentifier() {
        return "Multiblock Nexus";
    }

    @Override
    public void registerMultiblockDeployer(IMultiblockHandler multiblockHandler) {
        this.multiblock_handler_list.add(multiblockHandler);
        this.block_to_handler_map.put(multiblockHandler.getMultiblockClass().getCanonicalName(), multiblockHandler);
    }
}
