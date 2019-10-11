package simulation;

import tasks.TaskRepository;

/**
 * @author Ümit Tokmak
 */
public class CleanerFactory {

    private static int cleanerCount = 1;

    /**
     * Creates a cleaner, adding the cleaner to the timer observer list as well.
     */
    public static Cleaner createCleaner(Hotel hotel, TaskRepository sharedTasks) {
        var cleaner = new Cleaner(sharedTasks, hotel, cleanerCount++);
        hotel.getHotelTimer().addObserver(cleaner);
        return cleaner;
    }
}
