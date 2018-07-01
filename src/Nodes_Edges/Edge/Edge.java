package Nodes_Edges.Edge;

// class for the construction of Edges
public class Edge {
    // private variables for edge
    // Nodes_Edges.Edge ID
    private String edgeId;
    // Nodes_Edges.Edge cost
    private int edgeCost;
    // Id of stating Nodes_Edges.Node
    private String edgeStartingNode;
    // Id of ending Nodes_Edges.Node
    private String edgeEndingNode;

    // variable used to give ID number at nodes
    private static int edgeIDGiver = 0;

    // empty constructor
    public Edge() {}

    // Nodes_Edges.Edge constructor
    public Edge (int edgeCost, String edgeStartingNode, String edgeEndingNode) {
        // assign an id to each edge via the edgeIDGiver static variable and
        // the pattern "edge+edgeIDGiver" ex: edge1 as edge id
        edgeIDGiver++;
        this.edgeId = "edge" + edgeIDGiver;
        this.edgeCost = edgeCost;
        this.edgeStartingNode = edgeStartingNode;
        this.edgeEndingNode = edgeEndingNode;
    }

    // method to get the id of Nodes_Edges.Edge's starting Nodes_Edges.Node
    public String getEdgeStartingNode() {
        return edgeStartingNode;
    }

    // method to get the id of Nodes_Edges.Edge's ending Nodes_Edges.Node
    public String getEdgeEndingNode() {
        return edgeEndingNode;
    }

    // method to get the cost of the Nodes_Edges.Edge
    public int getEdgeCost() {
        return edgeCost;
    }

    // method to reset Nodes_Edges.Edge's class static variables
    public void reset() {
        edgeIDGiver = 0;
    }

    // method to alter the way an Nodes_Edges.Edge is shown at console
    @Override
    public String toString() {
        return this.edgeId + "\t" + this.edgeCost + "\t\t" + this.edgeStartingNode + "\t\t" + this.edgeEndingNode;
    }
}
