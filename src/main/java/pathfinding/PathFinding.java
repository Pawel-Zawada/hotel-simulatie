package pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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

    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();

    public PathFinding(Node startNode, Node endNode){
        this.startNode = startNode;
        this.endNode = endNode;
        this.currentNode = startNode;
        openList.add(startNode);
    }

    public ArrayList<Node> doPathFinding(){
        while(currentNode!=endNode){
            currentNode = getLowestFCostNode(openList);
            openList.remove(currentNode);
            closedList.add(currentNode);
            if (currentNode == endNode){
                System.out.println("jeeeej");
                return openList;
            }
            else {

                List<Connection> neighbourList = currentNode.getNeighbours();
                for(Connection connection:neighbourList){
                    Node node = connection.getNode();
                    if(closedList.contains(node)){
                        continue;
                    }
                    double gCost = calGCost(currentNode,connection);
                    if(gCost < node.getgCost() || !openList.contains(node)){
                        node.setgCost(gCost);
                        node.sethCost(calHCost(node));
                        node.setParentNode(currentNode);
                        if(!openList.contains(node)){
                            openList.add(node);
                        }
                    }
                }
            }
        }
        return null; //no valid path exists
    }

    private Node getLowestFCostNode(ArrayList<Node> nodeList){
        return nodeList.stream()
                .sorted(Comparator.comparingDouble(Node::getfCost))
                .findFirst()
                .get();
    }


    private double calGCost(Node parentNode, Connection connection){
        /**distance from starting node*/
        return parentNode.getgCost() + connection.getWeight();
    }

    private double calHCost(Node node){
        /**distance from end node*/

        //double xf = Math.abs(node.getX()-endNode.getX());
       // double yf = Math.abs(node.getY()-endNode.getY());
       // return calDistance(xf,yf);
        return 0;
    }

    private double calDistance(double xf, double yf){
        return Math.sqrt(Math.pow(xf,2) + Math.pow(yf,2));
    }

}
