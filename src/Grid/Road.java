package Grid;

/**
 * This class represents the road (line) between the factories
 * Products are delivered from one factory to another following the defined road
 */
public class Road {
    /**
     * Start position of the road
     */
    private Position posStart;
    /**
     * Finish position of the road
     */
    private Position posFinish;

    /**
     * Instantiates a new road
     * @param posStart the start position of the road
     * @param posFinish the finish position of the road
     */
    public Road(Position posStart, Position posFinish) {
        this.posStart = posStart;
        this.posFinish = posFinish;
    }

    /**
     * Get the start position of the road
     * @return This road's start position
     */
    public Position getPosStart() {return posStart;}
    /**
     * Get the finish position of the road
     * @return This road's finish position
     */
    public Position getPosFinish() {return posFinish;}

}