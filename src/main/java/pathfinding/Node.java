package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

    private int x;
    private int y;
    private double gCost; // distance to previous node
    private double hCost; //estimated distance to the end node
    private List<Connection> neighbours = new ArrayList<>();

    private Node parentNode;

    public Node(){} //Dijkstra

    public Node(int x, int y){ //A*
        this.x = x;
        this.y = y;
    }

    public List<Connection> getNeighbours(){
        return Collections.unmodifiableList(neighbours);
    }

    public void setParentNode(Node node) {
        this.parentNode= node;
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double fCost) {
        this.gCost = fCost;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public double getfCost(){
        return gCost+hCost;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void connect(Node node, double weight) {
        Connection connection = new Connection(node,weight);
        neighbours.add(connection);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
