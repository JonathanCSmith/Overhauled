package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public class MetaBlockItemObject extends ItemColored {
    private PropertyEnum mappingProperty;

    // TODO: Fix
    public MetaBlockItemObject(Block block, boolean hasSubtypes) {
        super(block, hasSubtypes);
    }

    public void setMappingProperty(PropertyEnum mappingProperty) {
        this.mappingProperty = mappingProperty;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (this.mappingProperty == null) {
            return super.getUnlocalizedName();
        }

        IBlockState state = block.getStateFromMeta(stack.getMetadata());
        String name = state.getValue(this.mappingProperty).toString().toLowerCase(Locale.US);
        return super.getUnlocalizedName(stack) + "." + name;
    }
}
