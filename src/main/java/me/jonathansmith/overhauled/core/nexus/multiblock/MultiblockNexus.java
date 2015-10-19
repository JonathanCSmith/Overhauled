package me.jonathansmith.overhauled.core.nexus.multiblock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblock;
import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblockDeployer;
import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblockHandler;
import me.jonathansmith.overhauled.api.nexus.multiblock.IMultiblockNexus;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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

    private final LinkedHashMap<IMultiblockDeployer, IMultiblockHandler> multiblock_deployers = new LinkedHashMap<>();

    private final ArrayList<IMultiblockHandler> multiblock_handler_list = new ArrayList<>();
    private final LinkedHashMap<String, IMultiblockHandler> block_to_handler_map = new LinkedHashMap<>();
    private final HashMap<World, WorldMultiblockManager> world_multiblock_manager_map = new HashMap<>();

    private MultiblockNexus() {}

    @Override
    public String getUniqueIdentifier() {
        return "Multiblock Nexus";
    }

    @Override
    public void registerMultiblockDeployer(IMultiblockHandler multiblockHandler) {
        // Register a deployer - handler mapping. We will check these during initialisation calls
        IMultiblockDeployer deployer = multiblockHandler.getMultiblockDeployer();
        this.multiblock_deployers.put(deployer, multiblockHandler);

        // For loading purposes we need to be able to trace a multiblock from a class
        this.multiblock_handler_list.add(multiblockHandler);
        this.block_to_handler_map.put(multiblockHandler.getMultiblockClass().getCanonicalName(), multiblockHandler);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        this.handleTick(event, Minecraft.getMinecraft().theWorld);
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.WorldTickEvent event) {
        this.handleTick(event, event.world);
    }

    @SubscribeEvent
    public void onChunkLoaded(ChunkEvent.Load loadEvent) {
        WorldMultiblockManager worldMultiblockManager = this.world_multiblock_manager_map.get(loadEvent.world);
        if (worldMultiblockManager != null) {
            worldMultiblockManager.handleChunkLoad(loadEvent.getChunk());
        }
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload unloadWorldEvent) {
        WorldMultiblockManager worldMultiblockManager = this.world_multiblock_manager_map.get(unloadWorldEvent.world);
        if (worldMultiblockManager != null) {
            worldMultiblockManager.handleWorldUnload();
            this.world_multiblock_manager_map.remove(unloadWorldEvent.world);
        }
    }

    public void validateConstruct(IMultiblock multiblock) {

    }

    private void handleTick(TickEvent tickEvent, World world) {
        if (tickEvent.phase == TickEvent.Phase.START) {
            WorldMultiblockManager worldManager = this.world_multiblock_manager_map.get(world);
            if (worldManager != null) {
                worldManager.processUpdates();
                worldManager.handleTickStart();
            }
        }
    }
}
