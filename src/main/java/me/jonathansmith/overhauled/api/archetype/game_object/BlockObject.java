package me.jonathansmith.overhauled.api.archetype.game_object;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

import me.jonathansmith.overhauled.api.nexus.game_object.IGameObject;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Template block object for all overhauled blocks
 */
public abstract class BlockObject extends Block implements IGameObject {

    protected final String state_independent_name;

    public BlockObject(Material materialIn, String stateIndependentName) {
        super(materialIn);

        this.state_independent_name = stateIndependentName;
    }
}
