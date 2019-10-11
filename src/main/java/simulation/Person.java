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

/**
 * A person, which can be either a cleaner or a guest.
 * The abstract class contains shared behaviour required for both.
 */
public abstract class Person {
    protected int x;
    protected int y;
    protected String name;
    protected HotelElement currentRoom;
    // Each person has a personal task repository, in which their own tasks get queued.
    // This ensures their personal tasks are handled in the correct order,
    // for instance: go to restaurant -> eat at the restaurant -> go to cinema -> watch a movie.
    protected TaskRepository personalTasks = new TaskRepository();

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

    /**
     * Gets the current room the person is in.
     * This will be null while walking around.
     */
    public HotelElement getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room the person is in.
     */
    public void setCurrentRoom(HotelElement room) {
        this.currentRoom = room;
    }

    /**
     * Finds a path to the given destination, and queues up a movement task,
     * to make the person move to that destination.
     */
    public void moveTo(HotelElement destination){
        var destinations = new ArrayList<HotelElement>();
        destinations.add(destination);
        moveToClosest(destinations);
    }

    /**
     * Finds a path to the closest of the given destinations, and queues up a movement task,
     * to make the person move to that destination.
     * @return the closest destination found
     */
    public HotelElement moveToClosest(List<HotelElement> destinations){
        var pathFinding = new PathFinding();
        var graph = Graph.createGraph(hotel);

        // Set the start node based on where we currently are.
        Node<HotelElement> startNode=null;
        for(Node<HotelElement> node : graph.getNodeList()){
            if (node.getElement().getX() == x && node.getElement().getY() == y){
                startNode = node;
            }
        }

        // Set the end nodes based on the destination list.
        ArrayList<Node> endNodes=new ArrayList<>();
        for(Node<HotelElement> node:graph.getNodeList()){
            if (destinations.contains(node.getElement())){
                endNodes.add(node);
            }
        }

        // Find a path to the closest end node.
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
