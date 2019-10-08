import simulation.Floor;
import simulation.Guest;
import simulation.GuestFactory;

import java.util.ArrayList;

public class Hotel {
    private Floor[] floors;

    public Hotel(int numberOfFloors) {
        this.floors = new Floor[numberOfFloors];
    }

    public Floor[] getFloors() {
        return floors;
    }

    ArrayList<Guest> guests = new ArrayList<>();

    public void newGuest() {
        guests.add(GuestFactory.makeNewGeust());
    }

    public void deadGuest(Guest guest) {
        guests.remove(guest);
    }

    public int numberOfGuests() {
        return guests.size();
    }
}
