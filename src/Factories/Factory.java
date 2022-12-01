package Factories;

import Components.Product;
import Grid.Position;
import java.awt.*;
import java.util.ArrayList;

/**
 * This abstract class is the superclass of all the factories
 */
public abstract class Factory {
    /**
     * Path for the file containing the 0% factory icon
     */
    private String icon0Path;
    /**
     * Path for the file containing the 33% factory icon
     */
    private String icon1Path;
    /**
     * Path for the file containing the 66% factory icon
     */
    private String icon2Path;
    /**
     * Path for the file containing the 100% factory icon
     */
    private String icon3Path;
    /**
     * Factory position on the Grid
     */
    private Position factoryPosition;
    /**
     * Factory id
     */
    private int id;
    /**
     * Input components necessary to manufacture a product
     */
    private ArrayList<Product> input = new ArrayList<>();
    /**
     * Boolean variable used to stop or resume the production
     */
    private boolean stopProduction=false;

    /**
     * Instantiates a new factory
     * @param icon0Path the path for the file containing the 0% factory icon
     * @param icon1Path the path for the file containing the 33% factory icon
     * @param icon2Path the path for the file containing the 66% factory icon
     * @param icon3Path the path for the file containing the 100% factory icon
     * @param factoryPosition the factory position in the Grid
     * @param id the factory id
     */
    public Factory(String icon0Path, String icon1Path, String icon2Path, String icon3Path, Position factoryPosition, int id) {
        this.icon0Path = icon0Path;
        this.icon1Path = icon1Path;
        this.icon2Path = icon2Path;
        this.icon3Path = icon3Path;
        this.factoryPosition = factoryPosition;
        this.id=id;
    }

    /**
     * Get factory 0% icon
     * @return this factory 0% icon
     */
    public String getIcon0Path() {return icon0Path;}

    /**
     * Get factory 33% icon
     * @return this factory 33% icon
     */
    public String getIcon1Path() {return icon1Path;}

    /**
     * Get factory 66% icon
     * @return this factory 66% icon
     */
    public String getIcon2Path() {return icon2Path;}

    /**
     * Get factory 100% icon
     * @return this factory 100% icon
     */
    public String getIcon3Path() {return icon3Path;}

    /**
     * Get factory position on the Grid
     * @return this factory's position
     */
    public Position getFactoryPosition() {return factoryPosition;}

    /**
     * Get factory ID
     * @return this factory's ID
     */
    public int getId() {return id;}

    /**
     * Get the product input list of a factory
     * @return this factory's input list
     */
    public ArrayList<Product> getInput() {return input;}

    /**
     * Add a product input list to the existing one
     * @param input product input list
     */
    public void addInput(ArrayList<Product> input) {this.input.addAll(input);}

    /**
     * Checks state of boolean field stopProduction used in observer pattern to stop ro resume production based on warehouse storage
     * @return true if warehouse is full, false if not
     */
    public boolean isStopProduction() {return stopProduction;}

    /**
     * Sets field stopProduction to given parameter
     * @param stopProduction boolean parameter, true to stop production, false to resume
     */
    public void setStopProduction(boolean stopProduction) {this.stopProduction = stopProduction;}

    public abstract void clock();
    public abstract void draw(Graphics g);
    public abstract void drawMaterials(Graphics g);

}