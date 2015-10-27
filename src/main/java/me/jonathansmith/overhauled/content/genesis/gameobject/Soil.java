package me.jonathansmith.overhauled.content.genesis.gameobject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import me.jonathansmith.overhauled.api.archetype.game_object.MetaBlockObject;
import me.jonathansmith.overhauled.api.utility.game_object.ObjectRegistrationHelper;
import me.jonathansmith.overhauled.api.utility.game_object.RecipeRegistrationHelper;
import me.jonathansmith.overhauled.api.utility.game_object.RenderingRegistrationHelper;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Reimplementation of dirt with additional traits to interact with behaviours
 */
public class Soil extends MetaBlockObject {

    private static final String NAME = "soil";

    public Soil() {
        super(Material.ground, NAME, Arrays.asList(new IProperty[]{BlockDirt.VARIANT, BlockDirt.SNOWY}));

        this.setDefaultState(this.blockState.getBaseState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT).withProperty(BlockDirt.SNOWY, false));

        // TODO: Change
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    @Override
    public void fillMap() {
        for (BlockDirt.DirtType dirtType : BlockDirt.DirtType.values()) {
            this.meta_map.put(dirtType.getMetadata(), dirtType.getName());
        }
    }

    @Override
    public BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{BlockDirt.VARIANT, BlockDirt.SNOWY});
    }

    @Override
    public IBlockState getActualState(IBlockState currentState, IBlockAccess worldAccess, BlockPos position) {
        if (currentState.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL) {
            Block block = worldAccess.getBlockState(position.up()).getBlock();
            currentState = currentState.withProperty(BlockDirt.SNOWY, block == Blocks.snow || block == Blocks.snow_layer);
        }

        return currentState;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List subItemsList) {
        subItemsList.add(new ItemStack(this, 1, BlockDirt.DirtType.DIRT.getMetadata()));
        subItemsList.add(new ItemStack(this, 1, BlockDirt.DirtType.COARSE_DIRT.getMetadata()));
        subItemsList.add(new ItemStack(this, 1, BlockDirt.DirtType.PODZOL.getMetadata()));
    }

    @Override
    public int getDamageValue(World world, BlockPos position) {
        IBlockState iblockstate = world.getBlockState(position);
        return iblockstate.getBlock() != this ? 0 : ((BlockDirt.DirtType) iblockstate.getValue(BlockDirt.VARIANT)).getMetadata();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockDirt.DirtType) state.getValue(BlockDirt.VARIANT)).getMetadata();
    }

    @Override
    public int damageDropped(IBlockState state) {
        BlockDirt.DirtType dirttype = (BlockDirt.DirtType) state.getValue(BlockDirt.VARIANT);

        if (dirttype == BlockDirt.DirtType.PODZOL) {
            dirttype = BlockDirt.DirtType.DIRT;
        }

        return dirttype.getMetadata();
    }

    @Override
    public boolean isGameObjectEnabled() {
        return true;
    }

    @Override
    public void handleObjectRegistration(ObjectRegistrationHelper objectRegistrationHelper) {
        objectRegistrationHelper.registerComplexMetaBlock(this, this.state_independent_name);
    }

    @Override
    public void handleRecipeRegistration(RecipeRegistrationHelper recipeRegistrationHelper) {
    }

    @Override
    public void handleRenderingRegistration(RenderingRegistrationHelper renderingRegistrationHelper) {
        renderingRegistrationHelper.registerStateAwareModel(this);
    }

    @Override
    public HashMap<Integer, String> getStateToMetadataMapping() {
        return this.meta_map;
    }

    @Override
    public Item getItem() {
        return Item.getItemFromBlock(this);
    }
}
