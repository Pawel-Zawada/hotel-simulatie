package system;

import simulation.Guest;
import tasks.TaskRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCollector {
    public static int getNumberOfTasks() {
        return TaskRepository.getTaskQueue().size();
    }

    public long getTotalOfGuests() {
        Stream<Guest> guestStream = Stream.of();
        return guestStream
                .map(Guest::getGuestID)
                .collect(Collectors.counting());
    }
}
