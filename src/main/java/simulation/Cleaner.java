package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import tasks.CleanRoomTask;
import tasks.Evacuate;
import tasks.Task;
import tasks.TaskRepository;

public class Cleaner extends Person implements HteObserver, Drawable {
    private final int cleanerNumber;
    private TaskRepository sharedTasks;

    public Cleaner(TaskRepository sharedTasks, Hotel hotel, int number){
        super(hotel);
        this.sharedTasks = sharedTasks;
        this.cleanerNumber = number;
        this.name = "Cleaner " + cleanerNumber;
    }

    public void observeHTE() {
        Task task = getPersonalTasks().getNextActiveTask();
        if(task == null){
            // No personal tasks remaining. Check for shared tasks.
            task = sharedTasks.getNextActiveTask();
            if(task == null){
                var checkInDesk = hotel.getCheckInDesk();
                if(currentRoom == checkInDesk){
                    // We have nothing to do and are already in the lobby.
                    // Just wait here now.
                    return;
                }else{
                    // We have nothing to do, let's go back to the lobby.
                    moveTo(hotel.getCheckInDesk());
                    task = getPersonalTasks().peek();
                }
            }else{
                System.out.println(this.name + " has picked up a new cleaning task.");
                CleanRoomTask cleanTask = (CleanRoomTask)sharedTasks.deQueue();
                moveTo(cleanTask.getRoom());
                getPersonalTasks().enQueue(cleanTask);
            }
        }
        task.executeStep();
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("cleaner", x, y);
    }


    public void evacuate(Hotel hotel){
        for(Task task:getPersonalTasks().getTaskQueue()){
            task.abort();
        }
        getPersonalTasks().getTaskQueue().clear();
        getPersonalTasks().enQueue(new Evacuate(this, hotel));
    }

}
