package me.jonathansmith.overhauled.archetype.gameobject.block;

import me.jonathansmith.overhauled.api.nexus.gameobject.IBlockWithVariants;
import me.jonathansmith.overhauled.api.nexus.gameobject.IGameObject;
import me.jonathansmith.overhauled.archetype.gameobject.item.StateAwareItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan Charles Smith on 09/09/15.
 *
 * TODO:
 */
public class StateAwareBlock extends Block implements IBlockWithVariants {

    private final Class<? extends StateAwareItem> item_class;
    private final HashMap<String, Integer> variant_mappings;

    public StateAwareBlock(Material materialIn, Class<? extends StateAwareItem> itemClass, HashMap<String, Integer> variantMappings) {
        super(materialIn);

        this.item_class = itemClass;
        this.variant_mappings = variantMappings;
    }

    @Override
    public StateAwareBlock getBlock() {
        return this;
    }

    @Override
    public Class<? extends StateAwareItem> getItemClass() {
        return this.item_class;
    }

    @Override
    public HashMap<String, Integer> getVariantMappings() {
        return this.variant_mappings;
    }
}
