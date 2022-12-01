package Factories;


import Grid.Position;
import ObserverPattern.Observer;
import ObserverPattern.Subject;
import StrategyPattern.Strategy;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the warehouse
 */
public class Warehouse extends Factory implements Subject {
    /**
     * Storage capacity of the warehouse
     */
    private int capacity;
    /**
     * List of observers (factories)
     */
    private ArrayList<Observer> listOfObservers;
    /**
     * Selling strategy of the warehouse
     */
    private Strategy sellingStrategy;

    /**
     * Instantiates a new warehouse
     * @param icon0Path the path for the 0% warehouse icon
     * @param icon1Path the path for the 33% warehouse icon
     * @param icon2Path the path for the 66% warehouse icon
     * @param icon3Path the path for the 100% warehouse icon
     * @param factoryPosition the warehouse position in the Grid
     * @param id the warehouse id
     * @param capacity the warehouse capacity
     */
    public Warehouse(String icon0Path, String icon1Path, String icon2Path, String icon3Path, Position factoryPosition, int id, int capacity) {
        super(icon0Path, icon1Path, icon2Path, icon3Path, factoryPosition, id);
        this.capacity = capacity;
        this.listOfObservers=new ArrayList<>();
    }

    /**
     * Verifies if a selling strategy was chosen, and sells products accordingly i.e. remove products from the product input list
     */
    public void sell(){
        if (getInput().size()!=0){
            this.sellingStrategy.setNumber(getInput().size());
            if(this.sellingStrategy.selling()){
                this.removeInput();
            }
        }
    }

    /**
     * Remove product from the product input list
     */
    public void removeInput(){getInput().remove(0);}

    /**
     * Set selling strategy to the strategy given in parameter
     * @param sellingStrategy selling strategy to be set
     */
    public void setSellingStrategy(Strategy sellingStrategy) {
        this.sellingStrategy = sellingStrategy;
    }

    /**
     * Get the warehouse capacity
     * @return this warehouse capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Draw warehouse icon based on storage capacity
     * @param g Graphics
     */
    @Override
    public void draw(Graphics g) {
        ImageIcon icon;
        if (getInput().size()<capacity/3){
            icon = new ImageIcon(getIcon0Path());
        } else if (getInput().size()<capacity*2/3){
            icon = new ImageIcon(getIcon1Path());
        } else if (getInput().size()<capacity){
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
    public void drawMaterials(Graphics g) {}

    /**
     * Verify if the warehouse is full or not, in both cases notify observers
     */
    @Override
    public void clock() {
        if(getInput().size()>=getCapacity()){
            setStopProduction(true);
            notifyObservers();
        }else {
            setStopProduction(false);
            notifyObservers();
        }
    }

    /**
     * Attach observer to the list of observers
     * @param observer factory observing the warehouse
     */
    @Override
    public void attach(Observer observer) {listOfObservers.add(observer);}

    /**
     * Notify observers to update their status
     */
    @Override
    public void notifyObservers() {
        for (int i=0; i<listOfObservers.size();i++){
            listOfObservers.get(i).update(isStopProduction());
        }
    }
    // method detach() could be added, but it isn't used in this project
    // public void detach(Observer observer) {listOfObservers.remove(observer);}

}