import system.Core;
import tasks.Task;
import tasks.TaskRepository;

public class Entry {
    public static void main(String[] args) {
        new Core();

        TaskRepository taskRepository = new TaskRepository();
        taskRepository.enQueue(new Task(10));
        taskRepository.enQueue(new Task(100));

        taskRepository.addEmergencyTask(new Task(300));
        taskRepository.addEmergencyTask(new Task(500));
        taskRepository.elements();

        try {
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
            taskRepository.deQueue();
        } catch (Exception e) {
        }

    }
}
