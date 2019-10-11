package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import tasks.CheckOutTask;
import tasks.EatAtRestaurantTask;
import tasks.WorkOutTask;
import tasks.Task;

import java.util.ArrayList;

/**
 * @author Ümit Tokmak, Marc Kemp, Johan Geluk
 * Represents a guest in the hotel.
 */
public class Guest extends Person implements HteObserver, Drawable {
    private String name;
    private int guestNumber;
    private Room room;


    public Guest(int x, int y, int guestNumber, Hotel hotel){
        super(hotel);
        this.name = "Guest: " + guestNumber;
        this.x = x;
        this.y = y;
        this.guestNumber = guestNumber;
    }

    public String getName(){
        return name;
    }

    public void observeHTE() {
        Task task = personalTasks.getNextActiveTask();
        // Task will be null if we have nothing to do.
        if(task != null){
            task.executeStep();
        }
        else if(getCurrentRoom() != room){
            this.moveTo(room);
        }
    }

    public void draw(DrawHelper drawHelper) {
        if(currentRoom == room){
            drawHelper.drawSprite("dnd", currentRoom.getX(), currentRoom.getY());
        }else{
            drawHelper.drawSprite("guest", this.x, this.y);
        }
        //drawHelper.drawString(name, "default", x, y);
    }

    public void assignRoom(Room room){
        room.setOccupied(true);
        this.room = room;
    }


    public int getGuestNumber() {
        return this.guestNumber;
    }

    public void checkOut(){
        personalTasks.enQueue(new CheckOutTask(hotel, this));
    }

    public void eatAtRestaurant(Restaurant restaurant, ArrayList<Restaurant> restaurantsToExclude){
        personalTasks.enQueue(new EatAtRestaurantTask(hotel,restaurant,restaurantsToExclude, this));
    }

    public Room getRoom() {
        return room;
    }

    public void workOut(int duration) {
        personalTasks.enQueue(new WorkOutTask(hotel, duration));
    }
}
