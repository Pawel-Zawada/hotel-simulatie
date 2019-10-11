package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import pathfinding.Graph;
import system.HotelTimer;
import tasks.CleanRoomTask;
import tasks.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel implements Drawable {

    private ArrayList<HotelElement> hotelElements;
    private ArrayList<Guest> guests = new ArrayList<>();
    private ArrayList<Cleaner> cleaners = new ArrayList<>();

    private final int width;
    private final int height;
    private int elevatorWeight;
    private int stairsWeight;

    private HotelTimer hotelTimer;
    private TaskRepository cleanerTasks = new TaskRepository();

    public Hotel( ArrayList<HotelElement> hotelElements,int width,int height){
        this.hotelElements = hotelElements;
        this.width = width;
        this.height = height;

        this.elevatorWeight = 12;
        this.stairsWeight = 4;

        hotelTimer = new HotelTimer();

        this.cleaners.add(CleanerFactory.createCleaner(this, cleanerTasks));
        this.cleaners.add(CleanerFactory.createCleaner(this, cleanerTasks));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void newGuest(int guestNumber, int requestedClassification) {

        var destinations = hotelElements.stream()
                .filter(e->e.getClass()==Room.class)
                .map(e -> (Room) e)
                .filter(r -> !r.isOccupied() && !r.isDirty() && r.getClassification() >= requestedClassification)
                .sorted(new RoomComparator())
                .collect(Collectors.toList());

        if(destinations.size() == 0){
            // No rooms found. Guest leaves the building.
            System.out.println("Guest " + guestNumber + " could not get a room of " + requestedClassification + " stars.");
        }else{
            var guest = GuestFactory.makeNewGuest(this, guestNumber);
            guests.add(guest);
            var room = destinations.get(0);
            if(room.getClassification() > requestedClassification){
                System.out.println("Guest " + guestNumber + " received a free upgrade (" + requestedClassification + " -> " + room.getClassification() + " stars), room number " + room.getRoomNumber());
            }else{
                System.out.println("Guest " + guestNumber + " received room number " + room.getRoomNumber());
            }
            guest.assignRoom(room);
        }
    }

    public void killGuest(Guest guest) { // Muahahaha >:)
        guests.remove(guest);
        requestCheckOut(guest.getGuestNumber());
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
        var guest = getGuestByNumber(guestNumber);
        if(guests.contains(guest)) { //if it does not contain guest, that means he is dead :(
            guest.moveTo(getCheckInDesk());
        }
        guest.checkOut();
    }

    public void checkOut(Guest guest){
        guest.getRoom().setOccupied(false);
        guest.getRoom().setDirty(true);
        cleanerTasks.enQueue(new CleanRoomTask(guest.getRoom()));
        guests.remove(guest);
    }

    public Guest getGuestByNumber(int guestNumber){
        for(Guest guest:guests){
            if (guest.getGuestNumber() == guestNumber){
                return guest;
            }
        }
        return null;
    }

    public TaskRepository getTaskQueue() {
        return cleanerTasks;
    }

    public List<Restaurant> getRestaurants() {
        return hotelElements.stream()
                .filter(e -> e.getClass() == Restaurant.class)
                .map(e -> (Restaurant) e).collect(Collectors.toList());
    }

    public void handleCleaningEmergency(int guestNumber) {
        var room = getGuestByNumber(guestNumber).getRoom();
        room.setDirty(true);
        cleanerTasks.addEmergencyTask(new CleanRoomTask(room));
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        for(var element: hotelElements){
            element.draw(drawHelper);
        }
        for(var guest: guests){
            guest.draw(drawHelper);
        }
        for(var cleaner: cleaners){
            cleaner.draw(drawHelper);
        }
    }
}
