package Components;

/**
 * This class represents the speed of the components
 */
public class Speed {
    /**
     * Speed according to X-axis
     */
    private int speedX;
    /**
     * Speed according to Y-axis
     */
    private int speedY;

    /**
     * Instantiates a speed
     * @param speedX the speed according to X-axis
     * @param speedY the speed according to Y-axis
     */
    public Speed(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * Get the speed according to X-axis
     * @return speed according to X-axis
     */
    public int getSpeedX(){return this.speedX;}
    /**
     * Get the speed according to Y-axis
     * @return speed according to Y-axis
     */
    public int getSpeedY(){return this.speedY;}

}