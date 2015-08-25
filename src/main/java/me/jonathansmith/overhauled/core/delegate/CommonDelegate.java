package me.jonathansmith.overhauled.core.delegate;

import java.util.LinkedList;

import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.EventBus;

import me.jonathansmith.overhauled.api.OverhauledAPI;
import me.jonathansmith.overhauled.api.content.IContent;
import me.jonathansmith.overhauled.api.delegate.IDelegate;
import me.jonathansmith.overhauled.api.delegate.event.CommonInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.CommonPostInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.CommonPreInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.INexus;
import me.jonathansmith.overhauled.core.ForgeState;
import me.jonathansmith.overhauled.core.log.LogHandler;
import me.jonathansmith.overhauled.core.nexus.CoreNexusProvider;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 * <p/>
 * Core delegate class for the overhauled mod. Delegates methods to side specific behaviour modifying classes.
 */
public abstract class CommonDelegate implements IDelegate {

    protected final EventBus             event_bus    = new EventBus();
    protected final LinkedList<INexus>   nexus_list   = new LinkedList<>();
    private final   LinkedList<IContent> content_list = new LinkedList<>();

    private ForgeState forgeState = ForgeState.SETTING_UP;

    public void registerNexus(INexus nexus) {
        if (this.forgeState.ordinal() >= ForgeState.PRE_INITIALISING.ordinal()) {
            LogHandler.getInstance().warn("Cannot register a nexus after the pre-initialisation phase. Add your content to OverhauledAPI during your mod's construction.");
        }

        this.nexus_list.add(nexus);
    }

    public void registerContent(IContent deployableContent) {
        if (this.forgeState.ordinal() >= ForgeState.PRE_INITIALISING.ordinal()) {
            LogHandler.getInstance().warn("Cannor register content after the pre-initialisation phase. Add your content to OverhauledAPI during your mod's construction.");
        }

        this.content_list.add(deployableContent);
    }

    public void handleFingerprintViolationEvent(FMLFingerprintViolationEvent event) {

    }

    public void handlePreInitialisationEvent(FMLPreInitializationEvent event) {
        // Set the state
        this.forgeState = ForgeState.PRE_INITIALISING;

        // Handle registries
        CoreNexusProvider nexusProvider = new CoreNexusProvider();
        for (INexus registry : this.nexus_list) {
            nexusProvider.registerNexus(registry);
            this.event_bus.register(registry);
        }
        OverhauledAPI.setNexusProvider(nexusProvider);

        // Handle content
        for (IContent content : this.content_list) {
            this.event_bus.register(content);
        }

        // Setup modules before we call pre init methods
        for (IContent content : this.content_list) {
            content.buildForPreInitialisation(this);
        }

        this.event_bus.post(new CommonPreInitialisationEvent(event));
        this.handleSidedPreInitialisationEvent(event);
    }

    public abstract void handleSidedPreInitialisationEvent(FMLPreInitializationEvent event);

    public void handleInitialisationEvent(FMLInitializationEvent event) {
        // Set the state
        this.forgeState = ForgeState.INITIALISING;

        // Pass along the event
        this.event_bus.post(new CommonInitialisationEvent(event));
        this.handleSidedInitialisationEvent(event);
    }

    public abstract void handleSidedInitialisationEvent(FMLInitializationEvent event);

    public void handlePostInitialisationEvent(FMLPostInitializationEvent event) {
        // Set the state
        this.forgeState = ForgeState.POST_INITIALISING;

        // Pass along the event
        this.event_bus.post(new CommonPostInitialisationEvent(event));
        this.handleSidedPostInitialisationEvent(event);

        // Set the state
        this.forgeState = ForgeState.DONE_LOADING;
    }

    public abstract void handleSidedPostInitialisationEvent(FMLPostInitializationEvent event);

    public void handleServerStartingEvent(FMLServerAboutToStartEvent event) {
    }

    public void handleServerStartedEvent(FMLServerStartedEvent event) {
    }

    public void handleServerStoppingEvent(FMLServerStoppingEvent event) {
    }

    public void handleServerStoppedEvent(FMLServerStoppedEvent event) {
    }
}
