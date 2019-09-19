import java.util.Queue;

public class TaskRepository {

    Queue<Task> taskQueue;

    public void enQueue(Task task){
        this.taskQueue.add(task);
    }

    public void deQueue(){
        taskQueue.remove();
    }

    public void emergencyTask(){
        taskQueue.peek();
    }


}
