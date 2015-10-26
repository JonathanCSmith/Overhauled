package me.jonathansmith.overhauled.content.genesis;

import me.jonathansmith.overhauled.api.content.IContent;
import me.jonathansmith.overhauled.api.delegate.IDelegate;
import me.jonathansmith.overhauled.content.genesis.gameobject.GameObjectProvider;
import me.jonathansmith.overhauled.content.genesis.universe.UniverseProvider;

/**
 * Created by Jonathan Charles Smith on 28/08/15.
 * <p/>
 * Content module responsible for setting up the environment for the overhauled mod
 * Likely to change many behaviours, kill all existing modules and generally create a more solid
 * foundation for world building
 */
public class GenesisContent implements IContent {

    private final UniverseProvider   universe_provider    = new UniverseProvider();
    private final GameObjectProvider game_object_provider = new GameObjectProvider();

    @Override
    public void buildForPreInitialisation(IDelegate delegate) {
        this.universe_provider.build();
        this.game_object_provider.build();
    }


}
