package Node;

import java.util.ArrayList;

// class for the Nodes creation
public class Node {

    // variable used to give ID number at nodes
    private static int nodeIDGiver = 0;

    // node's ID variable is private
    private String nodeId;

    // node's list of nearest nodes
    private ArrayList<Node> nearestNodesList = new ArrayList<Node>();

    // constructor for each Node
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

    // method to override how a node is shown to the console
    @Override
    public String toString() {
        return this.nodeId;
    }
}
