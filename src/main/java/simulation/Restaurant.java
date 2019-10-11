package simulation;

import drawing.DrawHelper;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements HotelElement {
    private int width;
    private int height;
    private int x;
    private int y;
    private int capacity;

    private List<Guest> guests = new ArrayList<>();

    public Restaurant(int width, int height, int x, int y, int capacity) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.capacity = capacity;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCapacity() {
        return capacity;
    }

    public void enterRestaurant(Guest guest) {
        if (!isFull()) {
            guests.add(guest);
        }
    }

    public void leaveRestaurant(Guest guest){
        guests.remove(guest);
    }

    public boolean isFull() {
        return guests.size() >= capacity;
    }

    public int getNumberOfGuests() {
        return guests.size();
    }


    @Override
    public void draw(DrawHelper drawHelper) {
        for(int x = 0; x < this.width; x++){
            // Also support rooms higher than 1.
            for(int y = 0; y < this.height; y++){
                drawHelper.drawSprite("restaurant", this.x + x, this.y + y);
            }
        }
    }
}

