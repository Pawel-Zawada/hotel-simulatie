package simulation;

public class GuestFactory {
    public static Guest makeNewGuest(Hotel hotel, int guestNumber) {
        var checkInDesk = hotel.getCheckInDesk();

        return new Guest(checkInDesk.getX(), checkInDesk.getY(), guestNumber);
    }
}
