package tasks;

/**
 * @author Ümit Tokmak
 * A generic that can be executed by people in the hotel.
 */
public abstract class Task {
    public abstract boolean isDone();

    public abstract void executeStep();

    public abstract void abort();
}

