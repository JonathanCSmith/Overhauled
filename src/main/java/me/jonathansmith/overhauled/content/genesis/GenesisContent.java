package me.jonathansmith.overhauled.content.genesis;

import me.jonathansmith.overhauled.api.content.IContent;
import me.jonathansmith.overhauled.api.delegate.IDelegate;
import me.jonathansmith.overhauled.content.genesis.gameobject.GravelObjectProvider;
import me.jonathansmith.overhauled.content.genesis.universe.UniverseProvider;

/**
 * Created by Jonathan Charles Smith on 28/08/15.
 *
 * Content module responsible for setting up the environment for the overhauled mod
 * Likely to change many behaviours, kill all existing modules and generally create a more solid
 * foundation for world building
 */
public class GenesisContent implements IContent {

    private final UniverseProvider     universe_provider = new UniverseProvider();
    private final GravelObjectProvider gravel_provider   = new GravelObjectProvider();

    @Override
    public void buildForPreInitialisation(IDelegate delegate) {
        // Core universe changes
        this.universe_provider.build();


        this.gravel_provider.build();
    }
}
