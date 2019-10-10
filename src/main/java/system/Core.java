package system;

import drawing.UserInterface;
import events.EventsAdapter;
import simulation.HotelFactory;

public class Core {
    public static UserInterface userInterface;

    public Core() {
        var factory = new HotelFactory();
        var hotel = factory.createHotel("assets/hotels/hotel_2.layout");
        EventsAdapter adapter = new EventsAdapter(hotel);
        userInterface = new UserInterface(hotel);
    }
}
