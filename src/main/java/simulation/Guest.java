package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import pathfinding.Graph;
import pathfinding.Node;
import pathfinding.PathFinding;

public class Guest implements IObserver, Drawable {
    private String name;
    private static int guestID = 00;
    private int x;
    private int y;
    private Node<HotelElement> destinationNode;

    public Guest(int x, int y){
        guestID++;
        this.name = "Guest: " + guestID;
        this.x = x;
        this.y = y;
    }

    public String getName(){
        return name;
    }
    public int getGuestID() {
        return guestID;
    }

    public void observe() {
        while (destinationNode.getElement().getX() != x && destinationNode.getElement().getY() != y){

        }
    }

    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("guest", this.x, this.y);
    }

    public void moveTo(Graph graph, HotelElement destination){
        PathFinding pathFinding = new PathFinding();
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

        destinationNode = pathFinding.doPathFinding(startNode,endNode);
    }
}
