package system;

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

    public int getNumberOfOneStarRooms() {
        return -1;
    }

    public int getNumberOfTwoStarRooms() {
        return -1;
    }

    public int getNumberOfThreeStarRooms() {
        return -1;
    }

    public int getNumberOfFourStarRooms() {
        return -1;
    }

    public int getNumberOfFiveStarRooms() {
        return -1;
    }

    public int guestsInGym() {
        return hotel.getGym().totalGuestsInGym();
    }

    public int guestsInElevator() {
        return hotel.getElevator().numberOfGuestsInElevator();
    }

    public int guestsInRestaurant() {
        return restaurants.stream().collect(Collectors.summingInt(r -> r.getNumberOfGuests()));
    }

    public int getTotalOfGuests() {
        return hotel.numberOfGuests();
    }

}
