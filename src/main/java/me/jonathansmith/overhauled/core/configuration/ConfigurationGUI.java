package me.jonathansmith.overhauled.core.configuration;

import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.fml.client.config.GuiConfig;

import me.jonathansmith.overhauled.core.CoreProperties;
import me.jonathansmith.overhauled.core.nexus.configuration.ConfigurationNexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * Parent object for the overhauled configuration GUI.
 */
public class ConfigurationGUI extends GuiConfig {

    public ConfigurationGUI(GuiScreen parentScreen) {
        super(parentScreen,
                ConfigurationNexus.getInstance().buildConfigurationElementsList(),
                CoreProperties.ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationNexus.getInstance().getOverhauledConfigurationPath()));
    }
}
