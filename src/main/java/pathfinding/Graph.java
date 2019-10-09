package pathfinding;
import simulation.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private ArrayList<HotelElement> hotelElements;
    private int width;
    private int height;
    private Hotel hotel;
    HashMap<HotelElement, Node> hotelElementNodeHashMap = new HashMap<>();

    public Graph(Hotel hotel){
        this.hotel = hotel;
        this.hotelElements = hotel.getHotelElements();
        this.width = hotel.getWidth();
        this.height = hotel.getHeight();
        fillHotelElementNodeHashMap();
        setHotelElementNeighbours();
    }

    private void fillHotelElementNodeHashMap(){
        for(HotelElement hotelElement:hotelElements){
           hotelElementNodeHashMap.put( hotelElement,new Node(hotelElement.getX(),hotelElement.getY()));
        }
    }

    private void setHotelElementNeighbours(){
        for(HotelElement hotelElement:hotelElementNodeHashMap.keySet()){
            if (hotelElement.getClass() == Elevator.class){
                for(Node node:hotelElementNodeHashMap.values()){
                    if (hotelElement.getX()+hotelElement.getWidth() == node.getX()) {
                        hotelElementNodeHashMap.get(hotelElement).connect(node, hotel.getElevatorWeight());
                    }
                }
            } else if (hotelElement.getClass() == Stairs.class){
                //connect left nodes
                int xOfLeftNode = 0;
                Node leftNode = null;
                for(Node node:hotelElementNodeHashMap.values()){
                    if (hotelElement.getY()==node.getY()) { //same floor
                        if (xOfLeftNode<node.getX() && node.getX() != hotelElement.getX()){
                            xOfLeftNode = node.getX();
                            leftNode = node;
                        }
                    }
                }
                if (leftNode!= null) {
                    int weight = hotelElement.getX()-xOfLeftNode;
                    hotelElementNodeHashMap.get(hotelElement).connect(leftNode, weight);
                }
                //connect top and bottom nodes
                for(Node node:hotelElementNodeHashMap.values()){
                    if (hotelElement.getX()==node.getX()) { //node is a stairs
                        if (node.getY() == hotelElement.getY()+1 || node.getY() == hotelElement.getY()-1){
                            hotelElementNodeHashMap.get(hotelElement).connect(node, hotel.getStairsWeight());
                        }
                    }
                }
            } else{
                for (Node node:hotelElementNodeHashMap.values()){
                    if(node.getX() != 0 && node.getX() != width-1) {
                        if (node.getY() == hotelElement.getY()) { //same floor
                            if (node.getX() == hotelElement.getX() + hotelElement.getWidth()) { //connect right node
                                int weight = hotelElement.getX() + hotelElement.getWidth();
                                hotelElementNodeHashMap.get(hotelElement).connect(node, weight);
                            }

                            if ((hotelElement.getX()- getElementOfNode(node).getWidth()) == node.getX()){ //connect left node
                                int weight = hotelElement.getX() - node.getX();
                                hotelElementNodeHashMap.get(hotelElement).connect(node, weight);
                            }
                        }
                    }
                }
            }
        }
    }

    private HotelElement getElementOfNode(Node node) {
        HotelElement hotelElement = null;
        for (HotelElement tmpElement: hotelElementNodeHashMap.keySet()){
            if ((tmpElement.getY() == node.getY()) && (tmpElement.getX()==node.getX())){
                hotelElement = tmpElement;
            }
        }
        return hotelElement;
    }
}
