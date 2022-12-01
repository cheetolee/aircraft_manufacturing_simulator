package Grid;

/**
 * This class represents the position of a component or a factory on the grid
 */
public class Position {
    /**
     * Position according to the X-axis
     */
    private int posX;
    /**
     * Position according to the Y-axis
     */
    private int posY;

    /**
     * Instantiates a new position
     * @param posX position according to X-axis
     * @param posY position according to Y-axis
     */
    public Position(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Get position according to X-axis
     * @return this position-X
     */
    public int getPositionX() { return this.posX; }
    /**
     * Get position according to Y-axis
     * @return this position-Y
     */
    public int getPositionY() {
        return this.posY;
    }

    /**
     * Set position-X to a given parameter
     * @param posX postion-X to be set
     */
    public void setPositionX(int posX) {
        this.posX = posX;
    }
    /**
     * Set position-Y to a given parameter
     * @param posY postion-Y to be set
     */
    public void setPositionY(int posY) {
        this.posY = posY;
    }

    /**
     * Verify if a position given in parameter equals this position
     * @param posA position to be compared
     * @return
     */
    public boolean equals(Position posA){
        if (this.posX==posA.posX && this.posY==posA.posY)return true;
        return false;
    }

}