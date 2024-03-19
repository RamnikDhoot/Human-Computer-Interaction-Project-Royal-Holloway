package model;

/**
 * The {@code Observer} interface is a fundamental part of the Observer design
 * pattern.
 * It should be implemented by classes that need to be notified about changes in
 * a {@code Subject}.
 */
public interface Observer {
    /**
     * Called by the {@code Subject} whenever the state changes that this
     * {@code Observer} needs to be updated on.
     * Implementing classes should override this method to define how they react to
     * such notifications.
     */
    void update();
}
