package me.jonathansmith.overhauled.api.utility.game_object;

import me.jonathansmith.overhauled.api.archetype.game_object.CustomItemHandlerBlockObject;
import me.jonathansmith.overhauled.api.archetype.game_object.MetadataBlockObject;

/**
 * Created by Jonathan Charles Smith on 28/10/15.
 *
 * Helper for registering differing game object types
 */
public interface IObjectRegistrationHelper {

    /**
     * Register a metadata sensitive model using the following properties:
     *
     * @param block the block to associate a model with
     * @param unlocalisedName the name to associate with the model - also used to generate the resource path
     * @param <T> a block that extends MetadataBlockObject
     */
    <T extends MetadataBlockObject> void registerMetadataBlock(T block, String unlocalisedName);

    /**
     * Register a model with a custom itemblock class using the following properties:
     *
     * @param block the block to associate a model with
     * @param unlocalisedName the name to associate with the model - also used to generate the resource path
     * @param <T> a block that extends CustomItemHandlerBlockObject
     */
    <T extends CustomItemHandlerBlockObject> void registerCustomItemHandlerBlock(T block, String unlocalisedName);
}
