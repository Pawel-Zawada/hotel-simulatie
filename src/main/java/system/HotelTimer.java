package system;

import simulation.IObserver;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HotelTimer {
    public static HotelTimer hotelTimer;

    private Timer timer = new Timer();
    private ArrayList<IObserver> observers;
    // Initial HTE value.
    private int HTE = 5000; // 1000 = 1 sec

    HotelTimer(ArrayList<IObserver> observers) {
        this.observers = observers; // Store observers that are to be notified after every tick.
        scheduleTimer();
    }

    // Can be used for statistics?
    public ArrayList<IObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<IObserver> observers) {
        this.observers = observers;
    }

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(int index) {
        this.observers.remove(index); // TODO: Assign an ID for each guest and cleaner, so they can be easily removed by ID, instead by index.
    }

    /**
     * Schedule new task with the current HTE.
     */
    private void scheduleTimer() {
        timer.schedule(new HotelTimerTask(observers), 0, HTE);
    }

    /**
     * Resets the timer and schedules new task with the current HTE.
     */
    private void rescheduleTimer() {
        timer.cancel(); // Clear current timer.
        timer = new Timer(); // Build fresh timer.
        scheduleTimer(); // Schedule task in newly built timer.
    }

    public int getHTE() {
        return HTE;
    }

    /**
     * Change the timer interval of the HTE ticks.
     *
     * @param HTE How many seconds a tick takes.
     */
    public void setHTE(int HTE) {
        this.HTE = HTE;

        rescheduleTimer(); // Reschedule timer with the new HTE value.
    }

    /**
     * TimerTask responsible for performing action according to every fulfilled tick.
     * Pass this into the `.schedule()` method as the task parameter.
     */
    private static class HotelTimerTask extends TimerTask {
        private ArrayList<IObserver> observers;

        HotelTimerTask(ArrayList<IObserver> observers) {
            this.observers = observers;
        }

        @Override
        public void run() {
            notifyObservers();
        }

        private void notifyObservers() {
            for (IObserver observer : observers) {
                observer.observe();
            }
        }
    }
}
