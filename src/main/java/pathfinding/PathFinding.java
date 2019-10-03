package pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PathFinding {
    /**
     *  openList deze bevat de nodes die nog berekend moeten worden
     *  closedList bevat de nodes die al berekend zijn, hier gaan we niet meer naar terug
     */

    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();

    /**
     * @param startNode
     * @param endNode
     * @return de endNode met een parent node, de parent node heeft weer een parent node, enz., tot aan de startNode. Dit is het pad
     */
    public Node doPathFinding(Node startNode, Node endNode){
        Node currentNode = startNode;

        openList.add(startNode);

        while(currentNode!=endNode){
            /** gCost = afstand naar vorige node
             * hCost = geschate affstand naar de eind node
             * fCost = gCost + hCost
             * currentNode = node in open list met de laagste fCost */
            currentNode = getLowestFCostNode(openList);
            openList.remove(currentNode);
            closedList.add(currentNode);
            if (currentNode == endNode){
                return currentNode;
            }
            else {
                /**Voor elke node waarmee de current node verbonden is*/
                List<Connection> neighbourList = currentNode.getNeighbours();
                for(Connection connection:neighbourList){
                    Node node = connection.getNode();
                    if(closedList.contains(node)){
                        continue;
                    }
                    double gCost = calGCost(currentNode,connection);
                    /**Als het nieuwe pad naar de naaste node korter is OF als de node nog niet in de open list zit*/
                    if(gCost < node.getgCost() || !openList.contains(node)){
                        node.setgCost(gCost);
                        node.sethCost(calHCost(node, endNode));
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

    private double calHCost(Node node, Node endNode){
        /**distance from end node*/
        double xf = Math.abs(node.getX()-endNode.getX());
        double yf = Math.abs(node.getY()-endNode.getY());
        return calDistance(xf,yf);
    }

    private double calDistance(double xf, double yf){
        return Math.sqrt(Math.pow(xf,2) + Math.pow(yf,2));
    }

}
