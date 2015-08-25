package me.jonathansmith.overhauled.core.nexus.configuration;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import me.jonathansmith.overhauled.api.delegate.event.CommonPreInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.INexus;
import me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationHandler;
import me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationNexus;
import me.jonathansmith.overhauled.core.CoreProperties;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * Registry for handling loading and saving of configuration files
 */
public class ConfigurationNexus implements IConfigurationNexus {

    private static ConfigurationNexus instance;

    public static ConfigurationNexus getInstance() {
        if (ConfigurationNexus.instance == null) {
            ConfigurationNexus.instance = new ConfigurationNexus();
        }

        return ConfigurationNexus.instance;
    }

    private final HashMap<String, IConfigurationHandler> configuration_file_map = new HashMap<String, IConfigurationHandler>();

    private File overhauledConfigurationDirectory;

    @Override
    public String getUniqueIdentifier() {
        return "Configuration Registry";
    }

    @Override
    public String getOverhauledConfigurationPath() {
        if (this.overhauledConfigurationDirectory == null) {
            return "";
        }

        return this.overhauledConfigurationDirectory.getAbsolutePath();
    }

    @Override
    public void registerConfigurationHandler(IConfigurationHandler handler) {
        this.configuration_file_map.put(handler.getFileName(), handler);
    }

    public List<IConfigElement> buildConfigurationElementsList() {
        LinkedList<IConfigElement> elementLinkedList = new LinkedList<IConfigElement>();
        for (IConfigurationHandler handler : this.configuration_file_map.values()) {
            List<IConfigElement> elements = handler.getConfigurationElements();
            if (elements != null && !elements.isEmpty()) {
                elementLinkedList.addAll(elements);
            }
        }

        return elementLinkedList;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void handlePreInitialisationEvent(CommonPreInitialisationEvent event) {
        this.overhauledConfigurationDirectory = event.getPreInitialisationEvent().getModConfigurationDirectory();

        Configuration configuration;
        for (IConfigurationHandler handler : this.configuration_file_map.values()) {
            configuration = handler.getConfiguration(this.overhauledConfigurationDirectory);

            try {
                configuration.load();
                handler.handleConfigurationChange();
            }

            catch (Exception ex) {

            }

            finally {
                if (configuration.hasChanged()) {
                    configuration.save();
                }
            }
        }

        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void handleConfigurationChange(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(CoreProperties.ID)) {
            IConfigurationHandler handler = this.configuration_file_map.get(event.configID);
            if (handler != null) {
                handler.handleConfigurationChange();
            }
        }
    }
}