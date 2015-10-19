package me.jonathansmith.overhauled.content.genesis.dimension.overworld;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import me.jonathansmith.overhauled.api.utility.universe.UniverseHelper;
import me.jonathansmith.overhauled.content.genesis.GenesisProperties;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Universe's main overworld
 */
public class Overworld extends WorldProvider {

    @Override
    public String getDimensionName() {
        return GenesisProperties.OVERWORLD_NAME;
    }

    @Override
    public String getInternalNameSuffix() {
        return GenesisProperties.OVERWORLD_SUFFIX;
    }

    @Override
    protected void registerWorldChunkManager() {
        // TODO: Switch out after everything is good - current single biome
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.extremeHills, 0.5F);
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        // TODO: Switch out after everything is good - current flat chunk
        return new ChunkProviderFlat(this.worldObj, this.worldObj.getSeed(), false, "");
    }

    @Override
    public boolean canCoordinateBeSpawn(int xPos, int yPos) {
        return xPos == 0 && yPos == 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight() {
        return GenesisProperties.OVERWORLD_CLOUD_HEIGHT;
    }

    @Override
    public int getAverageGroundLevel() {
        return GenesisProperties.OVERWORLD_GROUND_LEVEL;
    }

    @SideOnly(Side.CLIENT)
    public boolean getWorldHasVoidParticles() {
        return GenesisProperties.OVERWORLD_HAS_VOID_PARTICLES;
    }

    @Override
    public String getWelcomeMessage() {
        return GenesisProperties.OVERWORLD_ENTERING_DIMENSION_MESSAGE;
    }

    @Override
    public String getDepartMessage() {
        return GenesisProperties.OVERWORLD_DEPARTING_DIMENSION_MESSAGE;
    }

    @Override
    public BlockPos getRandomizedSpawnPoint() {
        return this.getSpawnPoint();
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP playerMP) {
        return this.dimensionId;
    }

    @Override
    public BlockPos getSpawnPoint() {
        return new BlockPos(0, this.worldObj.getTopSolidOrLiquidBlock(UniverseHelper.getZeroBlock()).getY(), 0);
    }

    @Override
    public double getHorizon() {
        return GenesisProperties.OVERWORLD_DIMENSION_HORIZON_LEVEL;
    }
}
