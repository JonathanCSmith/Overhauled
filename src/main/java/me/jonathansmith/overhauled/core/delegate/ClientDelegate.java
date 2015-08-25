package me.jonathansmith.overhauled.core.delegate;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import me.jonathansmith.overhauled.api.delegate.event.ClientInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.ClientPostInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.ClientPreInitialisationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Client side delegate
 */
public class ClientDelegate extends CommonDelegate {

    @Override
    public void handleSidedPreInitialisationEvent(FMLPreInitializationEvent event) {
        this.event_bus.post(new ClientPreInitialisationEvent(event));
    }

    @Override
    public void handleSidedInitialisationEvent(FMLInitializationEvent event) {
        this.event_bus.post(new ClientInitialisationEvent(event));
    }

    @Override
    public void handleSidedPostInitialisationEvent(FMLPostInitializationEvent event) {
        this.event_bus.post(new ClientPostInitialisationEvent(event));
    }
}
