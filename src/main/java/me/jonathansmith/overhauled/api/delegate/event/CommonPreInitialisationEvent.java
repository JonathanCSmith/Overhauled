package me.jonathansmith.overhauled.api.delegate.event;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Common pre-initialisation event. Distributed on all sides when subscribed to.
 */
public class CommonPreInitialisationEvent extends CommonSetupEvent {

    private final FMLPreInitializationEvent event;

    public CommonPreInitialisationEvent(FMLPreInitializationEvent event) {
        this.event = event;
    }

    public FMLPreInitializationEvent getPreInitialisationEvent() {
        return this.event;
    }
}
