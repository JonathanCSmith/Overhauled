package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.List;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

/**
 * Created by Jonathan Charles Smith on 27/10/15.
 * <p/>
 * Interface to facilitate easy registration of state aware blocks / items
 */
public interface IStateAwareObject {

    List<IProperty> getProperties();

    boolean isValidState(IBlockState state);
}
