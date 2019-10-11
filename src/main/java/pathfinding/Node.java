package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Marc Kemp
 */
public class Node<T> {

    private int x;
    private int y;
    private double gCost; // distance to previous node
    private double hCost; //estimated distance to the end node
    private List<Connection<T>> neighbours = new ArrayList<>();
    private T element;

    private Node<T> parentNode;

    public Node(T element){this.element = element;} //Dijkstra

    public Node(int x, int y, T element){ //A*
        this.x = x;
        this.y = y;
        this.element = element;
    }

    public List<Connection<T>> getNeighbours(){
        return Collections.unmodifiableList(neighbours);
    }

    public void setParentNode(Node<T> node) {
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

    public Node<T> getParentNode() {
        return parentNode;
    }

    public void connect(Node<T> node, double weight) {
        Connection connection = new Connection(node,weight);
        neighbours.add(connection);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public T getElement() {
        return element;
    }
}
