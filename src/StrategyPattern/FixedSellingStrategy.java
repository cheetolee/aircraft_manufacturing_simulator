package StrategyPattern;

/**
 * This class represents the implementation of the fixed selling strategy - Strategy Design Pattern
 */
public class FixedSellingStrategy implements Strategy {
    private int requiredQuantity =1;
    private int quantity;

    /**
     * Set the quantity field to given parameter
     * @param quantity quantity to be set
     */
    public void setNumber(int quantity){this.quantity=quantity;}

    /**
     * Method used by sell() method of warehouse to enable selling if quantity is at least 1
     * @return true if quantity is greater or equal to required quantity, and false otherwise
     */
    public boolean selling(){return this.quantity>=requiredQuantity;}
}