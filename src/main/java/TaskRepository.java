import java.util.ArrayList;

public class TaskRepository {
    private ArrayList<Task> taskQueue;
    private boolean isEmpty;

    public TaskRepository() {
        this.taskQueue = new ArrayList<>();
    }

    public int getSize() {
        return taskQueue.size();
    }

    public void enQueue(Task newTask) {
        this.taskQueue.add(newTask);
        isEmpty = false;
    }

    private boolean isEmpty() {
        return true;
    }

    public Task deQueue() throws Exception {
        if (taskQueue.size() > 0) {
            Task removed = this.taskQueue.remove(0);
            return removed;
        }else{
            throw new Exception("Cannot dequeue when task queue is empty.");
        }
    }

    public Task peek() {
        return taskQueue.get(0);
    }

    public void addEmergencyTask(Task emergencyTask) {
        if (getSize() == 0) {
            enQueue(emergencyTask);
        } else {
            taskQueue.add(0, emergencyTask);
        }
    }

    public void elements() {
        taskQueue.forEach(task -> System.out.println(task.getTaskNumber()));
    }


}

