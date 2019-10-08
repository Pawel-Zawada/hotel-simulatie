package simulation;

public class Room implements HotelElement{
    private int width;
    private int height;
    private int x;
    private int y;
    private int classification;

    public Room(int width, int height,int x,int y, int classification ){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.classification = classification;
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

    public int getClassification() {
        return classification;
    }
}

