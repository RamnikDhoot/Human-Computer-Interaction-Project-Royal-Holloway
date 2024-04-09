package model;

/**
 * The {@code Subject} interface is a fundamental part of the Observer design
 * pattern. It outlines the methods for adding and removing {@link Observer} objects,
 * as well as notifying them of any changes.
 *
 * Classes that implement this interface can have multiple {@link Observer}
 * instances that monitor changes in the implementing class's state.
 */
public interface Subject {
    /**
     * Registers an {@link Observer} to the subject. Once attached, the
     * observer will be updated about any changes to the subject's state.
     *
     * @param o the {@link Observer} to be attached; should not be {@code null}
     */
    void attach(Observer o);

    /**
     * Unregisters an {@link Observer} from the subject. After being detached,
     * the observer will no longer be updated about changes to the subject's state.
     *
     * @param o the {@link Observer} to be detached; should not be {@code null}
     */
    void detach(Observer o);

    /**
     * Notifies all registered {@link Observer}s about a change in the subject's state.
     * This method is typically invoked when the subject undergoes a change that
     * observers should be made aware of.
     */
    void notifyObservers();
}