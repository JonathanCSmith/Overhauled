package me.jonathansmith.overhauled.api.utility.universe;

import net.minecraft.util.BlockPos;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public class UniverseHelper {

    public static final BlockPos ORIGIN_BLOCK = new BlockPos(0, 0, 0);

    public static BlockPos getZeroBlock() {
        return ORIGIN_BLOCK;
    }
}
