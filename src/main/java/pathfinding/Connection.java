package pathfinding;

public class Connection {

    private final Node node;
    private final double weight;

    public Connection(Node node, double weight){

        this.node = node;
        this.weight = weight;
    }

    public Node getNode() {
        return node;
    }

    public double getWeight() {
        return weight;
    }
}
