package tasks;

import simulation.Cinema;
import simulation.Hotel;

/**
 * @author Johan Geluk
 * Executed by a guest when watching a movie
 */
public class WatchMovieTask extends Task {

    private Hotel hotel;
    private Cinema cinema;

    private int duration;
    private int stepsDone;
    private boolean done;
    private boolean arrived = false;

    public WatchMovieTask(Hotel hotel, Cinema cinema){
        this.hotel = hotel;
        this.cinema = cinema;
        this.duration = hotel.getConfiguration().getCinemaDuration();
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void executeStep() {
        if(!arrived && cinema.hasStarted()){
            System.out.println("Guest arrived too late at the cinema.");
            done = true;
            return;
        }
        if(!arrived && !cinema.hasStarted()){
            // Arrived in time.
            arrived = true;
            return;
        }
        if(cinema.hasStarted()){
            // Cinema has started, wait for it to end.
            stepsDone++;
            if(stepsDone >= duration){
                done = true;
            }
        }
    }

    @Override
    public void abort() {

    }
}
