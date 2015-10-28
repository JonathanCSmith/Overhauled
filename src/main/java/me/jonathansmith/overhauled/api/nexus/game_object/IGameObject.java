package me.jonathansmith.overhauled.api.nexus.game_object;

import me.jonathansmith.overhauled.api.utility.game_object.IObjectRegistrationHelper;
import me.jonathansmith.overhauled.api.utility.game_object.IRecipeRegistrationHelper;
import me.jonathansmith.overhauled.api.utility.game_object.IRenderingRegistrationHelper;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Core interface used by all game objects - this should not be referenced directly, instead
 * a sub class should be used
 */
public interface IGameObject {

    /**
     * @return whether or not this game object is enabled. This should not be toggled between initialisation phases!
     */
    boolean isGameObjectEnabled();

    /**
     * @param objectRegistrationHelper helper class to help object properly register themselves
     */
    void handleObjectRegistration(IObjectRegistrationHelper objectRegistrationHelper);

    /**
     * @param recipeRegistrationHelper helper class to help objects properly register their recipes
     */
    void handleRecipeRegistration(IRecipeRegistrationHelper recipeRegistrationHelper);

    /**
     * @param renderingRegistrationHelper helper class to help objects properly register their rendering
     */
    void handleRenderingRegistration(IRenderingRegistrationHelper renderingRegistrationHelper);
}
