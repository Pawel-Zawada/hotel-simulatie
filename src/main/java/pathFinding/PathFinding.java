package pathFinding;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PathFinding {
    /**
     * List open nodes  // deze bevat de nodes die nog berekend moeten worden
     * List closed nodes   // deze bevat de nodes die al berekend zijn, hier gaan we niet meer naar terug
     *
     *  Stap 1 voeg de start node toe aan de open list
     *
     *  Loop
     *      Current node = node in open list met de laagste f cost
     *      verwijder current van open list
     *      voeg current node toe aan closed list
     *
     *      Als de current node de target node is, return, want we hebben het pad
     *
     *      Anders
     *      Voor elke naaste node van de current node:
     *          Als de node niet bewandelbaar is OF als de node in de clossed list zit
     *              Ga naar de volgende node
     *          Als het nieuwe pad naar de naaste node korter is OF als de node nog niet in de open list zit
     *              Set f cost van de naaste node
     *              Set parent van naaste node naar de huidige node
     *              Als de naaste node niet in de open list staat voeg hem dan toe
     *
     *
     *  1 Maak een grid node class, kan bewandel baar zijn of solid
     *  2 Maak een grid en vul die met nodes
     *  3
     */

    private Node startNode;
    private Node endNode;
    private Node currentNode;
    private Floor floor;

    private ArrayList<Node> nodeList;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();

    public PathFinding(Floor floor){
        this.floor = floor;
        this.nodeList = floor.getNodeList();
        this.startNode = nodeList.get(0);
        this.endNode = nodeList.get(nodeList.size()-1);
        this.currentNode = startNode;
        openList.add(startNode);

        setFCosts();

        doPathFinding();
    }

    private void setFCosts(){
        for(Node node: nodeList){
            node.setFCost(calFcost(node));
        }
    }

    private void doPathFinding(){
        while(currentNode!=endNode){
            currentNode = getLowestFCostNode(openList);
            openList.remove(currentNode);
            closedList.add(currentNode);

            if (currentNode == endNode){
                System.out.println("jeeeej ik heb het pad gevonden");
                System.out.println(openList.size());
                System.out.println(closedList);
                return;
            }
            else {

                double lowestNodeFcost = 100000;
                for(Node node:nodeNeighbours(currentNode)){
                    if(node.isSolid() || closedList.contains(node)){
                        continue;
                    }
                    if(node.getFCost() < lowestNodeFcost || !openList.contains(node)){
                        node.setFCost(calFcost(node));
                        node.setParrentNode(currentNode);
                        if(!openList.contains(node)){
                            openList.add(node);
                        }
                    }

                }
            }
        }
    }

    private ArrayList<Node> nodeNeighbours(Node node){
        ArrayList<Node> nodeNeighbours = new ArrayList<>();
        int nodeX = node.getX();
        int nodeY = node.getY();
        int curNodeNumber;
        try {
            curNodeNumber = (nodeX - 1) * (nodeY - 1);
            nodeNeighbours.add(nodeList.get(curNodeNumber - 1));
        }catch (Exception e){}
        try {
            curNodeNumber = (nodeX) * (nodeY - 1);
            nodeNeighbours.add(nodeList.get(curNodeNumber - 1));
        }catch (Exception e){}
        try {
            curNodeNumber = (nodeX + 1) * (nodeY - 1);
            nodeNeighbours.add(nodeList.get(curNodeNumber - 1));
        }catch (Exception e){}
        try {
            curNodeNumber = (nodeX - 1) * (nodeY);
            nodeNeighbours.add(nodeList.get(curNodeNumber - 1));
        }catch (Exception e){}
        try {
            curNodeNumber = (nodeX+1)*(nodeY);
            nodeNeighbours.add(nodeList.get(curNodeNumber-1));
        }catch (Exception e){}
        try {
            curNodeNumber = (nodeX-1)*(nodeY+1);
            nodeNeighbours.add(nodeList.get(curNodeNumber-1));
        }catch (Exception e){}
        try {
            curNodeNumber = (nodeX)*(nodeY+1);
            nodeNeighbours.add(nodeList.get(curNodeNumber-1));
        }catch (Exception e){}
        try {
            curNodeNumber = (nodeX+1)*(nodeY+1);
            nodeNeighbours.add(nodeList.get(curNodeNumber-1));
        }catch (Exception e){}
        return nodeNeighbours;
    }

    private Node getLowestFCostNode(ArrayList<Node> nodeList){
        double lowestFcost = 10000000.000000;
        Node lowestFCostNode = null;
        for(Node node: nodeList){
            if(node.getFCost()<lowestFcost){
                lowestFcost = node.getFCost();
                lowestFCostNode = node;
            }
        }
        return lowestFCostNode;
    }

    private double calGCost(Node node){
        /**distance from starting node*/
        double xf = Math.abs(node.getX()-startNode.getX());
        double yf = Math.abs(node.getY()-startNode.getY());
        return calDistance(xf,yf);
    }

    private double calHCost(Node node){
        /**distance from end node*/
        double xf = Math.abs(node.getX()-endNode.getX());
        double yf = Math.abs(node.getY()-endNode.getY());
        return calDistance(xf,yf);
    }

    private double calDistance(double xf, double yf){
        return Math.sqrt(Math.pow(xf,2) + Math.pow(yf,2));
    }

    private double calFcost(Node node){
        return calGCost(node) + calHCost(node);
    }



}
