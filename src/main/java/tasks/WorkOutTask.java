package tasks;

import simulation.Hotel;

/**
 * @author Johan Geluk
 * Executed by a guest when working out.
 */
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

    @Override
    public void abort() {

    }
}
