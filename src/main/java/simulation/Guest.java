package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import pathfinding.Graph;
import pathfinding.Node;
import pathfinding.PathFinding;
import tasks.MoveToTask;
import tasks.Task;
import tasks.TaskRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Guest implements IObserver, Person, Drawable {
    private String name;
    private int x;
    private int y;
    private int guestNumber;
    private HotelElement currentRoom;

    private TaskRepository personalTasks = new TaskRepository();

    public Guest(int x, int y, int guestNumber){
        this.name = "Guest: " + guestNumber;
        this.x = x;
        this.y = y;
        this.guestNumber = guestNumber;
    }

    public String getName(){
        return name;
    }

    public void observe() {
        Task task;
        do{
            task = personalTasks.peek();
            if(task == null){
                // No more tasks left.
                return;
            }
            if(task.isDone()){
                try {
                    personalTasks.deQueue();
                } catch (Exception e) {
                    // This should never happen (famous last words)
                    throw new RuntimeException("Attempted to dequeue from an already empty task list.");
                }
            }
        }while (task.isDone());

        // We now have an active task.
        task.executeStep();
    }

    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("guest", this.x, this.y);
    }

    public void moveTo(Graph graph, HotelElement destination){
        PathFinding pathFinding = new PathFinding();
        Node<HotelElement> startNode=null;
        for(Node<HotelElement> node:graph.getNodeList()){
            if (node.getElement().getX() == x && node.getElement().getY() == y){
                startNode = node;
            }
        }

        Node<HotelElement> endNode=null;
        for(Node<HotelElement> node:graph.getNodeList()){
            if (node.getElement() == destination){
                endNode = node;
            }
        }

        Node<HotelElement> destinationNode = pathFinding.doPathFinding(startNode,endNode);
        List<HotelElement> path = new ArrayList<>();
        var currentNode = destinationNode;
        do{
            path.add(currentNode.getElement());
            currentNode = currentNode.getParentNode();
        }while(currentNode != null);

        Collections.reverse(path);

        personalTasks.enQueue(new MoveToTask(path, this));
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public HotelElement getCurrentRoom() {
        return null;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setCurrentRoom(HotelElement room) {
        this.currentRoom = room;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getGuestNumber() {
        return this.guestNumber;
    }
}
