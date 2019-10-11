package simulation;

public class GuestFactory {
    public static Guest makeNewGuest(Hotel hotel, int guestNumber) {
        var checkInDesk = hotel.getCheckInDesk();

        var guest = new Guest(checkInDesk.getX(), checkInDesk.getY(), guestNumber, hotel);
        hotel.getHotelTimer().addObserver(guest);

        return guest;
    }
}
