package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Client side post initialisation event
 */
public class ClientPostInitialisationEvent extends ClientSetupEvent {

    private final FMLPostInitializationEvent event;

    public ClientPostInitialisationEvent(FMLPostInitializationEvent event) {
        this.event = event;
    }
}