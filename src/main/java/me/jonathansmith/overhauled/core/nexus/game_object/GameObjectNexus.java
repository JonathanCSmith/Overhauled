package me.jonathansmith.overhauled.core.nexus.game_object;

import java.util.ArrayList;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import me.jonathansmith.overhauled.api.delegate.event.ClientInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.CommonInitialisationEvent;
import me.jonathansmith.overhauled.api.delegate.event.CommonPreInitialisationEvent;
import me.jonathansmith.overhauled.api.nexus.game_object.IGameObject;
import me.jonathansmith.overhauled.api.nexus.game_object.IGameObjectNexus;
import me.jonathansmith.overhauled.api.nexus.game_object.IGameObjectProvider;
import me.jonathansmith.overhauled.api.utility.game_object.*;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 * <p/>
 * Implementation of the IGameObjectNexus that allows automated deployment of game objects
 */
public class GameObjectNexus implements IGameObjectNexus {

    // Facilitators to enable reduced boilerplate visitor pattern
    private static final IObjectRegistrationHelper    OBJECT_REGISTRATION_HELPER    = new ObjectRegistrationHelper();
    private static final IRecipeRegistrationHelper    RECIPE_REGISTRATION_HELPER    = new RecipeRegistrationHelper();
    private static final IRenderingRegistrationHelper RENDERING_REGISTRATION_HELPER = new RenderingRegistrationHelper();

    private static GameObjectNexus instance;

    public static GameObjectNexus getInstance() {
        if (GameObjectNexus.instance == null) {
            GameObjectNexus.instance = new GameObjectNexus();
        }

        return GameObjectNexus.instance;
    }

    private final ArrayList<IGameObject> game_objects = new ArrayList<>();

    private GameObjectNexus() {
    }

    @Override
    public String getUniqueIdentifier() {
        return "Game Object Nexus";
    }

    @Override
    public void registerGameObject(IGameObject gameObject) {
        this.game_objects.add(gameObject);
    }

    @Override
    public void registerGameObjectProvider(IGameObjectProvider gameObjectProvider) {
        for (IGameObject gameObject : gameObjectProvider.getGameObjects()) {
            this.game_objects.add(gameObject);
        }
    }

    @SubscribeEvent
    public void handlePreInitialisationEvent(CommonPreInitialisationEvent event) {
        for (IGameObject gameObject : this.game_objects) {
            if (!gameObject.isGameObjectEnabled()) {
                continue;
            }

            gameObject.handleObjectRegistration(OBJECT_REGISTRATION_HELPER);
        }
    }

    @SubscribeEvent
    public void handleInitialisationEvent(CommonInitialisationEvent event) {
        for (IGameObject gameObject : this.game_objects) {
            if (!gameObject.isGameObjectEnabled()) {
                continue;
            }

            gameObject.handleRecipeRegistration(RECIPE_REGISTRATION_HELPER);
        }
    }

    @SubscribeEvent
    public void handleClientInitialisationEvent(ClientInitialisationEvent event) {
        for (IGameObject gameObject : this.game_objects) {
            if (!gameObject.isGameObjectEnabled()) {
                continue;
            }

            gameObject.handleRenderingRegistration(RENDERING_REGISTRATION_HELPER);
        }
    }
}
