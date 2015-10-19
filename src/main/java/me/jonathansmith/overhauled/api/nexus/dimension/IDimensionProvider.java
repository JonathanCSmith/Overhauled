package me.jonathansmith.overhauled.api.nexus.dimension;

import java.util.LinkedList;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Provider for dimensions
 */
public interface IDimensionProvider {

    /**
     * @return the list of dimensions associated with this provider
     */
    LinkedList<IDimension> getDimensions();
}
