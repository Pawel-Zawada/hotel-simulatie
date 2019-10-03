package elevator;

import java.util.*;

public class Elevator {

    private final int floorCount;

    // The elevator always starts out on the bottom floor.
    private int currentFloor = 0;
    // Since it starts at the bottom, the initial preferential direction is up.
    private ElevatorDirection currentDirection = ElevatorDirection.UP;
    // -1 shall be considered to be no destination.
    private int currentDestination = -1;

    private Queue<ElevatorCall> elevatorCalls = new ArrayDeque<>();
    private List<Integer> floorRequests = new ArrayList<>();

    public Elevator(int floorCount){
        this(floorCount, 0, ElevatorDirection.NONE);
    }

    public Elevator(int floorCount, int currentFloor, ElevatorDirection currentDirection){
        this.floorCount = floorCount;
        this.currentFloor = currentFloor;
        this.currentDirection = currentDirection;
    }

    /**
     * Requests for the elevator to go up from the given floor.
     * @param currentFloor The floor from which the elevator was called.
     * @throws ElevatorException Thrown when an invalid floor is given.
     */
    public void requestUp(int currentFloor) throws ElevatorException{
        if(currentFloor >= floorCount - 1){
            throw new ElevatorException("Can't go up from the top floor.");
        }
        elevatorCalls.add(new ElevatorCall(currentFloor, ElevatorDirection.UP));
    }

    /**
     * Requests for the elevator to go down from the given floor.
     * @param currentFloor The floor from which the elevator was called.
     * @throws ElevatorException Thrown when an invalid floor is given.
     */
    public void requestDown(int currentFloor) throws ElevatorException{
        if(currentFloor  <= 0){
            throw new ElevatorException("Can't go down from the bottom floor.");
        }
        elevatorCalls.add(new ElevatorCall(currentFloor, ElevatorDirection.DOWN));
    }

    /**
     * Requests for the elevator to move to a given floor.
     * @param requestedFloor The floor that the elevator should move to.
     * @throws ElevatorException Thrown when you request the elevator to move to a nonexistent floor.
     */
    public void requestFloor(int requestedFloor) throws ElevatorException {
        if(requestedFloor < 0 || requestedFloor >= floorCount){
            throw new ElevatorException("Can't request a floor that does not exist.");
        }
        floorRequests.add(requestedFloor);
    }

    /**
     * @return The current floor of the elevator.
     */
    public int getCurrentFloor(){
        return this.currentFloor;
    }

    /**
     * Simulates a single step (HTE) of the elevator's movement pattern.
     */
    public void step(){
        if(currentDestination == -1){
            pickNextDestination();
        }

        moveTowardsDestination();

        if(floorRequests.stream().anyMatch(r -> r == currentFloor)){
            // We have arrived at an intermediate floor that people want to go to.
            // In the future, we may want to notify them here as well.
            floorRequests.removeIf(r -> r == currentFloor);
        }

        if(elevatorCalls.stream().anyMatch(c -> c.getFloor() == currentFloor)){
            // We can pick some waiting people up on this floor.
            // In the future, we may want to notify them of this.
            elevatorCalls.removeIf(c -> c.getFloor() == currentFloor);
        }
    }

    private void pickNextDestination() {
        if(floorRequests.size() > 0){
            // Someone inside the elevator wants to go somewhere.
            // We should handle this before any outside elevator calls.
            currentDestination = floorRequests.remove(0);
            currentDirection = currentDestination > currentFloor ? ElevatorDirection.UP : ElevatorDirection.DOWN;

        }else if(elevatorCalls.size() > 0){
            // Someone outside the elevator has called it.
            // If we have no other destinations, let's pick them up.
            ElevatorCall nextDestination = elevatorCalls.remove();
            currentDestination = nextDestination.getFloor();
        }

    }

    private void moveTowardsDestination() {
        // We have somewhere to go.
        if(currentDestination > currentFloor){
            currentFloor++;
        }else if(currentDestination < currentFloor){
            currentFloor--;
        }else{
            // We have arrived. Reset our destination and preferential direction.
            currentDestination = -1;
            currentDirection = ElevatorDirection.NONE;
        }
    }
}
