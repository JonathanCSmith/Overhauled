package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public abstract class MultiMetaBlockObject extends BlockObject {
    private List<PropertyEnum> properties;

    // TODO: Fix
    public MultiMetaBlockObject(Material materialIn) {
        super(materialIn);
    }

    public List<PropertyEnum> getProperties() {
        return properties;
    }
}
