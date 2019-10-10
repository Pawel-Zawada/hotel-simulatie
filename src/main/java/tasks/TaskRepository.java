package tasks;

import java.util.ArrayList;

public class TaskRepository {
    private static ArrayList<Task> taskQueue;
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

    public boolean isEmpty() {
        if(taskQueue.size()==0) {
            return true;
        } else {
            return false;
        }

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

    public static ArrayList<Task> getTaskQueue(){
        return taskQueue;
    }

    public void elements() {
        taskQueue.forEach(task -> System.out.println(task.getTaskNumber()));
    }

}

