package system;

import simulation.Guest;
import tasks.Task;
import tasks.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCollector {

    public static List<Task> taskList;

    public static List<Guest> guestList = Arrays.asList();

    public long getTotalOfGuests() {
        Stream<Guest> guestStream = Stream.of();
        return guestStream
                .map(Guest::getGuestID)
                .collect(Collectors.counting());
    }

    public static long getNumberOfTasks() {
        return TaskRepository.getTaskQueue().size();
    }
}
