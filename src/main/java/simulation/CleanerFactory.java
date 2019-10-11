package simulation;

import tasks.TaskRepository;

public class CleanerFactory {

    private static int cleanerCount = 1;

    public static Cleaner createCleaner(Hotel hotel, TaskRepository sharedTasks) {
        var cleaner = new Cleaner(sharedTasks, hotel, cleanerCount++);
        hotel.getHotelTimer().addObserver(cleaner);
        return cleaner;
    }
}
