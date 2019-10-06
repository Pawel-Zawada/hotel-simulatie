package simulation;

public class HotelFactory {
    public Hotel createHotel(int numberOfFloors){
        return new Hotel(numberOfFloors);
    }
}



