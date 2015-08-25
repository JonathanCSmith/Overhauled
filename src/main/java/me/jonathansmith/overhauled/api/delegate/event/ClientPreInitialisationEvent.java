package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Client sided pre initialisation event.
 */
public class ClientPreInitialisationEvent extends ClientSetupEvent {

    private final FMLPreInitializationEvent event;

    public ClientPreInitialisationEvent(FMLPreInitializationEvent event) {
        this.event = event;
    }
}
