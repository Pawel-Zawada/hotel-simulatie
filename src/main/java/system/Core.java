package system;

import drawing.UserInterface;
import simulation.Cleaner;
import simulation.Floor;
import simulation.Guest;
import simulation.IObserver;

import java.util.ArrayList;

public class Core {
    public static HotelTimer hotelTimer;
    public static UserInterface userInterface;

    public Core() {
        Floor floorOne = new Floor(10, 10, 1);
        Floor floorTwo = new Floor(10, 10, 2);

        // Temp guest and cleaner observers
        Cleaner tempCleaner = new Cleaner();
        Guest tempGuest = new Guest();
        ArrayList<IObserver> tempObservers = new ArrayList<>();
        tempObservers.add(tempCleaner);
        tempObservers.add(tempGuest);

        hotelTimer = new HotelTimer(tempObservers);

        // System.out.println(pathFinding.doPathFinding()); // TODO: Uncomment or remove this line.

        System.out.println("No matter how many times I say hello, the world never answers.");

        userInterface = new UserInterface();
    }
}
