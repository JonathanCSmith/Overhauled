package me.jonathansmith.overhauled.api.content;

import me.jonathansmith.overhauled.api.IModule;
import me.jonathansmith.overhauled.api.delegate.IDelegate;
import me.jonathansmith.overhauled.core.delegate.CommonDelegate;

/**
 * Created by Jonathan Charles Smith on 25/08/15.
 *
 * Contract for deployable content within the overhauled framework
 */
public interface IContent extends IModule {

    /**
     * Method called when content should be setup prior to the pre-initialisation event. This should register all
     * register-ables with their appropriate registries
     *
     * @param delegate
     */
    void buildForPreInitialisation(IDelegate delegate);
}
