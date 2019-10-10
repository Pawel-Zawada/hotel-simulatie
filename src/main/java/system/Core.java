package system;

import drawing.UserInterface;
import pathfinding.Graph;
import simulation.*;
import tasks.Task;
import tasks.TaskRepository;

import java.util.ArrayList;

public class Core {
    public static HotelTimer hotelTimer = new HotelTimer(new ArrayList<IObserver>());
    public static UserInterface userInterface;
    public static TaskRepository taskRepository = new TaskRepository();

    public Core() {
        var factory = new HotelFactory();
        var hotel = factory.createHotel("assets/hotels/hotel_2.layout");

        taskRepository.enQueue(new Task(1));
        taskRepository.enQueue(new Task(taskRepository.getSize() + 1));

        // Temporary guest and cleaner observers...
        Cleaner tempCleaner = new Cleaner();
        hotel.newGuest();
        ArrayList<IObserver> tempObservers = new ArrayList<>();
        tempObservers.add(tempCleaner);
        Guest tempGuest = new Guest();
        // ...added to the hotel's timer system, to be called every tick.
        hotelTimer.addObserver(tempGuest);
        hotelTimer.addObserver(tempCleaner);

        // Periodically assign and remove tasks. TODO: Remove this and implement real task assignment.
        hotelTimer.addObserver(() -> {
            if ((hotelTimer.getHTEIteration() & 1) == 0) { // Every even tick...
                taskRepository.enQueue(new Task(taskRepository.getSize() + 1)); // ...add a new task.
            }
            if (((double) hotelTimer.getHTEIteration() / 10) % 1 == 0) {
                try {
                    taskRepository.deQueue();
                    taskRepository.deQueue();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        // System.out.println(pathFinding.doPathFinding()); // TODO: Uncomment or remove this line.

        System.out.println("No matter how many times I say hello, the world never answers.");

        userInterface = new UserInterface(hotel);
    }
}
