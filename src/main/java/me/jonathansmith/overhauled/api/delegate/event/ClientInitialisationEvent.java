package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Client side initialisation event
 */
public class ClientInitialisationEvent extends ClientSetupEvent {

    private final FMLInitializationEvent event;

    public ClientInitialisationEvent(FMLInitializationEvent event) {
        this.event = event;
    }
}
