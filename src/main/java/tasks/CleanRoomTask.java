package tasks;

import simulation.HotelElement;
import simulation.Room;

public class CleanRoomTask extends Task {
    private static final int ticksRequiredToClean = 2;

    private Room room;
    private int ticksDone = 0;

    public CleanRoomTask(Room room) {
        this.room = room;
    }

    @Override
    public boolean isDone() {
        return ticksDone >= ticksRequiredToClean;
    }

    @Override
    public void executeStep() {
        ticksDone++;
        if(isDone()){
            room.setDirty(false);
        }
    }

    public HotelElement getRoom() {
        return room;
    }
}
