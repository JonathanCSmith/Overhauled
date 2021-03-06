package me.jonathansmith.overhauled.core.log;

import org.apache.logging.log4j.Level;

import net.minecraftforge.fml.common.FMLLog;

import me.jonathansmith.overhauled.core.CoreProperties;
import me.jonathansmith.overhauled.core.configuration.CoreConfigurationHandler;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * General Logging Manager for Overhauled. Used to post information to the console/logs
 */
public class LogHandler {

    private static LogHandler instance;

    /**
     * @return an instance of LogHandler
     */
    public static LogHandler getInstance() {
        if (LogHandler.instance == null) {
            LogHandler.instance = new LogHandler();
        }

        return LogHandler.instance;
    }

    private void log(Level logLevel, Object obj) {
        FMLLog.log(CoreProperties.ID, logLevel, String.valueOf(obj));
    }

    /**
     * @param obj object to log at the fatal level see: {@link Level}
     */
    public void fatal(Object obj) {
        log(Level.FATAL, obj);
    }

    /**
     * @param obj object to log at the error level see: {@link Level}
     */
    public void error(Object obj) {
        log(Level.ERROR, obj);
    }

    /**
     * @param obj object to log at the warn level see: {@link Level}
     */
    public void warn(Object obj) {
        log(Level.WARN, obj);
    }

    /**
     * @param obj object to log at the info level see: {@link Level}
     */
    public void info(Object obj) {
        log(Level.INFO, obj);
    }

    /**
     * @param obj object to log at the debug level see: {@link Level}
     */
    // TODO: This should not be our debug level but it currently is....
    @SuppressWarnings(value = "all")
    public void debug(Object obj) {
        if (CoreProperties.IS_DEBUG_MODE_FORCED || (CoreConfigurationHandler.getInstance() != null && CoreConfigurationHandler.getInstance().isCoreInDebugMode())) {
            log(Level.INFO, obj);
        }
    }
}
