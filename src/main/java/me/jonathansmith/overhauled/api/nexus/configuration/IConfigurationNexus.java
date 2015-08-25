package me.jonathansmith.overhauled.api.nexus.configuration;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Contract employed by the configuration registry. It will automatically deploy configurations and allow them to be displayed
 * in the Overhauled mod's configuration GUI
 */
public interface IConfigurationNexus extends INexus {

    /**
     * @return the core path where all registered overhauled configurations *SHOULD* be stored
     */
    String getOverhauledConfigurationPath();

    /**
     * @param handler the {@link me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationHandler} to register for deployment
     */
    void registerConfigurationHandler(IConfigurationHandler handler);
}
