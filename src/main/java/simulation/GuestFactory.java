package simulation;

public class GuestFactory {
    public static Guest makeNewGeust(Hotel hotel) {
        var checkInDesk = hotel.getCheckInDesk();

        return new Guest(checkInDesk.getX(), checkInDesk.getY());
    }
}
