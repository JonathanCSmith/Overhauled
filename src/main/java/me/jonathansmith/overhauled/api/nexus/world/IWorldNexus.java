package me.jonathansmith.overhauled.api.nexus.world;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 08/09/15.
 *
 * Contract employed by the world registry. It will automatically deploy world and allow them to be displayed
 * in the create world directory. It also facilitates overworld replacement per world type
 */
public interface IWorldNexus extends INexus {

    /**
     * @param worldProvider the world provider to be deployed automatically
     */
    void registerWorldProvider(IWorldProvider worldProvider);
}
