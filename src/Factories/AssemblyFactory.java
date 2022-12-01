package Factories;

import Components.Product;
import Grid.Position;
import Grid.Road;
import Components.Speed;
import java.util.ArrayList;

/**
 * This class represents the Assembly Factory
 */
public class AssemblyFactory extends ManufacturingFactory {

    /**
     * List of wing products necessary to produce the aircraft
     */
    private ArrayList<Product> inputWing = new ArrayList<>();
    /**
     * List of engine products necessary to produce the aircraft
     */
    private ArrayList<Product> inputEngine = new ArrayList<>();

    /**
     * Instantiates a new assembly factory
     * @param icon0Path the path for the file containing the 0% factory icon
     * @param icon1Path the path for the file containing the 33% factory icon
     * @param icon2Path the path for the file containing the 66% factory icon
     * @param icon3Path the path for the file containing the 100% factory icon
     * @param factoryPosition the factory position in the Grid
     * @param id the factory id
     * @param requiredQuantity the input required quantity to start production
     * @param productionTime the production time necessary to manufacture a product
     * @param speed the product speed
     * @param road the factory road, from this factory to the client
     * @param client the client factory
     */
    public AssemblyFactory(String icon0Path, String icon1Path, String icon2Path, String icon3Path, Position factoryPosition, int id, ArrayList<Integer> requiredQuantity, int productionTime, Speed speed, Road road, Factory client) {
        super(icon0Path, icon1Path, icon2Path, icon3Path, factoryPosition, id, requiredQuantity, productionTime, speed,road,client);
        setComponentIconPath("./src/ressources/aircraft.png");
    }

    /**
     * Starts a clock to count the production time, if the input required quantity is available AND if the warehouse isn't full (second condition verified by variable isStopProduction)
     */
    @Override
    public void clock() {
        if (!(isStopProduction()) && getInputWing().size()>=getRequiredQuantity().get(0) && getInputEngine().size()>=getRequiredQuantity().get(1)){
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
        Product flight = new Product(iconPath,position,speed);
        super.addOutput(flight);
        //once an object is produced, remove inputs used in production from the list
        for (int i=0; i<getRequiredQuantity().get(0);i++){
            getInputWing().remove(0);
        }
        for (int i=0; i<getRequiredQuantity().get(1);i++){
            getInputEngine().remove(0);
        }
    }

    /**
     * Get the wing input list
     * @return this wing input list
     */
    public ArrayList<Product> getInputWing() {return inputWing;}

    /**
     * Adds a list of wing input to the existing one
     * @param inputWing list of wing input
     */
    public void addInputWing(ArrayList<Product> inputWing) {this.inputWing.addAll(inputWing);}

    /**
     * Get the engine input list
     * @return this engine input list
     */
    public ArrayList<Product> getInputEngine() {return inputEngine;}

    /**
     * Adds a list of engine input to the existing one
     * @param inputEngine list of engine input
     */
    public void addInputEngine(ArrayList<Product> inputEngine) {this.inputEngine.addAll(inputEngine);}

}