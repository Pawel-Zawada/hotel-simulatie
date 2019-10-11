package pathfinding;
import simulation.*;

import java.util.*;

/**
 * @author Marc Kemp
 * Represents the hotel in a graph form, which can be traversed by the pathfinding algorithm.
 */
public class Graph {
    private List<HotelElement> hotelElements;
    private int width;
    private Hotel hotel;
    private List<Node<HotelElement>> nodeList = new ArrayList<>();

    /**
     * Internal constructor, only used by createGraph.
     */
    private Graph(Hotel hotel){
        this.hotel = hotel;
        this.hotelElements = hotel.getHotelElements();
        this.width = hotel.getWidth();
    }

    /**
     * Builds a graph from a hotel.
     */
    public static Graph createGraph(Hotel hotel){
        Graph graph = new Graph(hotel);
        graph.fillNodeList();
        graph.setHotelElementNeighbours();
        return graph;
    }

    /**
     * Generates the node list based on the rooms in the hotel.
     */
    private void fillNodeList(){
        for(HotelElement hotelElement:hotelElements){
            // In order to use A*, we need to tell each node its coordinates, so that the pathfinding algorithm
            // can use heuristics to determine which paths to evaluate first.
            nodeList.add(new Node<>(hotelElement.getX(),hotelElement.getY(),hotelElement));
        }
    }

    /**
     * Connects each node to its neighbours where appropriate.
     */
    private void setHotelElementNeighbours(){
        for(Node<HotelElement> node: nodeList){
            HotelElement hotelElement = node.getElement();
            if (hotelElement.getClass() == Elevator.class){ //connect elevator to left nodes. in applies elevator weight
                for(Node<HotelElement> neigbourCandidate: nodeList){
                    if (hotelElement.getX()+hotelElement.getWidth() == neigbourCandidate.getElement().getX()) {
                        neigbourCandidate.connect(node, hotel.getConfiguration().getElevatorWeight());
                        node.connect(neigbourCandidate, hotelElement.getWidth());
                    }
                }
            } else if (hotelElement.getClass() == Stairs.class) {
                //connect left nodes
                var leftNode = findLeftNeighbour(hotelElement);
                if (leftNode != null) {
                    int weight = hotelElement.getX() - leftNode.getElement().getX();
                    node.connect(leftNode, weight);
                }

                //connect top and bottom nodes
                for (Node<HotelElement> neigbourCandidate : nodeList) {
                    if (hotelElement.getX() == neigbourCandidate.getElement().getX()) { //node is a stairs
                        if (neigbourCandidate.getElement().getY() == hotelElement.getY() + 1 || neigbourCandidate.getElement().getY() == hotelElement.getY() - 1) {
                            node.connect(neigbourCandidate, hotel.getConfiguration().getStairsWeight());
                        }
                    }
                }
            }else if(hotelElement.getClass() == Lobby.class){
                var leftNode = findLeftNeighbour(hotelElement);
                if (leftNode!= null) {
                    int weight = hotelElement.getX()-leftNode.getElement().getX();
                    node.connect(leftNode, weight);
                }
                var rightNode = findRightNeighbour(hotelElement);
                if (rightNode!=null){
                    int weight = hotelElement.getWidth();
                    node.connect(rightNode,weight);
                }
            } else{
                var leftNode = findLeftNeighbour(hotelElement);
                if (leftNode!= null) {
                    int weight = hotelElement.getX()-leftNode.getElement().getX();
                    node.connect(leftNode, weight);
                }
                var rightNode = findRightNeighbour(hotelElement);
                if (rightNode!=null){
                    int weight = hotelElement.getWidth();
                    node.connect(rightNode,weight);
                }
            }
        }
    }

    /**
     * Gets the node list of the graph.
     */
    public List<Node<HotelElement>> getNodeList(){
        return Collections.unmodifiableList(nodeList);
    }

    /**
     * Finds the nearest neighbour to the left side of this element.
     */
    private Node<HotelElement> findLeftNeighbour(HotelElement hotelElement){
        int xOfLeftNode = 0;
        Node leftNode = null;

        for(Node<HotelElement> neighbourCandidate: nodeList){
            // Only consider nodes that are on the same floor and to the left of us.
            if (hotelElement.getY() == neighbourCandidate.getElement().getY() && neighbourCandidate.getElement().getX() < hotelElement.getX()) {
                if (xOfLeftNode < neighbourCandidate.getElement().getX()){
                    // We've found a better match (a closer node).
                    xOfLeftNode = neighbourCandidate.getElement().getX();
                    leftNode = neighbourCandidate;
                }
            }
        }
        return leftNode;
    }

    /**
     * Finds the nearest neighbour to the right side of this element.
     */
    private Node<HotelElement> findRightNeighbour(HotelElement hotelElement){
        Node rightNode = null;
        for(Node<HotelElement> neighbourCandidate : nodeList){
            if (hotelElement.getY()==neighbourCandidate.getElement().getY()) { //same floor
                if (hotelElement.getX()+hotelElement.getWidth() == neighbourCandidate.getElement().getX()){
                    rightNode = neighbourCandidate;
                }
            }
        }
        return rightNode;
    }
}
