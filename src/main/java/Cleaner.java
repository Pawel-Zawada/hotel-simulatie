import java.util.Queue;

public class Cleaner {
    private Queue<Task> tasks;

    public Cleaner(Queue<Task> tasks) {
        this.tasks = tasks;
    }

    public void enQueue(Task task){
        tasks.add(task);
    }

    public void deQueueu(){
        tasks.remove();
    }

    public void emergencyTask(Task task){
        tasks.peek();
    }
}
