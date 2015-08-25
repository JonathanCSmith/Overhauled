package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Server side initialisation event
 */
public class ServerInitialisationEvent extends ServerSetupEvent {

    private final FMLInitializationEvent event;

    public ServerInitialisationEvent(FMLInitializationEvent event) {
        this.event = event;
    }
}
