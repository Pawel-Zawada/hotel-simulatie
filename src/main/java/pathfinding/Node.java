package pathfinding;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private double gCost; // afstand naar vorige node
    private double hCost; // geschate affstand naar de eind node

    private Node parentNode;

    public List<Connection> getNeighbours(){
        return new ArrayList<>();
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
}
