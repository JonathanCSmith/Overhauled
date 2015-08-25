package me.jonathansmith.overhauled.api.nexus.configuration;

import java.io.File;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Configuration handler, registration for the loading of configuration files. Happens during pre-initialisation stage!
 */
public interface IConfigurationHandler {

    /**
     * @return the desired file name for this configuration
     */
    String getFileName();

    /**
     * @param modConfigurationDirectory target parent directory for the configuration to be stored in
     *
     * @return the configuration instance
     */
    Configuration getConfiguration(File modConfigurationDirectory);

    /**
     * Called when the configuration has been changed
     */
    void handleConfigurationChange();

    /**
     * @return list of config elements to be displayed on the configuration GUI
     */
    List<IConfigElement> getConfigurationElements();
}
