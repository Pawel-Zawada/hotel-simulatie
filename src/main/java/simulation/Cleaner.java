package simulation;

public class Cleaner implements IObserver, Person {
    public void observe(){
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public HotelElement getCurrentRoom() {
        return null;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setCurrentRoom(HotelElement room) {

    }

    @Override
    public String getName() {
        return "CLEANER";
    }
}
