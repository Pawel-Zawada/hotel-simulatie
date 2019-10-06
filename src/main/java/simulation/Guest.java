package simulation;

public class Guest implements IObserver {
    public void observe() {
        System.out.println("I have been notified");
    }
}
