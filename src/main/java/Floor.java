public class Floor {
    private int width;
    private int height;
    private int floorNumber;
    private HotelElement[] hotelElements;


    public Floor(int width, int height, int floorNumber){
        this.width = width;
        this.height = height;
        this.floorNumber = floorNumber;
        hotelElements = new HotelElement[height*width];

        //ff tijdelijke floor.
        int i = 0;
        for(int y =1; y<=height; y++){
            for(int x = 1;x <= width; x++){
                hotelElements[i] = new Hallway(1,1,x,y);
                if(i == 10){hotelElements[i] = new Elevator(1,1,x,y);}
                if(i == 15){hotelElements[i] = new Stairs(1,1,x,y);}
                i++;
            }
        }
    }

    public HotelElement[] getHotelElements() {
        return hotelElements;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
