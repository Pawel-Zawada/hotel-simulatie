package simulation;

import tasks.Task;
import tasks.TaskRepository;

public class Cleaner implements IObserver {
    public void observe() {
    }

    public static Task goToNextTask(TaskRepository taskRepository) {
        if (taskRepository.getSize() == 0) {
            return null;
        }
        return taskRepository.peek();
    }

    public boolean waitForTask(TaskRepository taskRepository) {
        if (taskRepository.isEmpty()) {
            return true;
        }
        return false;
    }
}
