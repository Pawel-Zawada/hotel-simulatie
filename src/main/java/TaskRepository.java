import java.util.ArrayList;

public class TaskRepository {
    private ArrayList<Task> taskQueue;

    public TaskRepository() {
        this.taskQueue = new ArrayList<>();
    }

    public int getSize() {
        return taskQueue.size();
    }

    public void enQueue(Task newTask) {
        this.taskQueue.add(newTask);
    }

    public void deQueue() {
        if (taskQueue.size() > 0) {
            this.taskQueue.remove(0);
        } else {
            System.out.println("No Tasks to dequeue");
        }
    }

    public Task peek(){
        return taskQueue.get(0);
    }

    public void addEmergencyTask(Task emergencyTask){
        if(taskQueue.size() == 0){
            enQueue(emergencyTask);
        } else {
            taskQueue.add(0,emergencyTask);
        }
    }

    public void element(){
        taskQueue.forEach(task -> System.out.println(task.getTaskNumber()));
    }


}

