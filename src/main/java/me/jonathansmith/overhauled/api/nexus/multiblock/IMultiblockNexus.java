package me.jonathansmith.overhauled.api.nexus.multiblock;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 26/08/15.
 *
 * Contract employed by the current implementation of the IMultiblockHandler nexus. Responsible for
 * allowing multiblock registration and providing event sinks for checking whether or not a structure
 * should form
 */
public interface IMultiblockNexus extends INexus {
    void registerMultiblockDeployer(IMultiblockHandler multiblock);
}
