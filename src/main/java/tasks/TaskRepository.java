package tasks;

import java.util.ArrayList;

/**
 * @author Ümit Tokmak
 */
public class TaskRepository {
    private ArrayList<Task> taskQueue;
    private boolean isEmpty;

    public TaskRepository() {
        taskQueue = new ArrayList<>();
    }

    public ArrayList<Task> getTaskQueue() {
        return taskQueue;
    }

    public int getSize() {
        return taskQueue.size();
    }

    public void enQueue(Task newTask) {
        taskQueue.add(newTask);
    }

    public boolean isEmpty() {
        return taskQueue.size() == 0;
    }

    public Task deQueue() {
        if (taskQueue.size() > 0) {
            return taskQueue.remove(0);
        } else {
            throw new RuntimeException("Cannot dequeue when task queue is empty.");
        }
    }

    public Task peek() {
        if(taskQueue.size() == 0){
            return null;
        }
        return taskQueue.get(0);
    }

    public void addEmergencyTask(Task emergencyTask) {
        if (getSize() == 0) {
            enQueue(emergencyTask);
        } else {
            taskQueue.add(0, emergencyTask);
        }
    }

    public Task getNextActiveTask(){
        Task task;
        do{
            task = peek();
            if(task == null){
                // No more tasks left.
                return null;
            }
            if(task.isDone()){
                deQueue();
            }
        }while (task.isDone());
        return task;
    }
}

