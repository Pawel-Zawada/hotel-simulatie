package tasks;

/**
 * @author Ümit Tokmak
 */
public abstract class Task {
    public abstract boolean isDone();

    public abstract void executeStep();

    public abstract void abort();
}

