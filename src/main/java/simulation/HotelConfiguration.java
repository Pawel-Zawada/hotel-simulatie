package simulation;

public class HotelConfiguration {

    private int elevatorWeight;
    private int stairsWeight;
    private int dinnerTime = 5;

    public int getElevatorWeight() {
        return elevatorWeight;
    }

    public void setElevatorWeight(int elevatorWeight) {
        this.elevatorWeight = elevatorWeight;
    }

    public int getStairsWeight() {
        return stairsWeight;
    }

    public void setStairsWeight(int stairsWeight) {
        this.stairsWeight = stairsWeight;
    }

    public int getDinnerTime() {
        return dinnerTime;
    }

    public void setDinnerTime(int dinnerTime) {
        this.dinnerTime = dinnerTime;
    }
}
