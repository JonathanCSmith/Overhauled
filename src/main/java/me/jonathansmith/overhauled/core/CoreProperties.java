package me.jonathansmith.overhauled.core;

import tv.twitch.Core;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Properties for the core infrastructure of the mod
 */
public class CoreProperties {

    public static final String  ID                      = "overhauled";
    public static final String  NAME                    = "Overhauled";
    public static final String  VERSION                 = "0.0.0";
    public static final String  CONFIGURATION_GUI_CLASS = "me.jonathansmith.overhauled.core.configuration.ConfigurationGUIFactory";
    public static final String  SERVER_DELEGATE_CLASS   = "me.jonathansmith.overhauled.core.delegate.ServerDelegate";
    public static final String  CLIENT_DELEGATE_CLASS   = "me.jonathansmith.overhauled.core.delegate.ClientDelegate";

    // ************************ RUNTIME ARGUMENTS **********************
    public static final boolean IS_DEBUG_MODE_FORCED    = true;
    public static final String NETWORK_NAME = CoreProperties.ID;
}
