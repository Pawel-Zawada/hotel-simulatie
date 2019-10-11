package pathfinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Marc Kemp
 */
public class PathFinding {
    /**
     *  openList contains nodes yet to be calculated
     *  closedList contains nodes already calculated, we wont return no this list
     */

    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> closedList = new ArrayList<>();

    /**
     * @param startNode
     * @param endNode
     * @return returns the endNode, the endNode contains a parentNode, every parentNode contains a parentNode, until the startNode, this is the path
     */

    public Node doPathFinding(Node startNode, Node endNode){
        var endNodes = new ArrayList<Node>();
        endNodes.add(endNode);
        return doPathFinding(startNode, endNodes);
    }

    public Node doPathFinding(Node startNode, List<Node> endNodes){
        Node currentNode = startNode;

        openList.add(startNode);

        while(!endNodes.contains(currentNode)){
            /** gCost = distance to previous node
             * hCost = estimated distance to end node
             * fCost = gCost + hCost
             * currentNode = node in openList with the lowest fCost */
            currentNode = getLowestFCostNode(openList);
            openList.remove(currentNode);
            closedList.add(currentNode);
            if (endNodes.contains(currentNode)){
                return currentNode;
            }
            else {
                /**For every node connected to the current node*/
                List<Connection> neighbourList = currentNode.getNeighbours();
                for(Connection connection:neighbourList){
                    Node node = connection.getNode();
                    if(closedList.contains(node)){
                        continue;
                    }
                    double gCost = calGCost(currentNode,connection);
                    /**If the new path to the next node is shorter, of if the next node is not yet in the open list */
                    if(gCost < node.getgCost() || !openList.contains(node)){
                        node.setgCost(gCost);
                        node.sethCost(calHCost(node, endNodes));
                        node.setParentNode(currentNode);
                        if(currentNode.getParentNode() == node.getParentNode()){
                            System.out.println("oh no.");
                        }
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

    private double calHCost(Node node, List<Node> endNodes){
        /**distance from end node*/
        double shortestDistance = Double.MAX_VALUE;
        for(Node endNode: endNodes){
            double xf = Math.abs(node.getX()-endNode.getX());
            double yf = Math.abs(node.getY()-endNode.getY());
            double distance =  calDistance(xf,yf);
            if(shortestDistance > distance){
                shortestDistance = distance;
            }
        }
        return shortestDistance;
    }

    private double calDistance(double xf, double yf){
        return Math.sqrt(Math.pow(xf,2) + Math.pow(yf,2));
    }

}
