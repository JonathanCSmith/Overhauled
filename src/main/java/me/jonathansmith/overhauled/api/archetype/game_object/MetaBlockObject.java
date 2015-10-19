package me.jonathansmith.overhauled.api.archetype.game_object;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import me.jonathansmith.overhauled.api.OverhauledAPI;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Template class for blocks that need to be aware of one or multiple states
 */
public abstract class MetaBlockObject extends BlockObject implements IMetadataStateAwareModel {

    protected final HashMap<Integer, String> meta_map = new HashMap<>();

    private final List<IProperty> properties;

    public MetaBlockObject(Material materialIn, String stateIndependentName, IProperty property) {
        this(materialIn, stateIndependentName, Arrays.asList(new IProperty[]{property}));
    }

    public MetaBlockObject(Material materialIn, String stateIndependentName, List<IProperty> properties) {
        super(materialIn, stateIndependentName);

        this.properties = properties;

        this.fillMap();
        OverhauledAPI.getNexusProvider().getGameObjectNexus().registerGameObject(this);
    }

    public List<IProperty> getProperties() {
        return properties;
    }

    protected abstract void fillMap();

    @Override
    public abstract BlockState createBlockState();

    @Override
    public abstract IBlockState getActualState(IBlockState currentState, IBlockAccess worldAccess, BlockPos position);

    @Override
    public abstract void getSubBlocks(Item item, CreativeTabs tab, List subItemsList);

    @Override
    public abstract int getDamageValue(World world, BlockPos position);

    @Override
    public abstract IBlockState getStateFromMeta(int meta);

    @Override
    public abstract int getMetaFromState(IBlockState state);

    @Override
    public abstract int damageDropped(IBlockState state);
}
