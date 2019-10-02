import java.util.ArrayList;

public class TaskRepository {
    private ArrayList<Task> taskQueue;
    private int head = 0;
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

    public void deQueue() {
        try {
            if (taskQueue.size() > 0) {
                this.taskQueue.remove(head);
            } else {
                isEmpty();
            }
        } catch (Exception e) {
            System.out.println("No tasks to dequeue");
        }
    }

    public Task peek() {
        return taskQueue.get(head);
    }

    public void addEmergencyTask(Task emergencyTask) {
        if (getSize() == 0) {
            enQueue(emergencyTask);
        } else {
            taskQueue.add(head, emergencyTask);
        }
    }

    public void elements() {
        taskQueue.forEach(task -> System.out.println(task.getTaskNumber()));
    }


}

