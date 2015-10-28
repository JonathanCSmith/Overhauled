package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.List;

import net.minecraft.block.properties.IProperty;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Item wrapper for blocks that are metadata sensitive
 */
public class MetadataBlockItemObject extends ItemColored {

    private List<IProperty> mappingProperties;

    public MetadataBlockItemObject(MetadataBlockObject block) {
        super(block, true);
    }

    public void setMappingProperties(List<IProperty> properties) {
        this.mappingProperties = properties;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName();
    }
}
