package Factories;

import Components.Product;
import Grid.Position;
import Grid.Road;
import Components.Speed;
import java.util.ArrayList;

/**
 * This class represents the metal factory
 */
public class MetalFactory extends ManufacturingFactory{

    /**
     * Instantiates a new metal factory
     * @param icon0Path the path for the 0% factory icon
     * @param icon1Path the path for the 33% factory icon
     * @param icon2Path the path for the 66% factory icon
     * @param icon3Path the path for the 100% factory icon
     * @param factoryPosition the factory position in the Grid
     * @param id the factory id
     * @param requiredQuantity the input required quantity to start production
     * @param productionTime the production time necessary to manufacture a product
     * @param speed the product speed
     * @param road the factory road, from this factory to the client
     * @param client the client factory
     */
    public MetalFactory(String icon0Path, String icon1Path, String icon2Path, String icon3Path, Position factoryPosition, int id, ArrayList<Integer> requiredQuantity, int productionTime, Speed speed, Road road, Factory client) {
        super(icon0Path, icon1Path, icon2Path, icon3Path, factoryPosition, id, requiredQuantity, productionTime, speed, road, client);
        setComponentIconPath("./src/ressources/metal.png");
    }

    /**
     * Starts a clock to count the production time, if the input required quantity is available AND if the warehouse isn't full (second condition verified by variable isStopProduction)
     */
    @Override
    public void clock() {
        if (!isStopProduction()) {
            setCurrentClock(getCurrentClock()+5);
            checkProduction();
        }
        checkDelivery();
    }

    /**
     * Creates a product out of the input material
     * @param iconPath the path for the product icon
     * @param position the position of the product on the Grid
     * @param speed the speed of the product
     */
    @Override
    public void produce(String iconPath, Position position, Speed speed){
        Product metal = new Product(iconPath,position,speed);
        super.addOutput(metal);

    }
}