package me.jonathansmith.overhauled.api.archetype.game_object;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import me.jonathansmith.overhauled.api.nexus.game_object.IGameObject;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public abstract class BlockObject extends Block implements IGameObject {

    // TODO: Fix
    public BlockObject(Material materialIn) {
        super(materialIn);
    }
}
