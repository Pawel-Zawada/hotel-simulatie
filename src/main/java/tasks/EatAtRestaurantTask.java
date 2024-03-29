package tasks;

import simulation.Guest;
import simulation.Hotel;
import simulation.Restaurant;

import java.util.ArrayList;

/**
 * @author Marc Kemp
 * This task gets executed when a guest eats at a restaurant.
 */
public class EatAtRestaurantTask extends Task{

    private Restaurant restaurant;
    private Hotel hotel;
    private final ArrayList<Restaurant> restaurantsToExclude;
    private final Guest guest;
    private int timeStayed = 0;
    private boolean isDone = false;

    public EatAtRestaurantTask(Hotel hotel, Restaurant restaurant, ArrayList<Restaurant> restaurantsToExclude, Guest guest){
        this.restaurant = restaurant;
        this.hotel = hotel;
        this.restaurantsToExclude = restaurantsToExclude;
        this.guest = guest;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void executeStep() {
        if(restaurant.isFull()){
            restaurantsToExclude.add(restaurant);
            hotel.handleDinnerRequest(guest.getGuestNumber(),restaurantsToExclude);
            System.out.println("Guest "+guest.getName()+" arrived at a restaurant but it was full");
            isDone = true;
        }
        if(timeStayed==0 && !restaurant.isFull()){
            restaurant.enterRestaurant(guest);
        }
        else if(timeStayed >= hotel.getConfiguration().getDinnerTime()){
            System.out.println("Guest "+guest.getName()+" is done eating");
            restaurant.leaveRestaurant(guest);
            isDone = true;
        }
        timeStayed++;
    }

    @Override
    public void abort() {
        restaurant.leaveRestaurant(guest);
    }
}
