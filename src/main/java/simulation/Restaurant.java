package simulation;

import java.util.ArrayList;

public class Restaurant implements HotelElement{
    private int width;
    private int height;
    private int x;
    private int y;
    private int capacity;

    public Restaurant(int width, int height, int x, int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
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

    public int getCapacity(){
        return capacity;
    }

    public boolean isWalkable() {
        return false;
    }

    public static ArrayList<Guest> guestsInRestaurant = new ArrayList<>();
}

