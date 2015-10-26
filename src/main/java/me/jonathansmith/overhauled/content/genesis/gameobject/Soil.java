package me.jonathansmith.overhauled.content.genesis.gameobject;

import me.jonathansmith.overhauled.api.OverhauledAPI;
import me.jonathansmith.overhauled.api.archetype.game_object.MultiMetaBlockObject;
import me.jonathansmith.overhauled.api.utility.game_object.ObjectRegistrationHelper;
import me.jonathansmith.overhauled.api.utility.game_object.RecipeRegistrationHelper;
import me.jonathansmith.overhauled.api.utility.game_object.RenderingRegistrationHelper;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 */
public class Soil extends MultiMetaBlockObject {

    public Soil() {
        super(null);

        OverhauledAPI.getNexusProvider().getGameObjectNexus().registerGameObject(this);
    }

    @Override
    public boolean isGameObjectEnabled() {
        return false;
    }

    @Override
    public void handleObjectRegistration(ObjectRegistrationHelper objectRegistrationHelper) {

    }

    @Override
    public void handleRecipeRegistration(RecipeRegistrationHelper recipeRegistrationHelper) {

    }

    @Override
    public void handleRenderingRegistration(RenderingRegistrationHelper renderingRegistrationHelper) {

    }
}
