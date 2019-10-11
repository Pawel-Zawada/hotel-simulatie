package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import tasks.CleanRoomTask;
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
        Task task = personalTasks.getNextActiveTask();
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
                    task = personalTasks.peek();
                }
            }else{
                System.out.println(this.name + " has picked up a new cleaning task.");
                CleanRoomTask cleanTask = (CleanRoomTask)sharedTasks.deQueue();
                moveTo(cleanTask.getRoom());
                personalTasks.enQueue(cleanTask);
            }
        }
        task.executeStep();
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("cleaner", x, y);
    }
}
