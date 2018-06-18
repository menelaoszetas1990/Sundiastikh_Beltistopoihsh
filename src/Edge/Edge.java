package Edge;

public class Edge {
    // private variables for edge
    private String edgeId;
    private int edgeCost;
    private String edgeStartingNode;
    private String edgeEndingNode;

    // variable used to give ID number at nodes
    public static int edgeIDGiver = 0;

    public Edge (int edgeCost, String edgeStartingNode, String edgeEndingNode) {
        // assign an id to each edge via the edgeIDGiver static variable and
        // the pattern "edge+edgeIDGiver" ex: edge1 as edge id
        edgeIDGiver++;
        this.edgeId = "edge" + edgeIDGiver;
        this.edgeCost = edgeCost;
        this.edgeStartingNode = edgeStartingNode;
        this.edgeEndingNode = edgeEndingNode;

        // logging for test reasons
        // System.out.println("Edge created with id: " + this.edgeId);
    }

    @Override
    public String toString() {
        return this.edgeId + " " + this.edgeCost + " " + this.edgeStartingNode + " " + this.edgeEndingNode;
    }
}
