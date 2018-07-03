package Nodes_Edges.Node;

import java.util.*;

// class for the Nodes creation
public class Node {

    // variable used to give ID number at nodes
    private static int nodeIDGiver = 0;

    // node's ID variable is private
    private String nodeId;

    // node's list of nearest nodes
    private ArrayList<Node> nearestNodesList = new ArrayList<Node>();

    // constructor for each Nodes_Edges.Node
    public Node() {
        // assign an id to each node via the nodeIDGiver static variable and
        // the pattern "node+nodeIDGiver" ex: node1 as node id
        nodeIDGiver++;
        this.nodeId = "node" + nodeIDGiver;
    }

    // getter method to return the node ID when needed
    public String getNodeId() {
        return nodeId;
    }

    // setter method to set nearest nodes
    public void setNearestNodesList(Node nearestNodeId) {
        nearestNodesList.add(nearestNodeId);
    }

    // getter method to return the list of nearest nodes
    public ArrayList<Node> getNearestNodesList() {
        return nearestNodesList;
    }

    // method to reset the static vars of the class if
    // the creation of the graph fails
    public void reset() {
        nodeIDGiver = 0;
        nearestNodesList.clear();
    }

    // ----------------------------------------------------------------------------------------
    // Functionality for Algorithms.Dijkstra
    private int distanceFromSource = Integer.MAX_VALUE;
    Map <Node, Integer> distanceToAdjacentNodes = new HashMap<Node, Integer>();

    private LinkedList<Node> shortestPath = new LinkedList<>();

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public Map <Node, Integer> getDistanceToAdjacentNodes() {
        return distanceToAdjacentNodes;
    }

    public void addDistanceToAdjacentNodes(Node destinationNode, int distance) {
        distanceToAdjacentNodes.put(destinationNode, distance);
    }

    // ----------------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------------
    // Functionality for Floyd-Warshall
    public Node(int num) {
        if (num == 0)
            this.nodeId = "Empty";
    }
    // ----------------------------------------------------------------------------------------


    // method to override how a node is shown to the console
    @Override
    public String toString() {
        return this.nodeId;
    }
}
