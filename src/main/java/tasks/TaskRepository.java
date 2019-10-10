package tasks;

import java.util.ArrayList;

public class TaskRepository {
    private static ArrayList<Task> taskQueue;

    public TaskRepository() {
        taskQueue = new ArrayList<>();
    }

    public static ArrayList<Task> getTaskQueue() {
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

    public void deQueue() throws Exception {
        if (taskQueue.size() > 0) {
            taskQueue.remove(0);
        } else {
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

