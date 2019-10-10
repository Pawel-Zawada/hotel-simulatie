package simulation;

public class GuestFactory {
    public static Guest makeNewGuest(Hotel hotel) {
        var checkInDesk = hotel.getCheckInDesk();

        return new Guest(checkInDesk.getX(), checkInDesk.getY());
    }
}
