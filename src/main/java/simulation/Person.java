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
    protected TaskRepository personalTasks = new TaskRepository();
    protected Hotel hotel;

    public Person(Hotel hotel){

        this.hotel = hotel;
    }

    public String getName(){
        return this.name;
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
        var pathFinding = new PathFinding();
        var graph = Graph.createGraph(hotel);
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
        if(destinationNode == null){
            System.out.println("Could not find a path to destination: " + destination.getClass().getName());

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
    }

}
