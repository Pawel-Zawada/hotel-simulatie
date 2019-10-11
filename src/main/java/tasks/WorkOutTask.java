package tasks;

import simulation.Hotel;

public class WorkOutTask extends Task{
    private final int ticksRequiredForGym;
    private int ticksDone = 0;

    public WorkOutTask(Hotel hotel, int duration){
        this.ticksRequiredForGym = duration;
    }

    @Override
    public boolean isDone() {
        return this.ticksDone >= this.ticksRequiredForGym;
    }

    @Override
    public void executeStep() {
        ticksDone++;
    }
}
