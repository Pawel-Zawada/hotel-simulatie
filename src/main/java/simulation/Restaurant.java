package simulation;

public class Restaurant implements HotelElement {
    private int width;
    private int height;
    private int x;
    private int y;
    private static int capacity;

    public Restaurant(int width, int height, int x, int y) {
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

    public int getCapacity() {
        return capacity;
    }

    public boolean isWalkable() {
        return false;
    }

    public static Guest[] guestsInRestaurant = new Guest[capacity - 1];

    public void enterRestaurant(Guest guest) {
        if (!restaurantFull(guestsInRestaurant)) {
            for (int i = 0; i < guestsInRestaurant.length - 1; i++) {
                guestsInRestaurant[i] = guest;
            }
        }
    }

    public boolean restaurantFull(Guest[] guestsInRestaurant) {
        if (guestsInRestaurant.length == capacity - 1) {
            return true;
        }
        return false;
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

}

