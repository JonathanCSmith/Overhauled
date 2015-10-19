package me.jonathansmith.overhauled.content.genesis.universe;

import net.minecraft.world.WorldType;

import me.jonathansmith.overhauled.content.genesis.GenesisProperties;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Universe world type - contains all of the universe dimensions
 */
public class UniverseType extends WorldType {

    public UniverseType() {
        super(GenesisProperties.UNIVERSE_TYPE_IDENTIFIER);
    }
}
