package system;

import drawing.UserInterface;
import events.EventsAdapter;
import simulation.Cleaner;
import simulation.HotelFactory;
import simulation.IObserver;

import java.util.ArrayList;

public class Core {
    public static UserInterface userInterface;

    public Core() {
        var factory = new HotelFactory();
        var hotel = factory.createHotel("assets/hotels/hotel_2.layout");

        // Temp guest and cleaner observers
        Cleaner tempCleaner = new Cleaner();
        hotel.newGuest();
        ArrayList<IObserver> tempObservers = new ArrayList<>();
        tempObservers.add(tempCleaner);
        EventsAdapter adapter = new EventsAdapter();

        userInterface = new UserInterface(hotel);
    }
}
