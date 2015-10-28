package me.jonathansmith.overhauled.api.utility.game_object;

import me.jonathansmith.overhauled.api.archetype.game_object.BlockObject;
import me.jonathansmith.overhauled.api.archetype.game_object.IStateAwareObject;

/**
 * Created by Jonathan Charles Smith on 28/10/15.
 *
 * Helper to facilitate registration of the copious rendering types
 */
public interface IRenderingRegistrationHelper {

    /**
     * Mesh a block with multiple states to the state specific models
     *
     * @param block the block to register
     * @param <T> BlockObject implementing IStateAwareObject
     */
    <T extends BlockObject & IStateAwareObject> void meshMultiBlockStatesToModels(T block);

    /**
     * Mesh a block with multiple states to the state specific models at a custom resource domain
     *
     * @param block the block to register
     * @param resourceDomain the custom resource domain
     * @param <T> BlockObject implementing IStateAwareObject
     */
    <T extends BlockObject & IStateAwareObject> void meshMultiBlockStatesToModels(T block, String resourceDomain);
}
