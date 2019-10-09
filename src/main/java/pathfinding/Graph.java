package pathfinding;
import simulation.*;

import java.util.*;

public class Graph {
    private ArrayList<HotelElement> hotelElements;
    private int width;
    private Hotel hotel;
    private List<Node<HotelElement>> nodeList = new ArrayList<>();

    private Graph(Hotel hotel){
        this.hotel = hotel;
        this.hotelElements = hotel.getHotelElements();
        this.width = hotel.getWidth();
    }

    public static Graph createGraph(Hotel hotel){
        Graph graph = new Graph(hotel);
        graph.fillNodeList();
        graph.setHotelElementNeighbours();
        return graph;
    }

    private void fillNodeList(){
        for(HotelElement hotelElement:hotelElements){
            /** Nodes get a X and a Y just in case we want to use A* **/
            nodeList.add(new Node<>(hotelElement.getX(),hotelElement.getY(),hotelElement));
        }
    }

    private void setHotelElementNeighbours(){

        for(Node<HotelElement> node: nodeList){
            HotelElement hotelElement = node.getElement();
            if (hotelElement.getClass() == Elevator.class){ //connect elevator to left nodes. in applies elevator weight
                for(Node<HotelElement> neigbourCandidate: nodeList){
                    if (hotelElement.getX()+hotelElement.getWidth() == neigbourCandidate.getElement().getX()) {
                        neigbourCandidate.connect(node, hotel.getElevatorWeight());
                        node.connect(neigbourCandidate, hotelElement.getWidth());
                    }
                }
            } else if (hotelElement.getClass() == Stairs.class){
                //connect left nodes
                var leftNode = findLeftNeighbour(hotelElement);
                if (leftNode!= null) {
                    int weight = hotelElement.getX()-leftNode.getElement().getX();
                    node.connect(leftNode, weight);
                }

                //connect top and bottom nodes
                for(Node<HotelElement> neigbourCandidate: nodeList){
                    if (hotelElement.getX()==neigbourCandidate.getElement().getX()) { //node is a stairs
                        if (neigbourCandidate.getElement().getY() == hotelElement.getY()+1 || neigbourCandidate.getElement().getY() == hotelElement.getY()-1){
                            node.connect(neigbourCandidate, hotel.getStairsWeight());
                        }
                    }
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


    public List<Node<HotelElement>> getNodeList(){
        return Collections.unmodifiableList(nodeList);
    }

    private Node<HotelElement> findLeftNeighbour(HotelElement hotelElement){
        int xOfLeftNode = 0;
        Node leftNode = null;
        for(Node<HotelElement> neigbourCandidate: nodeList){
            if (hotelElement.getY()==neigbourCandidate.getElement().getY()) { //same floor
                if (xOfLeftNode<neigbourCandidate.getElement().getX() && neigbourCandidate.getElement().getX() != hotelElement.getX()){
                    xOfLeftNode = neigbourCandidate.getElement().getX();
                    leftNode = neigbourCandidate;
                }
            }
        }
        return leftNode;
    }

    private Node<HotelElement> findRightNeighbour(HotelElement hotelElement){
        Node rightNode = null;
        for(Node<HotelElement> neigbourCandidate: nodeList){
            if (hotelElement.getY()==neigbourCandidate.getElement().getY()) { //same floor
                if (hotelElement.getX()+hotelElement.getWidth() == neigbourCandidate.getElement().getX()){
                    rightNode = neigbourCandidate;
                }
            }
        }
        return rightNode;
    }
}
