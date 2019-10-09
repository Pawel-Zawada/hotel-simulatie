package pathfinding;

public class Connection<T> {

    private final Node<T> node;
    private final double weight;

    public Connection(Node<T> node, double weight){

        this.node = node;
        this.weight = weight;
    }

    public Node<T> getNode() {
        return node;
    }

    public double getWeight() {
        return weight;
    }
}
