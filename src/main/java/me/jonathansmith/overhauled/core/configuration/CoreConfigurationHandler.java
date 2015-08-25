package me.jonathansmith.overhauled.core.configuration;

import java.io.File;
import java.util.List;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;

import me.jonathansmith.overhauled.api.content.IContent;
import me.jonathansmith.overhauled.api.delegate.IDelegate;
import me.jonathansmith.overhauled.api.nexus.configuration.IConfigurationHandler;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * Configuration manager for the overhauled mod.
 */
public class CoreConfigurationHandler implements IContent, IConfigurationHandler {

    private static CoreConfigurationHandler instance;

    public static CoreConfigurationHandler getInstance() {
        if (CoreConfigurationHandler.instance == null) {
            CoreConfigurationHandler.instance = new CoreConfigurationHandler();
        }

        return CoreConfigurationHandler.instance;
    }

    private boolean isDebugMode                = false;
    private int     vanillaOverworldProviderID = CoreConfigurationProperties.LEGACY_DEFAULT_VANILLA_OVERWORLD_PROVIDER_ID;

    private Configuration configuration;

    @Override
    public void buildForPreInitialisation(IDelegate delegate) {

    }

    @Override
    public String getFileName() {
        return CoreConfigurationProperties.CORE_CONFIGURATION_NAME;
    }

    @Override
    public Configuration getConfiguration(File modConfigurationDirectory) {
        if (this.configuration == null) {
            this.configuration = new Configuration(new File(modConfigurationDirectory.getAbsolutePath(), CoreConfigurationProperties.CORE_CONFIGURATION_PATH), true);
        }

        return this.configuration;
    }

    @Override
    public void handleConfigurationChange() {
        this.synchroniseRuntimeCategory();

        if (this.configuration.hasChanged()) {
            this.configuration.save();
        }
    }

    @Override
    public List<IConfigElement> getConfigurationElements() {
        return new ConfigElement(this.configuration.getCategory(CoreConfigurationProperties.RUNTIME_CATEGORY)).getChildElements();
    }

    public boolean isCoreInDebugMode() {
        return this.isDebugMode;
    }

    public int getVanillaOverworldProviderID() {
        return this.vanillaOverworldProviderID;
    }

    private void synchroniseRuntimeCategory() {
        this.configuration.setCategoryComment(CoreConfigurationProperties.RUNTIME_CATEGORY, CoreConfigurationProperties.RUNTIME_CATEGORY_HEADER);
        this.configuration.setCategoryPropertyOrder(CoreConfigurationProperties.RUNTIME_CATEGORY, CoreConfigurationProperties.RUNTIME_CATEGORY_ORDER);

        // Debug runtime
        Property debugProperty = this.configuration.get(CoreConfigurationProperties.RUNTIME_CATEGORY, CoreConfigurationProperties.DEBUG_RUNTIME_IDENTIFIER, false, CoreConfigurationProperties.DEBUG_RUNTIME_COMMENT);
        debugProperty = debugProperty.setRequiresMcRestart(true);
        debugProperty = debugProperty.setRequiresWorldRestart(true);
        this.isDebugMode = debugProperty.getBoolean();

//        // Tick based execution
//        Property tickTimeMax = this.configuration.get(ConfigurationProperties.RUNTIME_CATEGORY, ConfigurationProperties.MAX_TICK_TIME_IDENTIFIER, ConfigurationProperties.DEFAULT_SYNCHRONOUS_EXECUTOR_TICK_TIME, ConfigurationProperties.MAX_TICK_TIME_COMMENT);
//        tickTimeMax = tickTimeMax.setRequiresMcRestart(false);
//        tickTimeMax = tickTimeMax.setRequiresWorldRestart(false);
//        tickTimeMax = tickTimeMax.setMinValue(5);
//        tickTimeMax = tickTimeMax.setMaxValue(50);
//        this.maxSynchronousExecutorTickTime = tickTimeMax.getInt();

        // Overworld provider - Moved to legacy
//        Property overworldProviderID = this.configuration.get(CoreConfigurationProperties.RUNTIME_CATEGORY, CoreConfigurationProperties.VANILLA_OVERWORLD_PROVIDER_IDENTIFIER, CoreConfigurationProperties.LEGACY_DEFAULT_VANILLA_OVERWORLD_PROVIDER_ID, CoreConfigurationProperties.VANILLA_OVERWORLD_PROVIDER_COMMENT);
//        overworldProviderID = overworldProviderID.setRequiresMcRestart(true);
//        overworldProviderID = overworldProviderID.setRequiresWorldRestart(true);
//        overworldProviderID = overworldProviderID.setMinValue(2);
//        overworldProviderID = overworldProviderID.setMaxValue(99);
//        this.vanillaOverworldProviderID = overworldProviderID.getInt();
    }
}
