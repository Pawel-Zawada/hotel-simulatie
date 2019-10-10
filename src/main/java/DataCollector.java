import simulation.Elevator;
import tasks.TaskRepository;

public class DataCollector {

    public static int getTotalOfGuests() {
        return Hotel.guests.size();
    }

    public static int getNumberOfTasks() {
        return TaskRepository.getTaskQueue().size();
    }

    public static int getNumberOfOneStarRooms(){
        return -1;
    }

    public static int getNumberOfTwoStarRooms(){
        return -1;
    }

    public static int getNumberOfThreeStarRooms(){
        return -1;
    }

    public static int getNumberOfFourStarRooms(){
        return -1;
    }

    public static int getNumberOfFiveStarRooms(){
        return -1;
    }

    public static int guestsInGym(){
        return -1;
    }

    public static int guestsInElevator(){
        return Elevator.guestsInElevator.size();
    }

}
