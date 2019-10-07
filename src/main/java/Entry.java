import java.util.ArrayList;

public class Entry {
    static HotelTimer hotelTimer;

    public static void main(String[] args) {

        Floor floorOne = new Floor(10, 10, 1);
        Floor floorTwo = new Floor(10, 10, 2);

        // Temp guest and cleaner observers
        Cleaner tempCleaner = new Cleaner();
        Guest tempGuest = new Guest();
        ArrayList<IObserver> tempObservers = new ArrayList<>();
        tempObservers.add(tempCleaner);
        tempObservers.add(tempGuest);

        hotelTimer = new HotelTimer(tempObservers);

        // System.out.println(pathFinding.doPathFinding()); // TODO: Uncomment or remove this line.

        System.out.println("No matter how many times I say hello, the world never answers.");

        TaskRepository taskRepository = new TaskRepository();
        taskRepository.enQueue(new Task(10));
        taskRepository.enQueue(new Task(100));

        taskRepository.addEmergencyTask(new Task(300));
        taskRepository.addEmergencyTask(new Task(500));
        taskRepository.elements();

        try {
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
        } catch (Exception e) {
        }

        UserInterface menu = new UserInterface();
    }
}
