package Rooms;

public class HotelRoomFactory {
    public HotelRoom createRoom(String classification){
        HotelRoom hotelRoom = null;

        if(classification.equals("1 star")){
            hotelRoom = new OneStar();
        } else if(classification.equals("2 star")){
            hotelRoom = new TwoStar();
        } else if(classification.equals("3 star")){
            hotelRoom = new ThreeStar();
        } else if(classification.equals("4 star")){
            hotelRoom = new FourStar();
        } else if(classification.equals("5 star")){
            hotelRoom = new FourStar();
        }
        return hotelRoom;
    }
}
