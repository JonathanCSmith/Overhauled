package me.jonathansmith.overhauled.api.utility.game_object;

import java.util.Locale;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.fml.common.registry.GameRegistry;

import me.jonathansmith.overhauled.api.archetype.game_object.*;

import me.jonathansmith.overhauled.core.CoreProperties;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Helper class for object registration - inspired (currently basically copied from) by
 * https://github.com/SlimeKnights/TinkersConstruct/blob/rework/src/main/java/slimeknights/tconstruct/common/TinkerPulse.java
 */
public class ObjectRegistrationHelper implements IObjectRegistrationHelper {

    @Override
    public <T extends MetadataBlockObject> void registerMetadataBlock(T block, String unlocalisedName) {
        this.registerBlock(block, MetadataBlockItemObject.class, unlocalisedName);
        ((MetadataBlockItemObject) Item.getItemFromBlock(block)).setMappingProperties(block.getProperties());
    }

    @Override
    public <T extends CustomItemHandlerBlockObject> void registerCustomItemHandlerBlock(T block, String unlocalisedName) {
        this.registerBlock(block, block.getItemClass(), unlocalisedName);
    }

    // Raw method to register blocks. Additional more comprehensive registration functionality will be added here later
    protected <T extends BlockObject> void registerBlock(T block, Class<? extends ItemBlock> itemBlockClass, String unlocalisedName) {
        if (!unlocalisedName.equals(unlocalisedName.toLowerCase(Locale.US))) {
            throw new IllegalArgumentException("Error with unlocalised name: " + unlocalisedName + " as it should be lowercase!");
        }

        block.setUnlocalizedName(CoreProperties.ID + "." + unlocalisedName);
        GameRegistry.registerBlock(block, itemBlockClass, unlocalisedName);

        // TODO: Automatic creative tabs
    }
}
