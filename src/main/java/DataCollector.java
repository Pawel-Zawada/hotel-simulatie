import tasks.TaskRepository;

public class DataCollector {

    public static int getTotalOfGuests() {
        return Hotel.guests.size();
    }

    public static int getNumberOfTasks() {
        return TaskRepository.getTaskQueue().size();
    }
}
