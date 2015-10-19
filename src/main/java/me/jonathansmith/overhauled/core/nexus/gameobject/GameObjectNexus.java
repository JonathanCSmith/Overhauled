package me.jonathansmith.overhauled.core.nexus.gameobject;

import me.jonathansmith.overhauled.api.nexus.gameobject.IGameObjectNexus;

/**
 * Created by Jon on 27/09/2015.
 */
public class GameObjectNexus implements IGameObjectNexus {

    private static GameObjectNexus instance;

    public static GameObjectNexus getInstance() {
        if (GameObjectNexus.instance == null) {
            GameObjectNexus.instance = new GameObjectNexus();
        }

        return GameObjectNexus.instance;
    }

    private GameObjectNexus() {
    }

    @Override
    public String getUniqueIdentifier() {
        return "Game Object Nexus";
    }
}
