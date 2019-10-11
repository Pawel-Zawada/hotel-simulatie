package system;

import simulation.HteObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Marc Kemp
 */
public class HotelTimer {
    private Timer timer = new Timer();
    private List<HteObserver> observers = new ArrayList<>();
    private List<HteObserver> afterEventObservers = new ArrayList<>();
    // Initial HTE value.
    private int HTE = 1000; // 1000 = 1 sec
    private int HTEIteration = 0; // Which iteration the current tick is.

    public HotelTimer() {
        scheduleTimer();
    }

    // Can be used for statistics?
    public List<HteObserver> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<HteObserver> observers) {
        this.observers = observers;
    }

    public void addObserver(HteObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(int index) {
        this.observers.remove(index); // TODO: Assign an ID for each guest and cleaner, so they can be easily removed by ID, instead by index.
    }

    /**
     * Schedule new task with the current HTE.
     */
    private void scheduleTimer() {
        timer.schedule(new HotelTimerTask(observers, afterEventObservers), 0, HTE);
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

    public int getHTEIteration() {
        return HTEIteration;
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

    public void addAfterEventObserver(HteObserver observer) {
        this.afterEventObservers.add(observer);
    }

    /**
     * TimerTask responsible for performing action according to every fulfilled tick.
     * Pass this into the `.schedule()` method as the task parameter.
     */
    private class HotelTimerTask extends TimerTask {
        private List<HteObserver> observers;
        private List<HteObserver> afterEventObservers;

        HotelTimerTask(List<HteObserver> observers, List<HteObserver> afterEventObservers) {
            this.observers = observers;
            this.afterEventObservers = afterEventObservers;
        }

        @Override
        public void run() {
            notifyObservers();
        }

        private void notifyObservers() {
            HotelTimer.this.HTEIteration++; // Update the tick iteration
            for (HteObserver observer : observers) {
                observer.observeHTE();
            }
            for (HteObserver observer : afterEventObservers) {
                observer.observeHTE();
            }
        }
    }
}
