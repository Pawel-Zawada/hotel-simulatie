package tasks;

import simulation.HotelElement;
import simulation.Person;

import java.util.List;

/**
 * @author Johan Geluk
 * Represents a task to move to a given (public) room in a hotel.
 */
public class MoveToTask extends Task {
    private final List<HotelElement> path;
    private final Person person;

    public MoveToTask(List<HotelElement> path, Person person) {
        this.path = path;
        this.person = person;
    }

    @Override
    public boolean isDone() {
        return path.size() == 0;
    }

    @Override
    public void executeStep() {
        var nextNode = path.remove(0);
        // Someone who is walking is not in a room
        person.setCurrentRoom(null);

        person.setX(nextNode.getX());
        person.setY(nextNode.getY());

        if(isDone()){
            System.out.println(person.getName() + " arrived at destination.");

            person.setCurrentRoom(nextNode);
        }
    }

    @Override
    public void abort() {

    }
}
