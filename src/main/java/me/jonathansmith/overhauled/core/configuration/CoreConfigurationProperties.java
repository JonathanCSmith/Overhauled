package me.jonathansmith.overhauled.core.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Configuration Properties
 */
public class CoreConfigurationProperties {

    // ***************************************** CONFIGURATION GENERAL ************************************************
    // Configuration Setup Properties
    public static final String CORE_CONFIGURATION_NAME = "Overhauled Configuration";
    public static final String CORE_CONFIGURATION_PATH = "/Overhauled/Overhauled.cfg";
    // ***************************************** CONFIGURATION GENERAL ************************************************

    // ******************************************** RUNTIME CATEGORY **************************************************
    public static final String RUNTIME_CATEGORY        = "Runtime:";
    public static final String RUNTIME_CATEGORY_HEADER = "General runtime properties of the overhauled mod.";

    public static final String DEBUG_RUNTIME_IDENTIFIER = "debug_mode";
    public static final String DEBUG_RUNTIME_COMMENT    = "Switch to true to enable debugging mode in your logs. Note, this is generally useless to a non-developer.";

//    public static final int    DEFAULT_SYNCHRONOUS_EXECUTOR_TICK_TIME = 25;
//    public static final String MAX_TICK_TIME_IDENTIFIER               = "max_synchronous_executor_tick_time";
//    public static final String MAX_TICK_TIME_COMMENT                  = "Maximum time in milliseconds allocated for synchronous execution";

    public static final List<String> RUNTIME_CATEGORY_ORDER = new ArrayList<String>(Arrays.asList(
            DEBUG_RUNTIME_IDENTIFIER
    ));
    // ******************************************** RUNTIME CATEGORY **************************************************

    // ******************************************** LEGACY CATEGORY END **********************************************
    public static final String VANILLA_OVERWORLD_PROVIDER_IDENTIFIER        = "Vanilla Overworld Provider ID";
    public static final int    LEGACY_DEFAULT_VANILLA_OVERWORLD_PROVIDER_ID = 2;
    public static final String VANILLA_OVERWORLD_PROVIDER_COMMENT           = "Overhauled moves the default overworld provider to this location. You can change it for cross-mod compatibility.";
    // ******************************************** LEGACY CATEGORY END **********************************************
}
