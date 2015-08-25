package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Server side post initialisation event
 */
public class ServerPostInitialisationEvent extends ServerSetupEvent {

    private final FMLPostInitializationEvent event;

    public ServerPostInitialisationEvent(FMLPostInitializationEvent event) {
        this.event = event;
    }
}
