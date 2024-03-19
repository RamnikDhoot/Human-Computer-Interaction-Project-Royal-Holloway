package model;

/**
 * The {@code Subject} interface is a key component of the Observer design
 * pattern.
 * It defines methods for attaching, detaching, and notifying {@code Observer}
 * objects.
 * Implementing this interface allows a class to be observed by multiple
 * {@code Observer} instances.
 */
public interface Subject {
    /**
     * Attaches an {@code Observer} to the subject.
     * The observer will be notified of changes to the subject's state.
     *
     * @param o The {@code Observer} to be attached.
     */
    void attach(Observer o);

    /**
     * Detaches an {@code Observer} from the subject.
     * The observer will no longer receive updates from the subject.
     *
     * @param o The {@code Observer} to be detached.
     */
    void detach(Observer o);

    /**
     * Notifies all attached {@code Observer}s of a change in the subject's state.
     * This method is typically called by the subject itself when it wants to inform
     * its observers about a state change.
     */
    void notifyObservers();
}