package tasks;

import simulation.Cinema;
import simulation.Hotel;
import tasks.Task;

public class WatchMovieTask extends Task {

    private Hotel hotel;
    private Cinema cinema;

    public WatchMovieTask(Hotel hotel, Cinema cinema){
        this.hotel = hotel;
        this.cinema = cinema;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void executeStep() {

    }

    @Override
    public void abort() {

    }
}
