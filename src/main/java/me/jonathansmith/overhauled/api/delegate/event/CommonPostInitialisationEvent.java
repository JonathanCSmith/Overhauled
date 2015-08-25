package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Common post initialisation event. Distributed on all sides when subscribed to
 */
public class CommonPostInitialisationEvent extends CommonSetupEvent {

    private final FMLPostInitializationEvent event;

    public CommonPostInitialisationEvent(FMLPostInitializationEvent event) {
        this.event = event;
    }
}
