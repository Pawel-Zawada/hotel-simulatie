package simulation;

import drawing.DrawHelper;

public class Restaurant implements HotelElement {
    private int width;
    private int height;
    private int x;
    private int y;
    private int capacity;
    private int restaurantID;

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

    public int getRestaurantID(){
        return restaurantID;
    }

    public boolean isWalkable() {
        return false;
    }

    public Guest[] guestsInRestaurant = new Guest[capacity - 1];

    public void enterRestaurant(Guest guest) {
        if (!restaurantFull(guestsInRestaurant)) {
            for (int i = 0; i < guestsInRestaurant.length - 1; i++) {
                guestsInRestaurant[i] = guest;
            }
        }
    }

    public boolean restaurantFull(Guest[] guestsInRestaurant) {
        return guestsInRestaurant.length == capacity - 1;
    }

    public int restaurantGuest(Guest[] guestsInRestaurant) {
        int numberOfGuestsInRestaurant;
        if (restaurantFull(guestsInRestaurant)) {
            return capacity;
        } else {
            numberOfGuestsInRestaurant = guestsInRestaurant.length;
        }
        return numberOfGuestsInRestaurant;

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

