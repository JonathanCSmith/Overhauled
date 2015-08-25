package me.jonathansmith.overhauled.api.nexus.trait;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Generic interface for objects that wish to hold traits.
 */
public interface ITraitHolder {

    /**
     * @param traitClazz the trait to look for
     * @return a held instance of a trait class or null
     */
    ITrait getTrait(Class<? extends ITrait> traitClazz);
}
