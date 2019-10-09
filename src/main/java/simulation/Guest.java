package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import pathfinding.Graph;
import pathfinding.Node;
import pathfinding.PathFinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Guest implements IObserver, Drawable {
    private String name;
    private static int guestID = 00;
    private int x;
    private int y;
    private List<HotelElement> path = new ArrayList<>();

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
        System.out.println("Step");
        if(path.size() > 0){
            var nextElement = path.get(0);
            path.remove(0);

            this.x = nextElement.getX();
            this.y = nextElement.getY();
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

        Node<HotelElement> destinationNode = pathFinding.doPathFinding(startNode,endNode);
        List<HotelElement> path = new ArrayList<>();
        var currentNode = destinationNode;
        do{
            path.add(currentNode.getElement());
            currentNode = currentNode.getParentNode();
        }while(currentNode != null);

        Collections.reverse(path);
        this.path = path;
    }
}
