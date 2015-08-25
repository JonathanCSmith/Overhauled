package me.jonathansmith.overhauled.core.delegate;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import me.jonathansmith.overhauled.api.delegate.event.ServerInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.ServerPostInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.ServerPreInitialisationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Server side delegate
 */
public class ServerDelegate extends CommonDelegate {

    @Override
    public void handleSidedPreInitialisationEvent(FMLPreInitializationEvent event) {
        this.event_bus.post(new ServerPreInitialisationEvent(event));
    }

    @Override
    public void handleSidedInitialisationEvent(FMLInitializationEvent event) {
        this.event_bus.post(new ServerInitialisationEvent(event));
    }

    @Override
    public void handleSidedPostInitialisationEvent(FMLPostInitializationEvent event) {
        this.event_bus.post(new ServerPostInitialisationEvent(event));
    }
}
