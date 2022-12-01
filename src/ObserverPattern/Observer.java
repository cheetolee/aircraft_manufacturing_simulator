package ObserverPattern;

/**
 * Observer Interface - Observer Design Pattern
 */
public interface Observer {
    /**
     * Update observer state based on the boolean parameter stopProduction
     * @param stopProduction true when the warehouse is full, and false otherwise
     */
    void update(boolean stopProduction);
}