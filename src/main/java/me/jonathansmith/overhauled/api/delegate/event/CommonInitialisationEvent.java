package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Common initialisation event. Distributed universally when this is subscribed to.
 */
public class CommonInitialisationEvent extends CommonSetupEvent {

    private final FMLInitializationEvent event;

    public CommonInitialisationEvent(FMLInitializationEvent event) {
        this.event = event;
    }
}
