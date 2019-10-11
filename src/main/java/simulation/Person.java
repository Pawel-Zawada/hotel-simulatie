package simulation;

import pathfinding.Graph;
import pathfinding.Node;
import pathfinding.PathFinding;
import tasks.MoveToTask;
import tasks.Task;
import tasks.TaskRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Person {
    protected int x;
    protected int y;
    protected String name;
    protected HotelElement currentRoom;
    private TaskRepository personalTasks = new TaskRepository();
    protected Hotel hotel;

    public Person(Hotel hotel){

        this.hotel = hotel;
    }

    public String getName(){
        return this.name;
    }

    public TaskRepository getPersonalTasks() {
        return personalTasks;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public HotelElement getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(HotelElement room) {
        this.currentRoom = room;
    }

    public void moveTo(HotelElement destination){
        var destinations = new ArrayList<HotelElement>();
        destinations.add(destination);
        moveToClosest(destinations);
    }

    public HotelElement moveToClosest(List<HotelElement> destinations){
        var pathFinding = new PathFinding();
        var graph = Graph.createGraph(hotel);

        Node<HotelElement> startNode=null;
        for(Node<HotelElement> node:graph.getNodeList()){
            if (node.getElement().getX() == x && node.getElement().getY() == y){
                startNode = node;
            }
        }

        ArrayList<Node> endNodes=new ArrayList<>();
        for(Node<HotelElement> node:graph.getNodeList()){
            if (destinations.contains(node.getElement())){
                endNodes.add(node);
            }
        }

        Node<HotelElement> destinationNode = pathFinding.doPathFinding(startNode,endNodes);
        if(destinationNode == null){
            System.out.println("Could not find a path to destination: " + destinations.get(0).getClass().getName());
        } else {
            List<HotelElement> path = new ArrayList<>();
            var currentNode = destinationNode;
            do {
                path.add(currentNode.getElement());
                currentNode = currentNode.getParentNode();
            } while (currentNode != null && currentNode != destinationNode);

            Collections.reverse(path);

            personalTasks.enQueue(new MoveToTask(path, this));
        }
        return destinationNode.getElement();
    }

    public abstract void evacuate(Hotel hotel);
}
