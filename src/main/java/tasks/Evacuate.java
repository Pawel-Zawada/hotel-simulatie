package tasks;

import simulation.Hotel;
import simulation.Person;

/**
 * @author Marc Kemp
 * Executed by everyone in the hotel when an emergency occurs.
 */
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
