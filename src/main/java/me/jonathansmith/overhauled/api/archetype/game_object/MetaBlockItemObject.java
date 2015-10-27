package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemColored;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Item wrapper for blocks that are metadata sensitive
 */
public class MetaBlockItemObject extends ItemColored {

    private List<IProperty> mappingProperties;

    public MetaBlockItemObject(Block block) {
        super(block, true);
    }

    public void setMappingProperties(List<IProperty> properties) {
        this.mappingProperties = properties;
    }
}
