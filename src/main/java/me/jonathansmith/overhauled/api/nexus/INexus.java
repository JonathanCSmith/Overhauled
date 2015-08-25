package me.jonathansmith.overhauled.api.nexus;

import me.jonathansmith.overhauled.api.IModule;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Contract for a deployable centralisation point. Allows grouping of common methods/techniques/maps and will be
 * subscribed to the overhauled specific event bus
 */
public interface INexus extends IModule {

    /**
     * @return the unique name for this registry
     */
    String getUniqueIdentifier();
}
