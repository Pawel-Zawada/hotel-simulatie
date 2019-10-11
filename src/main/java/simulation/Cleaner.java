package simulation;

import tasks.Task;
import tasks.TaskRepository;

public class Cleaner implements HteObserver, Person {
    public void observeHTE() {
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public HotelElement getCurrentRoom() {
        return null;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setCurrentRoom(HotelElement room) {

    }

    @Override
    public String getName() {
        return "CLEANER";
    }

    public static Task goToNextTask(TaskRepository taskRepository) {
        if (taskRepository.getSize() == 0) {
            return null;
        }
        return taskRepository.peek();
    }
}
