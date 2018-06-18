package GraphCreator;

import java.util.ArrayList;

public class Node {

    // variable used to give ID at nodes
    public static int nodeIDGiver = 1;
    // node's ID variable is private for security reasons
    private int nodeId;
    // node's list of nearest nodes
    ArrayList<Integer> nearestNodesList = new ArrayList<Integer>();

    // constructor for each Node
    public Node() {
        // assign an id to each node via the nodeIDGiver static variable
        this.nodeId = nodeIDGiver++;

        // logging for test reasons
        System.out.println("Node created with id: " + this.nodeId);
    }

    // getter method to return the node ID when needed
    public int getNodeId() {
        return nodeId;
    }

    // getter method to return the total amount of nodes
    public int getNodesNumber() {
        return nodeIDGiver;
    }

    // setter method to set ids of nearest nodes
    public void setNearestNodesList(int nearestNodeId) {
        nearestNodesList.add(nearestNodeId);
    }

    // getter method to return the list of nearest nodes
    public ArrayList<Integer> getNearestNodesList() {
        return nearestNodesList;
    }
}
