package me.jonathansmith.overhauled.api.utility.game_object;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

import net.minecraftforge.client.model.ModelLoader;

import me.jonathansmith.overhauled.api.archetype.game_object.IMetadataStateAwareModel;
import me.jonathansmith.overhauled.core.CoreProperties;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Helper class to facilitate registration of the copious rendering types
 */
public class RenderingRegistrationHelper {

    private static final int DEFAULT_METADATA = 0;

    /**
     * Register a state aware block to the overhauled resource path
     *
     * @param model the model to associate with the resource path and to generate states from
     */
    public void registerStateAwareModel(IMetadataStateAwareModel model) {
        this.registerStateAwareModel(model, CoreProperties.ID);
    }

    /**
     * Register a state aware block with the following properties
     *
     * @param model the model to associate with the resource path and to generate states from
     * @param resourceParent the mod resource path to use
     */
    public void registerStateAwareModel(IMetadataStateAwareModel model, String resourceParent) {
        HashMap<Integer, String> modelStates = model.getStateToMetadataMapping();
        Item item = model.getItem();
        for (Map.Entry<Integer, String> entry : modelStates.entrySet()) {
            this.registerModel(item, resourceParent, entry.getValue(), entry.getKey());
        }
    }

    /**
     * Register a block with a resource path
     *
     * @param block the block
     * @param state the block name including state - usually the base state as it assumes meta == 0
     */
    public void registerModel(Block block, String state) {
        Item item = Item.getItemFromBlock(block);
        this.registerModel(item, state);
    }

    /**
     * Register a block with a custom resource path
     *
     * @param block the block
     * @param resourceParent the resource path (mod name) where the resources will be found
     * @param state the block name including state - usually the base state as it assumes meta == 0
     */
    public void registerModel(Block block, String resourceParent, String state) {
        Item item = Item.getItemFromBlock(block);
        this.registerModel(item, resourceParent, state);
    }

    /**
     * Register a block with a specific metadata to a specific namespace
     *
     * @param block the block
     * @param state the blockname + state value associated with the metadata
     * @param meta the metadata value for this state
     */
    public void registerModel(Block block, String state, int meta) {
        Item item = Item.getItemFromBlock(block);
        this.registerModel(item, state, meta);
    }

    /**
     * Register a block with a specific metadata to a specific namespace in a specific resource location
     *
     * @param block the block
     * @param resourceParent the resource path (mod name) where the resources will be found
     * @param state the block name + state value associated with the metadata
     * @param metadata the metadata value for this state
     */
    public void registerModel(Block block, String resourceParent, String state, int metadata) {
        Item item = Item.getItemFromBlock(block);
        this.registerModel(item, resourceParent, state, metadata);
    }

    /**
     * Register an item with a resource path
     *
     * @param item the block
     * @param state the item name including state - usually the base state as it assumes meta == 0
     */
    public void registerModel(Item item, String state) {
        this.registerModel(item, CoreProperties.ID, state, DEFAULT_METADATA);
    }

    /**
     * Register an item with a custom resource path
     *
     * @param item the block
     * @param resourceParent the resource path (mod name) where the resources will be found
     * @param state the block name including state - usually the base state as it assumes meta == 0
     */
    public void registerModel(Item item, String resourceParent, String state) {
        this.registerModel(item, resourceParent, state, DEFAULT_METADATA);
    }

    /**
     * Register an item with a specific metadata to a specific namespace
     *
     * @param item the block
     * @param state the blockname + state value associated with the metadata
     * @param metadata the metadata value for this state
     */
    public void registerModel(Item item, String state, int metadata) {
        this.registerModel(item, CoreProperties.ID, state, metadata);
    }

    /**
     * Register an item with a specific metadata to a specific namespace in a specific resource location
     *
     * @param item the block
     * @param resourceParent the resource path (mod name) where the resources will be found
     * @param state the block name + state value associated with the metadata
     * @param metadata the metadata value for this state
     */
    public void registerModel(Item item, String resourceParent, String state, int metadata) {
        ModelBakery.addVariantName(item, resourceParent + ":" + state);
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(resourceParent + ":" + state, "inventory"));
    }
}
