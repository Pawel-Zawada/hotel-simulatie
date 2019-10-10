package simulation;

import drawing.DrawHelper;
import drawing.Drawable;

import java.util.ArrayList;

public class Hotel implements Drawable {
    ArrayList<HotelElement> hotelElements;
    ArrayList<Guest> guests = new ArrayList<>();


    private final int width;
    private final int height;

    public Hotel( ArrayList<HotelElement> hotelElements,int width,int height){
        this.hotelElements = hotelElements;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(DrawHelper drawHelper) {
        for(var element: hotelElements){
            element.draw(drawHelper);
        }
    }

    public void addGuest() {
        guests.add(GuestFactory.makeNewGeust());
    }

    public void killGuest(Guest guest) { // Muahahaha >:)
        guests.remove(guest);
    }

    public int numberOfGuests() {
        return guests.size();
    }
}
