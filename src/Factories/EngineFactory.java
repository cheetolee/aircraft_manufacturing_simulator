package Factories;

import Grid.Position;
import Grid.Road;
import Components.Speed;
import java.util.ArrayList;

/**
 * This class represents the Engine Factory
 */
public class EngineFactory extends ManufacturingFactory{
    /**
     * Instantiates a new engine factory
     * @param icon0Path the path for the file containing the 0% factory icon
     * @param icon1Path the path for the file containing the 33% factory icon
     * @param icon2Path the path for the file containing the 66% factory icon
     * @param icon3Path the path for the file containing the 100% factory icon
     * @param factoryPosition the factory position in the Grid
     * @param id the factory id
     * @param requiredQuantity the input required quantity to start production
     * @param productionTime the production time necessary to manufacture a product
     * @param speed  the product speed
     * @param road the factory road, from this factory to the client
     * @param client the client factory
     */
    public EngineFactory(String icon0Path, String icon1Path, String icon2Path, String icon3Path, Position factoryPosition, int id, ArrayList<Integer> requiredQuantity, int productionTime, Speed speed, Road road, Factory client) {
        super(icon0Path, icon1Path, icon2Path, icon3Path, factoryPosition, id, requiredQuantity, productionTime, speed, road, client);
        setComponentIconPath("./src/ressources/engine.png");
    }

}