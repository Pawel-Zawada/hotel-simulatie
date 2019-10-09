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

        EventsAdapter adapter = new EventsAdapter(hotel);

        userInterface = new UserInterface(hotel);
    }
}
