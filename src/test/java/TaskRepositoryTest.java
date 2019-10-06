import org.junit.Test;
import tasks.Task;
import tasks.TaskRepository;

import static org.junit.Assert.*;

public class TaskRepositoryTest {
    @Test
    public void addEmergencyTaskToHeadOfQueueShouldReturn100(){
        TaskRepository taskRepository = new TaskRepository();
        taskRepository.addEmergencyTask(new Task(100));

        assertEquals(100,taskRepository.peek().getTaskNumber());
    }

}
