package simulation;

public class Restaurant implements HotelElement{
    private int width;
    private int height;
    private int x;
    private int y;
    private int capacity;

    public Restaurant(int width, int height, int x, int y, int capacity){
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

    public boolean isWalkable() {
        return false;
    }
}

