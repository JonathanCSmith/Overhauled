package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.ItemColored;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public class MultiMetaBlockItemObject extends ItemColored {

    private List<PropertyEnum> mappingProperties;

    // TODO: Fix
    public MultiMetaBlockItemObject(Block block, boolean hasSubtypes) {
        super(block, hasSubtypes);
    }

    public void setMappingProperties(List<PropertyEnum> properties) {
        this.mappingProperties = properties;
    }
}
