package me.jonathansmith.overhauled.api.archetype.game_object;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public abstract class MetaBlockObject extends BlockObject {
    private final PropertyEnum property;

    // TODO: Fix
    public MetaBlockObject(PropertyEnum property) {
        super(Material.air);
        this.property = property;
    }

    public PropertyEnum getProperty() {
        return this.property;
    }
}
