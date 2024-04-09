package model;

/**
 * Represents an Observer in the Observer design pattern.
 * Classes that should be notified about state changes in a Subject must implement this interface.
 */
public interface Observer {
    /**
     * This method is called by the Subject whenever its state changes to notify this Observer.
     * Classes implementing this interface should define this method to respond to the state changes.
     */
    void update();
}
