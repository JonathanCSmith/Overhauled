package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.List;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

/**
 * Created by Jonathan Charles Smith on 27/10/15.
 * <p/>
 * Interface to facilitate easy registration of state aware blocks / items
 */
public interface IStateAwareObjectModelMesherHelper {

    /**
     * @return a list of properties. This is conventionally used by IRenderingRegistrationHelper to register metadata sensitive block renderers
     */
    List<IProperty> getProperties();

    /**
     * @param state the block state to check
     *
     * @return whether or not this block state should be mapped to a model
     */
    boolean isValidState(IBlockState state);

    /**
     * @param meta the metadata state to query
     *
     * @return a custom resource path (not domain) for the model representing this state
     *
     * NOTE: If you wish to change the domain for this model, please use that alternative method in IRenderingRegistrationHelper
     */
    String getCustomResourcePathForMetadata(int meta);
}
