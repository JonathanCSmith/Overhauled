package me.jonathansmith.overhauled.api.nexus.gameobject;

import me.jonathansmith.overhauled.archetype.gameobject.block.StateAwareBlock;
import me.jonathansmith.overhauled.archetype.gameobject.item.StateAwareItem;

import java.util.HashMap;

/**
 * Created by Jon on 27/09/2015.
 */
public interface IBlockWithVariants extends IGameObject {

    StateAwareBlock getBlock();

    Class<? extends StateAwareItem> getItemClass();

    HashMap<String, Integer> getVariantMappings();
}
