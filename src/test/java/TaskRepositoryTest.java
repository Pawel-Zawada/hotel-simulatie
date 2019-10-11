import org.junit.Test;
import tasks.Task;
import tasks.TaskRepository;

import static org.junit.Assert.*;

public class TaskRepositoryTest {
    @Test
    public void addEmergencyTaskToHeadOfQueueShouldReturn100(){
        TaskRepository taskRepository = new TaskRepository();
        taskRepository.enQueue(new TestTask(10));
        taskRepository.addEmergencyTask(new TestTask(100));

        assertEquals(100,((TestTask)taskRepository.peek()).getTaskNumber());
    }

    private class TestTask extends Task {
        private int number;

        public TestTask(int number) {
            super();
            this.number = number;
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public void executeStep() {

        }

        @Override
        public void abort() {

        }

        public int getTaskNumber() {
            return number;
        }
    }
}
