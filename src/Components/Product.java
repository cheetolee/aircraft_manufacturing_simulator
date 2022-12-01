package Components;

import Grid.Position;

/**
 * This class represents the different products
 * A product can be metal, engine, wing or even a complete aircraft
 */
public class Product {
    /**
     * Path to the file containing the product icon
     */
    private String iconPath;
    /**
     * Current position of the product on the Grid
     */
    private Position position;
    /**
     * Speed of the product
     */
    private Speed speed;
    /**
     * Instantiate a new product
     * @param iconPath the path for the product icon
     * @param position the position of the product on the Grid
     * @param speed the speed of the product
     */
    public Product(String iconPath, Position position, Speed speed) {
        this.iconPath = iconPath;
        this.position = position;
        this.speed = speed;
    }

    /**
     * Get the product's icon path
     * @return this product's icon path
     */
    public String getIconPath() {return this.iconPath;}

    /**
     * Get the product's position
     * @return this product's position
     */
    public Position getPosition() {return this.position;}

    /**
     * Get the product's speed
     * @return this product's speed
     */
    public Speed getSpeed() {return speed;}

    /**
     * Set the product's position to given parameter
     * @param position position of the product in the Grid
     */
    public void setPosition(Position position) {this.position = position;}

}