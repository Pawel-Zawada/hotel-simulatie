package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import system.HotelTimer;
import tasks.CleanRoomTask;
import tasks.TaskRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Hotel implements Drawable {

    private ArrayList<HotelElement> hotelElements;
    private ArrayList<Guest> guests = new ArrayList<>();
    private ArrayList<Cleaner> cleaners = new ArrayList<>();

    private final int width;
    private final int height;

    private boolean destroyed = false;

    private HotelTimer hotelTimer;
    private TaskRepository cleanerTasks = new TaskRepository();
    private HotelConfiguration configuration = new HotelConfiguration();

    private Set<Person> evacuatedPersons = new HashSet<>();

    public Hotel( ArrayList<HotelElement> hotelElements,int width,int height){
        this.hotelElements = hotelElements;
        this.width = width;
        this.height = height;


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
                System.out.println("Guest " + guestNumber + " received room number " + room.getRoomNumber() + " (" + requestedClassification + " stars).");
            }
            guest.assignRoom(room);
        }
    }

    public void killGuest(Guest guest) { // Muahahaha >:)
        checkOut(guest);
    }

    public int numberOfGuests() {
        return guests.size();
    }

    public ArrayList<HotelElement> getHotelElements(){
        return hotelElements;
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
            guest.checkOut();
        }else{
            System.out.println("He's dead Jim");
        }
    }

    public void checkOut(Guest guest){
        guest.getRoom().setOccupied(false);
        guest.getRoom().setDirty(true);
        cleanerTasks.enQueue(new CleanRoomTask(this, guest.getRoom()));
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
        return new TaskRepository();
    }

    public List<Restaurant> getRestaurants() {
        return hotelElements.stream()
                .filter(e -> e.getClass() == Restaurant.class)
                .map(e -> (Restaurant) e).collect(Collectors.toList());
    }

    public void handleCleaningEmergency(int guestNumber) {
        var guest = getGuestByNumber(guestNumber);
        if(guest == null){
            System.out.println("A dead guest can't have their room cleaned!");
            return;
        }
        var room = guest.getRoom();
        room.setDirty(true);
        cleanerTasks.addEmergencyTask(new CleanRoomTask(this, room));
    }

    public void handleDinnerRequest(int guestNumber, ArrayList<Restaurant> restaurantsToExclude){
        List<HotelElement> restaurantsToTry = getRestaurants().stream().filter(r->!restaurantsToExclude.contains(r)).collect(Collectors.toList());
        Guest guest = getGuestByNumber(guestNumber);
        if(guest!=null) {
            Restaurant restaurant = (Restaurant)guest.moveToClosest(restaurantsToTry); //find nearest
            guest.eatAtRestaurant(restaurant, restaurantsToExclude);
        }
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        for(var element: hotelElements){
            if(destroyed){
                drawHelper.drawSprite("ruin", element.getX(), element.getY());
            }else{
                element.draw(drawHelper);
            }
        }
        for(var guest: guests){
            guest.draw(drawHelper);
        }
        for(var cleaner: cleaners){
            cleaner.draw(drawHelper);
        }
    }

    public HotelConfiguration getConfiguration() {
        return configuration;
    }

    public void handleGoToFitness(int guestNumber, int duration) {
        var guest = getGuestByNumber(guestNumber);
        if(guest == null){
            System.out.println("A dead guest can't go to the gym!");
            return;
        }
        var gym = getGym();
        guest.moveTo(gym);
        guest.workOut(duration);
    }

    private Gym getGym() {
        return hotelElements.stream()
                .filter(e -> e.getClass() == Gym.class)
                .map(e -> (Gym) e).findFirst().get();
    }

    public void handleGoToCinema(int guestNumber) {
        var guest = getGuestByNumber(guestNumber);
        if(guest == null){
            System.out.println("A dead guest can't go to the cinema!");
            return;
        }
        List<HotelElement> cinemas = getCinemas();
        Cinema closest = (Cinema) guest.moveToClosest(cinemas);
        guest.watchMovie(closest);
    }

    private List<HotelElement> getCinemas() {
        return hotelElements.stream()
                .filter(e -> e.getClass() == Cinema.class)
                .collect(Collectors.toList());
    }

    public void handleGodzilla() {
        while(guests.size() > 0){
            killGuest(guests.get(0));
        }
        destroyed = true;
    }

    public void handleStartCinema(int cinemaId) {
        ;
    }

    public void handleEvacuation(){
        ArrayList<Person> evacuees = new ArrayList<>();
        for(Guest guest:guests){
            evacuees.add(guest);
        }
        for (Cleaner cleaner: cleaners){
            evacuees.add(cleaner);
        }
        for (Person evacuee: evacuees){
            evacuee.moveTo(getCheckInDesk());
        }
        for (Person evacuee: evacuees){
            evacuee.evacuate(this);
        }
    }

    public void addEvacuatedPerson(Person person){
        evacuatedPersons.add(person);

    }

    public boolean evacuationComplete(){
        if (evacuatedPersons.size() == (guests.size() + cleaners.size())){
            return true;
        }
        else return false;
    }
}
