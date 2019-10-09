package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import json.JsonReader;
import pathfinding.Graph;
import system.HotelTimer;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Hotel implements Drawable {

    ArrayList<HotelElement> hotelElements;
    ArrayList<Guest> guests = new ArrayList<>();
    private final int width;
    private final int height;
    private int elevatorWeight;
    private int stairsWeight;

    private HotelTimer hotelTimer;
    private Graph graph;

    public Hotel( ArrayList<HotelElement> hotelElements,int width,int height){
        this.hotelElements = hotelElements;
        this.width = width;
        this.height = height;
        // temp, will be dynamic
        this.elevatorWeight = 12;
        this.stairsWeight = 4;
        graph = Graph.createGraph(this);
        hotelTimer = new HotelTimer();
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
        for(var guest: guests){
            guest.draw(drawHelper);
        }
    }

    public void newGuest() {
        var guest = GuestFactory.makeNewGeust(this);
        guests.add(guest);
        hotelTimer.addObserver(guest);

        var destinations = hotelElements.stream().filter(e->e.getClass()==Cinema.class).collect(Collectors.toList());

        guest.moveTo(graph, destinations.get(destinations.size()-1));
    }

    public void deadGuest(Guest guest) {
        guests.remove(guest);
    }

    public int numberOfGuests() {
        return guests.size();
    }

    public ArrayList<HotelElement> getHotelElements(){
        return hotelElements;
    }

    public int getElevatorWeight() {
        return elevatorWeight;
    }

    public void setElevatorWeight(int elevatorWeight) {
        this.elevatorWeight = elevatorWeight;
    }

    public int getStairsWeight() {
        return stairsWeight;
    }

    public void setStairsWeight(int stairsWeight) {
        this.stairsWeight = stairsWeight;
    }

    public CheckInDesk getCheckInDesk() {
        return (CheckInDesk)hotelElements.stream().filter(e->e.getClass()==CheckInDesk.class).findFirst().get();
    }

    public HotelTimer getHotelTimer() {
        return hotelTimer;
    }
}
