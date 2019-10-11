package simulation;

/**
 * Used by classes that want to listen for HTE events.
 */
public interface HteObserver {
    /**
     * Method that will be called every tick.
     */
    void observeHTE();
}
