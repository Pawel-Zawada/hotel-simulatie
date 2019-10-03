public class Entry {
    public static void main(String[] args){



        Floor floorOne = new Floor(10 ,10,1);
        Floor floorTwo = new Floor(10 ,10,2);
     //   PathFinding pathFinding = new PathFinding(floorOne,floorTwo,floorOne.getHotelElements()[1],floorTwo.getHotelElements()[10]);



        //System.out.println(pathFinding.doPathFinding());

        System.out.println("No matter how many times I say hello, the world never answers.");

        TaskRepository taskRepository = new TaskRepository();
        taskRepository.enQueue(new Task(1));
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


        UserInterface menu = new UserInterface();
    }
}
