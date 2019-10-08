package simulation;

public class Floor {
    private int width;
    private int floorNumber;
    private HotelElement[] hotelElements;


    public Floor(int width, int floorNumber){
        this.width = width;
        this.floorNumber = floorNumber;
    }

    public HotelElement[] getHotelElements() {
        return hotelElements;
    }

    public int getWidth() {
        return width;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
