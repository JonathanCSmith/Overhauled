package me.jonathansmith.overhauled.api.nexus.trait;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Nexus point for the creation / addition of traits and behaviours for any
 * given object. Acts as a mapping point for object <-> trait, implementation should be
 * thread safe and highly efficient in the search space where possible
 *
 * Note this system does not use objects directly (so traits cannot be associated with instances) but instead
 * associates them with their class
 */
public interface ITraitNexus extends INexus {

    /**
     * Adds a trait to an object. Note, this trait is associated to all instances of the o.getClass() of this object
     *
     * @param o the object to have a trait associated with it
     * @param t the trait to associate
     */
    void addTrait(Object o, ITrait t);

    /**
     * @param o the object to check for a held trait
     * @param traitClazz the class of the trait to look for
     * @return a trait associated with an object of a type traitClazz
     */
    ITrait getTrait(Object o, Class<? extends ITrait> traitClazz);
}
