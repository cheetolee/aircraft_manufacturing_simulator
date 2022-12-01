package ObserverPattern;

/**
 * Subject Interface - Observer Design Pattern
 */
public interface Subject {
    /**
     * Attach an observer to the list of observers
     * @param observer factory observing the subject
     */
    void attach(Observer observer);

    /**
     * Notify the list of observers when the subject's state has changed
     */
    void notifyObservers();
    // detach could be added, but it is not used in this project
    //void detach(Observer observer);
}