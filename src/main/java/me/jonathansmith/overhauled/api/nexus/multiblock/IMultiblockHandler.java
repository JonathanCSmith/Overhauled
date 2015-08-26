package me.jonathansmith.overhauled.api.nexus.multiblock;

/**
 * Created by Jonathan Charles Smith on 26/08/15.
 *
 * Contract for an object that can form (?) a multiblock structure
 */
public interface IMultiblockHandler {

    Class<? extends IMultiblock> getMultiblockClass();
}
