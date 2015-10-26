package me.jonathansmith.overhauled.api.utility.game_object;

import java.util.Locale;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.fml.common.registry.GameRegistry;

import me.jonathansmith.overhauled.api.archetype.game_object.*;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Helper class for object registration - inspired (currently basically copied from) by https://github.com/SlimeKnights/TinkersConstruct/blob/rework/src/main/java/slimeknights/tconstruct/common/TinkerPulse.java
 */
public class ObjectRegistrationHelper {

    public <T extends MultiMetaBlockObject> void registerComplexMetaBlock(T block, String unlocalisedName) {
        this.registerBlock(block, MultiMetaBlockItemObject.class, unlocalisedName);
        ((MultiMetaBlockItemObject) Item.getItemFromBlock(block)).setMappingProperties(block.getProperties());
    }

    public <T extends MetaBlockObject> void registerSimpleMetaBlock(T block, String unlocalisedName) {
        this.registerBlock(block, MetaBlockItemObject.class, unlocalisedName);
        ((MetaBlockItemObject) Item.getItemFromBlock(block)).setMappingProperty(block.getProperty());
    }

    public <T extends CustomItemHandlerBlockObject> void registerCustomItemHandlerBlock(T block, String unlocalisedName) {
        this.registerBlock(block, block.getItemClass(), unlocalisedName);
    }

    protected <T extends BlockObject> void registerBlock(T block, Class<? extends ItemBlock> itemBlockClass, String unlocalisedName) {
        if (!unlocalisedName.equals(unlocalisedName.toLowerCase(Locale.US))) {
            throw new IllegalArgumentException("Error with unlocalised name: " + unlocalisedName + " as it should be lowercase!");
        }

        block.setUnlocalizedName(unlocalisedName);
        GameRegistry.registerBlock(block, itemBlockClass, unlocalisedName);

        // TODO: Automatic creative tabs
    }
}
