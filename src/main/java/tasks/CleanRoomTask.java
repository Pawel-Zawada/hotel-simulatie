package tasks;

import simulation.Hotel;
import simulation.HotelElement;
import simulation.Room;

/**
 * @author Johan Geluk
 * A task that can be picked up by a cleaner, to clean a room.
 */
public class CleanRoomTask extends Task {
    private final int ticksRequiredToClean;
    private Room room;
    private int ticksDone = 0;
    private Hotel hotel;

    public CleanRoomTask(Hotel hotel, Room room) {
        this.ticksRequiredToClean = hotel.getConfiguration().getCleaningTime();
        this.room = room;
        this.hotel = hotel;
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

    @Override
    public void abort() {
        hotel.getTaskQueue().enQueue(new CleanRoomTask(hotel,room));
    }

    public HotelElement getRoom() {
        return room;
    }
}
