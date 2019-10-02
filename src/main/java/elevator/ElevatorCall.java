package elevator;

public class ElevatorCall {
    private final int floor;
    private final ElevatorDirection direction;

    public ElevatorCall(int floor, ElevatorDirection direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public ElevatorDirection getDirection() {
        return direction;
    }
}
