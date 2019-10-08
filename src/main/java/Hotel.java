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

    public static ArrayList<Guest> guests = new ArrayList<>();

    public void newGuest() {
        guests.add(GuestFactory.makeNewGuest());
    }

    public void deadGuest(Guest guest) {
        guests.remove(guest);
    }

    public static int numberOfGuests() {
        return guests.size();
    }
}
