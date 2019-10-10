package tasks;

import simulation.Guest;
import simulation.Hotel;

public class CheckOutTask extends Task {
    private Hotel hotel;
    private Guest guest;

    public CheckOutTask(Hotel hotel, Guest guest){
        this.hotel = hotel;
        this.guest = guest;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void executeStep() {
        hotel.checkOut(guest);
    }
}
