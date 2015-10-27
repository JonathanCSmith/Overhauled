package me.jonathansmith.overhauled.api.nexus.game_object;

import me.jonathansmith.overhauled.api.nexus.INexus;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Contract for the game object nexus. Objects will be automatically deployed
 */
public interface IGameObjectNexus extends INexus {

    void registerGameObject(IGameObject gameObject);

    void registerGameObjectProvider(IGameObjectProvider gameObjectProvider);
}
