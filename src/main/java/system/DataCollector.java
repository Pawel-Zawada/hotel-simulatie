package system;

import simulation.Elevator;
import simulation.Gym;
import simulation.Hotel;
import simulation.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ümit Tokmak
 */
public class DataCollector {
    Hotel hotel;
    List<Restaurant> restaurants;

    public DataCollector(Hotel hotel) {
        this.hotel = hotel;
        this.restaurants = hotel.getRestaurants();
    }

    public int getNumberOfTasks() {
        return hotel.getTaskQueue().getSize();
    }

    public static int getNumberOfOneStarRooms() {
        return -1;
    }

    public static int getNumberOfTwoStarRooms() {
        return -1;
    }

    public static int getNumberOfThreeStarRooms() {
        return -1;
    }

    public static int getNumberOfFourStarRooms() {
        return -1;
    }

    public static int getNumberOfFiveStarRooms() {
        return -1;
    }

    public static int guestsInGym() {
        return Gym.guestsInGym.size();
    }

    public static int guestsInElevator() {
        return Elevator.guestsInElevator.size();
    }

    public int guestsInRestaurant() {
        return restaurants.stream().collect(Collectors.summingInt(r -> r.getNumberOfGuests()));
    }

    public int getTotalOfGuests() {
        return hotel.numberOfGuests();
    }

}
