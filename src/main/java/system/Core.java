package system;

import drawing.UserInterface;
import pathfinding.Graph;
import simulation.*;

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

        userInterface = new UserInterface(hotel);
    }
}
