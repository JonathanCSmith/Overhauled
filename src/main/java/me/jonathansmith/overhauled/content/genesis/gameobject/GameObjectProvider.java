package me.jonathansmith.overhauled.content.genesis.gameobject;

/**
 * Created by Jonathan Charles Smith on 26/10/15.
 *
 * Provides all of the game objects required for the genesis content
 */
public class GameObjectProvider {

    // Soft to hard <-- logical right
    /*
    public Mud mud; // Clay, Silt
    public Sand sand; // Sand;
    public Pebble pebble; // gravel
    */

    public Soil soil; // podzol, snowy, grass, dirt

    /*
    public FracturedStone fracturedStone; // cobblestone of all types
    public Boulder boulder; //
    public Stone stone; // Stone of all types!
    public CompressedStone compressedStone; //
    public HardenedStone hardenedStone; //
    public TemperedStone temperedStone; //
    public Mineral mineral;
    public Metal metal;

    // Organics
    public Flora flora;
    public

    // Liquids
    public Water water;
    public Lava lava;
    */

    public void build() {
        this.soil = new Soil();
    }
}
