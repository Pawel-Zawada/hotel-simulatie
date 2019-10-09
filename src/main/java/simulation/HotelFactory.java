package simulation;

import json.JsonReader;

import java.util.ArrayList;

public class HotelFactory {

    public Hotel createHotel(String jsonFile){
        //load Hotel from json file
        JsonReader jsonReader = new JsonReader();
        ArrayList<HotelElement> hotelElements = jsonReader.loadHotelElements(jsonFile);

        var numberOfFloors = numberOfFloors(hotelElements);
        var width = width(hotelElements);

        hotelElements.add(new Elevator(1, numberOfFloors, 0, 0));

        for(int i = 0; i<numberOfFloors; i++) {
            hotelElements.add(new Stairs(1, 1, width - 1, i));
        }

        hotelElements.add(new CheckInDesk(1, 1, 1, 0));
        hotelElements.add(new Lobby(width - 3, 1, 2,0 ));

        return new Hotel(hotelElements, width, numberOfFloors);
    }

    //create lift and stairs and lobby

    private int numberOfFloors(ArrayList<HotelElement> hotelElements){
        int numberOfFloors = 1;
        for(HotelElement hotelElement: hotelElements){
            int y = hotelElement.getY();
            int yMax = y+hotelElement.getHeight();
            if(yMax > numberOfFloors){
                numberOfFloors = yMax;
            }
        }
        return numberOfFloors;
    }

    private int width(ArrayList<HotelElement> hotelElements){
        int width = 1;
        for(HotelElement hotelElement: hotelElements){
            int x = hotelElement.getX();
            int xMax = x + hotelElement.getWidth();
            if(xMax > width){
                width = xMax;
            }
        }
        // Add one to account for the stairs all the way at the right.
        return width + 1;
    }
}



