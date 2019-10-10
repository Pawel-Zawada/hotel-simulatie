package simulation;

public interface Person {
    public int getX();
    public int getY();
    public HotelElement getCurrentRoom();

    void setX(int x);

    void setY(int y);

    void setCurrentRoom(HotelElement room);

    String getName();
}
