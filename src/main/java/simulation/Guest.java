package simulation;

public class Guest implements IObserver {
    private String name;
    private static int guestID = 00;

    public Guest(){
        guestID++;
        this.name = "Guest: " + guestID;
    }

    public String getName(){
        return name;
    }
    public int getGuestID() {
        return guestID;
    }

    public void observe() {
        System.out.println("I have been notified");
    }
}
