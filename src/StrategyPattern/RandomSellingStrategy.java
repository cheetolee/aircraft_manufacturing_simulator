package StrategyPattern;

import java.util.Random;

/**
 * This class represents the implementation of the random selling strategy - Strategy Design Pattern
 */
public class RandomSellingStrategy implements Strategy {
    private int luckyNumber;

    /**
     * Set the luckyNumber field to given parameter
     * @param number number to be set
     */
    public void setNumber(int number) {this.luckyNumber=number;}

    /**
     * Method used by sell() method of warehouse to enable selling if random number is equal to luckyNumber
     * @return true if random number is equal to lucky number, and false otherwise
     */
    public boolean selling() {return new Random().nextInt(100)==this.luckyNumber;}

}