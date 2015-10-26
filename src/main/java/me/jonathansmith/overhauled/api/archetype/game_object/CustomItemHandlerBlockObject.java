package me.jonathansmith.overhauled.api.archetype.game_object;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public abstract class CustomItemHandlerBlockObject extends BlockObject {

    private Class<? extends ItemBlock> itemClass;

    // TODO: Fix
    public CustomItemHandlerBlockObject(Material materialIn) {
        super(materialIn);
    }

    public Class<? extends ItemBlock> getItemClass() {
        return itemClass;
    }
}
