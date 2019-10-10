import simulation.Elevator;
import simulation.Gym;
import simulation.Restaurant;
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
        return Gym.guestsInGym.size();
    }

    public static int guestsInElevator(){
        return Elevator.guestsInElevator.size();
    }

    public static int guestsInRestaurant(){
        return Restaurant.guestsInRestaurant.length;
    }

}
