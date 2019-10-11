package tasks;

import simulation.Guest;
import simulation.Hotel;

/**
 * @author Marc Kemp
 */
public class CheckOutTask extends Task {
    private Hotel hotel;
    private Guest guest;
    private boolean isDone = false;

    public CheckOutTask(Hotel hotel, Guest guest){
        this.hotel = hotel;
        this.guest = guest;
    }

    @Override
    public boolean isDone() {

        return isDone;
    }

    @Override
    public void executeStep() {
        isDone = true;
        hotel.checkOut(guest);
    }
}
