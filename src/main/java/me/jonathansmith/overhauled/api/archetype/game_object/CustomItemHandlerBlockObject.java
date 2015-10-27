package me.jonathansmith.overhauled.api.archetype.game_object;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Template for blocks that wish to use custom itemblock classes
 */
public abstract class CustomItemHandlerBlockObject extends BlockObject {

    private Class<? extends ItemBlock> itemClass;

    public CustomItemHandlerBlockObject(Material materialIn, String stateIndependentName) {
        super(materialIn, stateIndependentName);
    }

    public Class<? extends ItemBlock> getItemClass() {
        return itemClass;
    }
}
