package simulation;

public class Hallway implements HotelElement{
    private int width;
    private int height;
    private int x;
    private int y;

    public Hallway(int width, int height, int x, int y){
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

    public boolean isWalkable() {
        return false;
    }
}

