package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.HashMap;

import net.minecraft.item.Item;

/**
 * Created by Jonathan Charles Smith on 27/10/15.
 *
 * Interface to facilitate easy registration of state aware blocks / items
 */
public interface IMetadataStateAwareModel {

    /**
     * return a map linking all metadata states to their corresponding names in the resource paths
     */
    HashMap<Integer,String> getStateToMetadataMapping();

    /**
     * @return the item instance. If this model is traditionally a block, Item.getItemFromBlock can be used
     */
    Item getItem();
}
