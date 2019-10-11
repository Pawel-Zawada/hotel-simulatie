package tasks;

import simulation.Hotel;
import simulation.HotelElement;
import simulation.Room;

public class CleanRoomTask extends Task {
    private final int ticksRequiredToClean;
    private Room room;
    private int ticksDone = 0;

    public CleanRoomTask(Hotel hotel, Room room) {
        this.ticksRequiredToClean = hotel.getConfiguration().getCleaningTime();
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
