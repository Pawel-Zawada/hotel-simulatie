package simulation;

public class HotelConfiguration {

    private int elevatorWeight = 12;
    private int stairsWeight = 4;
    
    private int dinnerTime = 5;
    private int cleaningTime = 2;
    private int cinemaDuration = 10;

    /**
     * Gets the pathfinding weight of the elevator.
     */
    public int getElevatorWeight() {
        return elevatorWeight;
    }

    /**
     * Sets the pathfinding weight of the elevator.
     */
    public void setElevatorWeight(int elevatorWeight) {
        this.elevatorWeight = elevatorWeight;
    }

    /**
     * Gets the pathfinding weight of the stairs.
     */
    public int getStairsWeight() {
        return stairsWeight;
    }

    /**
     * Sets the pathfinding weight of the stairs.
     */
    public void setStairsWeight(int stairsWeight) {
        this.stairsWeight = stairsWeight;
    }

    /**
     * Gets how long it takes to have dinner at the restaurant.
     */
    public int getDinnerTime() {
        return dinnerTime;
    }

    /**
     * Sets how long it takes to have dinner at the restaurant.
     */
    public void setDinnerTime(int dinnerTime) {
        this.dinnerTime = dinnerTime;
    }

    /**
     * Gets how long it takes to clean a room.
     */
    public int getCleaningTime() {
        return this.cleaningTime;
    }

    /**
     * Sets how long it takes to clean a room.
     */
    public void setCleaningTime(int cleaningTime) {
        this.cleaningTime = cleaningTime;
    }

    /**
     * Gets how long it takes to play a movie.
     */
    public int getCinemaDuration() {
        return this.cinemaDuration;
    }
}
