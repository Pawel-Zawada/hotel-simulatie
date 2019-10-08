package simulation;

import json.JsonReader;

import java.util.ArrayList;

public class HotelFactory {

    public Hotel createHotel(String jsonFile){
        //load Hotel from json file
        JsonReader jsonReader = new JsonReader();
        ArrayList<HotelElement> hotelElements = jsonReader.loadHotelElements(jsonFile);
        return new Hotel(hotelElements, numberOfFloors(hotelElements),width(hotelElements));
    }

    //create lift and stairs and lobby

    private int numberOfFloors(ArrayList<HotelElement> hotelElements){
        int numberOfFloors = 1;
        for(HotelElement hotelElement: hotelElements){
            if(hotelElement.getY()+1 > numberOfFloors){
                numberOfFloors = hotelElement.getY();
            }
        }
        return numberOfFloors;
    }

    private int width(ArrayList<HotelElement> hotelElements){
        int width = 1;
        for(HotelElement hotelElement: hotelElements){
            if(hotelElement.getX()+2 > width){
                width = hotelElement.getX();
            }
        }
        return width;
    }
}



