import java.util.Arrays;

public class TaskRepository {
    private Task[] taskQueue;
    private int queueSize;
    private int head = 0;
    private int rear = 0;
    private int numberOfTasks = 0;

    TaskRepository(int size) {
        this.queueSize = size;
        taskQueue = new Task[size];
        Arrays.fill(taskQueue, "-1");
    }

    public void enQueue(Task newTask) {
        if (numberOfTasks + 1 <= queueSize) {
            taskQueue[rear] = newTask;
            rear++;
            numberOfTasks++;
        }
    }

    public void deQueue() {
        if (numberOfTasks > 0) {
            taskQueue[head] = null;
            head++;
            numberOfTasks--;
        }
    }

    public void peek() {
        System.out.println(taskQueue[head]);
    }

    public void emergencyTask(Task newTask) {
        int i = 0;

        if (numberOfTasks == 0) {
            enQueue(newTask);
        } else {
            for (i = numberOfTasks - 1; i >= 0; i--) {
//                Task temp1 = new Task();
//                Task temp2 = new Task();
//                temp1 = taskQueue[head];
//                taskQueue[head] = newTask;
//                numberOfTasks++;
//                temp2 = taskQueue[i + 1];
//                taskQueue[i + 1] = temp1;
                taskQueue[i + 1] = taskQueue[i];
            }
            taskQueue[i + 1] = newTask;
            rear++;
            numberOfTasks++;
        }
    }
}