package me.jonathansmith.overhauled.api.nexus.game_object;

import java.util.List;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Game object group provider, allows an alternative for deploying game objects
 */
public interface IGameObjectProvider {

    List<IGameObject> getGameObjects();
}
