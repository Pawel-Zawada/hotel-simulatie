package system;

import simulation.Guest;
import simulation.Elevator;
import simulation.Gym;
import simulation.Hotel;
import simulation.Restaurant;
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

    private final Hotel hotel;
    private final Restaurant restaurant;

    DataCollector(Hotel hotel, Restaurant restaurant) {
        this.hotel = hotel;
        this.restaurant = restaurant;
    }

    public static int getNumberOfTasks() {
        return TaskRepository.getTaskQueue().size();
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
        return restaurant.guestsInRestaurant.length;
    }

    public int getTotalOfGuests() {
        return hotel.numberOfGuests();
    }

}