package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Server side pre initialisation event
 */
public class ServerPreInitialisationEvent extends ServerSetupEvent {

    private final FMLPreInitializationEvent event;

    public ServerPreInitialisationEvent(FMLPreInitializationEvent event) {
        this.event = event;
    }
}