
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import drawing.UserInterface;
import simulation.Cleaner;
import simulation.Floor;
import simulation.Guest;
import simulation.IObserver;
import tasks.Task;
import tasks.TaskRepository;

public class Entry {
    public static void main(String[] args){

        int HotelTimeSpeed = 5000; // 1000 = 1 sec // this will be adjustable


        Floor floorOne = new Floor(10 ,10,1);
        Floor floorTwo = new Floor(10 ,10,2);

        Timer timer = new Timer();
        //temp guest and cleaner observers
        Cleaner tempCleaner = new Cleaner();
        Guest tempGuest = new Guest();
        List<IObserver> tempObservers = new ArrayList<>();
        tempObservers.add(tempCleaner);
        tempObservers.add(tempGuest);
        timer.schedule(new HotelTimer(tempObservers), 0, HotelTimeSpeed);



        //System.out.println(pathFinding.doPathFinding());

        System.out.println("No matter how many times I say hello, the world never answers.");

        TaskRepository taskRepository = new TaskRepository();
        taskRepository.enQueue(new Task(1));
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
