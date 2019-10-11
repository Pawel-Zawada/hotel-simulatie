package simulation;

public class HotelConfiguration {

    private int elevatorWeight = 12;
    private int stairsWeight = 4;
    
    private int dinnerTime = 5;
    private int cleaningTime = 2;

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

    public int getCleaningTime() {
        return this.cleaningTime;
    }
    
    public void setCleaningTime(int cleaningTime) {
        this.cleaningTime = cleaningTime;
    }
}
