package me.jonathansmith.overhauled.api.nexus;

import me.jonathansmith.overhauled.api.nexus.trait.ITraitNexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Provider for any nexus registered with the API as well as certain core nexus implementations
 */
public interface ICoreNexusProvider {

    /**
     * @return the current implementation of the trait nexus
     */
    ITraitNexus getTraitNexus();

    /**
     * @param name the unique string identifier for a nexus
     *
     * @return the nexus registered with the provided unique identifier
     */
    INexus getNexusByName(String name);
}
