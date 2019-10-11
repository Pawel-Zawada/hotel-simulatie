package elevator;

/**
 * Represents a request for entering the elevator from a given floor.
 */
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
