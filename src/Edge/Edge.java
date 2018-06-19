package Edge;

// class for the construction of Edges
public class Edge {
    // private variables for edge
    // Edge ID
    private String edgeId;
    // Edge cost
    private int edgeCost;
    // Id of stating Node
    private String edgeStartingNode;
    // Id of ending Node
    private String edgeEndingNode;

    // variable used to give ID number at nodes
    private static int edgeIDGiver = 0;

    // empty constructor
    public Edge() {}

    // Edge constructor
    public Edge (int edgeCost, String edgeStartingNode, String edgeEndingNode) {
        // assign an id to each edge via the edgeIDGiver static variable and
        // the pattern "edge+edgeIDGiver" ex: edge1 as edge id
        edgeIDGiver++;
        this.edgeId = "edge" + edgeIDGiver;
        this.edgeCost = edgeCost;
        this.edgeStartingNode = edgeStartingNode;
        this.edgeEndingNode = edgeEndingNode;
    }

    // method to get the id of Edge's starting Node
    public String getEdgeStartingNode() {
        return edgeStartingNode;
    }

    // method to get the id of Edge's ending Node
    public String getEdgeEndingNode() {
        return edgeEndingNode;
    }

    // method to reset Edge's class static variables
    public void reset() {
        edgeIDGiver = 0;
    }

    // method to alter the way an Edge is shown at console
    @Override
    public String toString() {
        return this.edgeId + " " + this.edgeCost + " " + this.edgeStartingNode + " " + this.edgeEndingNode;
    }
}
