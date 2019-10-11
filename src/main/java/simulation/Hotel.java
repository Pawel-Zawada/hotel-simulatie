package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import pathfinding.Graph;
import system.HotelTimer;
import tasks.TaskRepository;

import java.util.ArrayList;
import java.util.List;
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


    @Override
    public void draw(DrawHelper drawHelper) {
        for(var element: hotelElements){
            element.draw(drawHelper);
        }
        for(var guest: guests){
            guest.draw(drawHelper);
        }
    }

    public void newGuest(int guestNumber, int requestedClassification) {
        var guest = GuestFactory.makeNewGuest(this, guestNumber);

        var destinations = hotelElements.stream()
                .filter(e->e.getClass()==Room.class)
                .map(e -> (Room) e)
                .filter(r -> !r.isOccupied() && !r.isDirty() && r.getClassification() >= requestedClassification)
                .sorted(new RoomComparator())
                .collect(Collectors.toList());

        if(destinations.size() == 0){
            // No rooms found. Guest leaves the building.
            System.out.println("Guest " + guestNumber + " could not get a room.");
        }else{
            guests.add(guest);
            hotelTimer.addObserver(guest);
            var room = destinations.get(0);

            room.setOccupied(true);
            guest.setRoom(room);
            if(room.getClassification() > requestedClassification){
                System.out.println("Guest " + guestNumber + " received a free upgrade (" + requestedClassification + " -> " + room.getClassification() + " stars), room number " + room.getRoomNumber());
            }else{
                System.out.println("Guest " + guestNumber + " received room number " + room.getRoomNumber());
            }
            guest.moveTo(graph, destinations.get(0));
        }
    }

    public void killGuest(Guest guest) { // Muahahaha >:)
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

    public void requestCheckOut(int guestNumber) {
        var guest = getByNumber(guestNumber);
        guest.moveTo(graph, getCheckInDesk());
        guest.checkOut();
    }

    public void checkOut(Guest guest){
        guest.getRoom().setOccupied(false);
        guest.getRoom().setDirty(true);
        guests.remove(guest);

    }

    public Guest getByNumber(int guestNumber){
        for(Guest guest:guests){
            if (guest.getGuestNumber() == guestNumber){
                return guest;
            }
        }
        return null;
    }

    public TaskRepository getTaskQueue() {
        return new TaskRepository();
    }

    public List<Restaurant> getRestaurants() {
        return hotelElements.stream()
                .filter(e -> e.getClass() == Restaurant.class)
                .map(e -> (Restaurant) e).collect(Collectors.toList());
    }
}
