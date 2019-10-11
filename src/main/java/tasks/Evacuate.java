package tasks;

import simulation.CheckInDesk;
import simulation.Guest;
import simulation.Hotel;
import simulation.Person;

public class Evacuate extends Task {

    private final Person person;
    private final Hotel hotel;
    private boolean isDone = false;

    public Evacuate(Person person, Hotel hotel){

        this.person = person;
        this.hotel = hotel;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void executeStep() {
        hotel.addEvacuatedPerson(person);
        if (hotel.evacuationComplete()) {
            isDone = true;
        }
    }

    @Override
    public void abort() {

    }
}
