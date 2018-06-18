package Node;

import java.util.ArrayList;

public class Node {

    // variable used to give ID number at nodes
    public static int nodeIDGiver = 0;

    // node's ID variable is private for security reasons
    private String nodeId;
    // node's list of nearest nodes
    private ArrayList<Node> nearestNodesList = new ArrayList<Node>();

    // constructor for each Node
    public Node() {
        // assign an id to each node via the nodeIDGiver static variable and
        // the pattern "node+nodeIDGiver" ex: node1 as node id
        nodeIDGiver++;
        this.nodeId = "node" + nodeIDGiver;

        // logging for test reasons
        // System.out.println("Node created with id: " + this.nodeId);
    }

    // getter method to return the node ID when needed
    public String getNodeId() {
        return nodeId;
    }

    // getter method to return the total amount of nodes
    public int getNodesNumber() {
        return nodeIDGiver;
    }

    // setter method to set ids of nearest nodes
    public void setNearestNodesList(Node nearestNodeId) {
        nearestNodesList.add(nearestNodeId);
    }

    // getter method to return the list of nearest nodes
    public ArrayList<Node> getNearestNodesList() {
        return nearestNodesList;
    }

    public void reset() {
        nodeIDGiver = 0;
        nearestNodesList.clear();
    }

    @Override
    public String toString() {
        return this.nodeId;
    }
}
