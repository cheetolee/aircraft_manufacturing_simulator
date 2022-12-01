package Factories;

import Components.Product;
import Grid.Position;
import Grid.Road;
import Components.Speed;
import ObserverPattern.Observer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This abstract class is the superclass of all the factories that manufacture products
 * It is implemented to make a difference between a warehouse and the other factories
 */
public abstract class ManufacturingFactory extends Factory implements Observer {
    /**
     * The respective required quantities of input components necessary to manufacture a product
     */
    private ArrayList<Integer> requiredQuantity;
    /**
     * List of output products (not yet delivered)
     */
    private ArrayList<Product> output= new ArrayList<>();
    /**
     * List of output delivered products
     */
    private ArrayList<Product> delivery = new ArrayList<>() ;
    /**
     * The client factory
     */
    private Factory client;
    /**
     * The production time necessary to manufacture a product
     */
    private int productionTime;
    /**
     * The speed of a product
     */
    private Speed speed;
    /**
     * The road between this factory and the client factory
     */
    private Road road;
    /**
     * Current clock that represents a snapshot of the production time
     */
    private int currentClock;
    /**
     * Path to the file containing the icon for the factory's output product
     */
    private String componentIconPath;

    /**
     * Instantiates a new manufacturing factory
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
    public ManufacturingFactory(String icon0Path, String icon1Path, String icon2Path, String icon3Path, Position factoryPosition, int id, ArrayList<Integer> requiredQuantity, int productionTime, Speed speed, Road road, Factory client) {
        super(icon0Path, icon1Path, icon2Path, icon3Path, factoryPosition, id);
        this.requiredQuantity = requiredQuantity;
        this.productionTime = productionTime;
        this.speed=speed;
        this.road=road;
        this.client=client;
        this.currentClock=0;
    }

    /**
     * Get the input required quantity to start production
     * @return this input required quantity
     */
    public ArrayList<Integer> getRequiredQuantity() {return requiredQuantity;}

    /**
     * Get the product output list of a manufacturing factory
     * @return
     */
    public ArrayList<Product> getOutput() {return output;}

    /**
     * Add a product to the product output list of the manufacturing factory
     * @param output product output
     */
    public void addOutput(Product output) {this.output.add(output);}

    /**
     * Get the list of products in the delivery list, i.e. products that are delivered
     * @return this manufacturing factory's delivery list
     */
    public ArrayList<Product> getDelivery() {return delivery;}

    /**
     * Add a product to the delivery list of the manufacturing factory
     * @param delivered product to be delivered
     */
    public void addDelivery(Product delivered) {this.delivery.add(delivered);}

    /**
     * Clear the delivery list of the manufacturing factory
     */
    public void clearDelivery() {this.delivery.clear();}

    /**
     * Get the product icon path (product of the manufacturing factory)
     * @return this product's icon path (product of the manufacturing factory)
     */
    public String getComponentIconPath() {return componentIconPath;}

    /**
     * Set the product icon path to given parameter
     * @param componentIconPath product icon path
     */
    public void setComponentIconPath(String componentIconPath) {this.componentIconPath = componentIconPath;}

    /**
     * Get the current clock that represents a snapshot of the production time
     * @return this current clock
     */
    public int getCurrentClock() {return currentClock;}

    /**
     * Set the current clock to given parameter. This method is used to reset clock to zero after finishing the manufacturing of a product
     * @param currentClock clock to be set
     */
    public void setCurrentClock(int currentClock) {this.currentClock = currentClock;}

    /**
     * Delete a list of products from the product output list
     * @param listToBeDeleted list of products to be deleted
     */
    public void deleteOutput(ArrayList<Product> listToBeDeleted){this.output.removeAll(listToBeDeleted);}

    /**
     * Get the product's movement speed. All products of the same manufacturing factory have the same speed
     * @return this product's movement speed
     */
    public Speed getSpeed() {return speed;}

    /**
     * Get the manufacturing factory's road
     * @return this manufacturing factory's road
     */
    public Road getRoad() {return road;}

    /**
     * Get the manufacturing factory's clien
     * @return this manufacturing factory's client
     */
    public Factory getClient() {return client;}

    /**
     * Get the manufacturing factory's production time
     * @return this manufacturing factory's production time
     */
    public int getProductionTime() {return productionTime;}

    /**
     * Observer update method. Set boolean field StopProduction to value given in parameter
     * @param stopProduction boolean parameter, should be given by Warehouse (Subject)
     */
    @Override
    public void update(boolean stopProduction){setStopProduction(stopProduction);}

    /**
     * Start a clock to count the production time, if the input required quantity is available AND if the warehouse isn't full (second condition verified by variable isStopProduction)
     */
    @Override
    public void clock() {
        if (!(isStopProduction()) && getInput().size() >= getRequiredQuantity().get(0)){
            setCurrentClock(getCurrentClock()+5);
            checkProduction();
        }
        checkDelivery();
    }

    /**
     * Verify if the production time is complete, and in such create a product by calling the produce method
     */
    public void checkProduction() {
        if(this.getCurrentClock()==getProductionTime()) {
            //Pour aligner l'icone du produit
            ImageIcon icon = new ImageIcon(getIcon0Path());
            int offsetY=icon.getIconHeight()/2;
            int offsetX=icon.getIconWidth()/2;
            //
            Position position = new Position(getFactoryPosition().getPositionX()-offsetX,getFactoryPosition().getPositionY()-offsetY);
            produce(getComponentIconPath(),position,getSpeed());
            setCurrentClock(0);
        }
    }

    /**
     * Create a product and add it to the product output list
     * @param iconPath Product icon path
     * @param position Product position
     * @param speed Product speed
     */
    public void produce(String iconPath, Position position, Speed speed){
        //create a product and add it to the output list
        Product product = new Product(iconPath,position,speed);
        addOutput(product);
        //once an object is produced, remove inputs from the list
        for (int i=0; i<getRequiredQuantity().get(0);i++){
            getInput().remove(0);
        }
    }

    /**
     * For each product in the output list, increment its position by the corresponding speed
     * If a product arrives to destination (client factory) add it to the delivery list, and remove it from the output list
     */
    public void checkDelivery() {
        for (int i=0;i<getOutput().size();i++){
            getOutput().get(i).getPosition().setPositionX(getOutput().get(i).getPosition().getPositionX()+4*getOutput().get(i).getSpeed().getSpeedX());
            getOutput().get(i).getPosition().setPositionY(getOutput().get(i).getPosition().getPositionY()+4*getOutput().get(i).getSpeed().getSpeedY());
            ImageIcon icon = new ImageIcon(getIcon0Path());
            int offsetY=icon.getIconHeight()/2;
            int offsetX=icon.getIconWidth()/2;
            Position destination = new Position(getRoad().getPosFinish().getPositionX()-offsetX,getRoad().getPosFinish().getPositionY()-offsetY);
            if(destination.equals(getOutput().get(i).getPosition())){
                addDelivery(getOutput().get(i));
            }
        }
        deleteOutput(getDelivery());
    }

    /**
     * Draw factory icon based on the production time
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        //Draw factory icon based on the productionTime
        ImageIcon icon;
        if (getCurrentClock()<getProductionTime()/3){
            icon = new ImageIcon(getIcon0Path());
        } else if (getCurrentClock()<getProductionTime()*2/3){
            icon = new ImageIcon(getIcon1Path());
        } else if (getCurrentClock()<getProductionTime()){
            icon = new ImageIcon(getIcon2Path());
        } else {
            icon = new ImageIcon(getIcon3Path());
        }
        //Draw without Icon Position Offset
        int offsetY=icon.getIconHeight()/2;
        int offsetX=icon.getIconWidth()/2;
        g.drawImage(icon.getImage(),getFactoryPosition().getPositionX()-offsetX , getFactoryPosition().getPositionY()-offsetY, null);
    }
    @Override

    /**
     * For each product in the product output list, draw its icon
     */
    public void drawMaterials(Graphics g) {
        for (int i=0; i<getOutput().size();i++){
            ImageIcon icon = new ImageIcon(getOutput().get(i).getIconPath());
            g.drawImage(icon.getImage(),getOutput().get(i).getPosition().getPositionX(),getOutput().get(i).getPosition().getPositionY(),null);
        }
    }

}