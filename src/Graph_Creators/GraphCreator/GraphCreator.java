package Graph_Creators.GraphCreator;

import Nodes_Edges.Edge.Edge;
import Nodes_Edges.Node.Node;

import java.util.ArrayList;

public class GraphCreator {

    // List of Nodes
    public ArrayList<Node> nodes = new ArrayList<Node>();
    // List of Edges
    public ArrayList<Edge> edges = new ArrayList<Edge>();
    // Array of Nodes to Nodes
    public int[][] arrayNodesEdges;
    // Array of Nodes to edges
    public int[][] arrayNodesNodes;
    // List of lists of nearby Nodes that a Nodes_Edges.Node can go (adjacency list)
    private ArrayList<ArrayList<Node>> adjacencyArrayList = new ArrayList<ArrayList<Node>>();

    // number of nodes
    public final int nodesNumber = 15;

    // main method that creates the Graph
    public void createGraph() {

        // repetition to create the number of nodes asked from the user
        for (int i = 0; i < nodesNumber; i++) {
            // create a Nodes_Edges.Node
            Node newNode = new Node();
            // add the new Nodes to the Nodes list
            nodes.add(newNode);
        }

        // create the Nodes - Nodes array with the appropriate sizes based on the Graph
        arrayNodesNodes = new int[][] {
                {0,	1,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	1,	0},
                {0,	0,	0,	0,	0,	0,	0,	1,	1,	0,	0,	0,	0,	0,	0},
                {1,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0},
                {0,	1,	0,	0,	0,	1,	1,	0,	0,	1,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	1,	1,	0,	0,	1,	0,	0,	1},
                {0,	0,	0,	0,	0,	0,	1,	0,	0,	1,	0,	0,	0,	0,	0},
                {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0},
                {0,	1,	0,	0,	0,	0,	0,	0,	1,	0,	0,	1,	0,	1,	0},
                {0,	0,	1,	0,	1,	0,	1,	0,	1,	0,	0,	0,	0,	0,	0},
                {0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0}
        };

        // create the Edges list based on the Graph
        edges.add(new Edge(35,	"node1",	"node7"));
        edges.add(new Edge(24,	"node1",	"node2"));
        edges.add(new Edge(55,	"node2",	"node4"));
        edges.add(new Edge(30,	"node2",	"node15"));
        edges.add(new Edge(15,	"node3",	"node11"));
        edges.add(new Edge(90,	"node3",	"node14"));
        edges.add(new Edge(2,	"node4",	"node8"));
        edges.add(new Edge(90,	"node4",	"node9"));
        edges.add(new Edge(75,	"node5",	"node1"));
        edges.add(new Edge(28,	"node5",	"node9"));
        edges.add(new Edge(5,	"node6",	"node5"));
        edges.add(new Edge(93,	"node7",	"node6"));
        edges.add(new Edge(6,	"node7",	"node4"));
        edges.add(new Edge(82,	"node8",	"node1"));
        edges.add(new Edge(33,	"node9",	"node6"));
        edges.add(new Edge(57,	"node9",	"node2"));
        edges.add(new Edge(59,	"node9",	"node10"));
        edges.add(new Edge(97,	"node9",	"node7"));
        edges.add(new Edge(86,	"node10",	"node15"));
        edges.add(new Edge(3,	"node10",	"node8"));
        edges.add(new Edge(10,	"node10",	"node9"));
        edges.add(new Edge(16,	"node10",	"node12"));
        edges.add(new Edge(75,	"node11",	"node7"));
        edges.add(new Edge(39,	"node11",	"node10"));
        edges.add(new Edge(44,	"node12",	"node11"));
        edges.add(new Edge(7,	"node13",	"node2"));
        edges.add(new Edge(52,	"node13",	"node12"));
        edges.add(new Edge(34,	"node13",	"node14"));
        edges.add(new Edge(85,	"node13",	"node9"));
        edges.add(new Edge(57,	"node14",	"node3"));
        edges.add(new Edge(47,	"node14",	"node5"));
        edges.add(new Edge(97,	"node14",	"node7"));
        edges.add(new Edge(28,	"node14",	"node9"));
        edges.add(new Edge(29,	"node15",	"node13"));
        edges.add(new Edge(77,	"node15",	"node3"));

        // fill the Nodes_Edges.Node - Edges array
        arrayNodesEdges = new int[nodesNumber][edges.size()];
        fillArrayNodesEdges();

        // fill in the adjacency list
        // first create the nearestNodesList for each node
        for (int i = 0; i < nodesNumber; i++) {
            for (int j = 0; j < nodesNumber; j++) {
                if (arrayNodesNodes[i][j] == 1){
                    nodes.get(i).setNearestNodesList(nodes.get(j));
                }
            }
        }
        // finally fill the adjacency list
        nodes.forEach(node -> adjacencyArrayList.add(node.getNearestNodesList()));
    }

    // method to fill the array of Nodes - Edges
    // A lookup at each edge is done and an 1 is assign at its starting Nodes_Edges.Node
    // A lookup at each edge is done and a -1 is assign at its ending Nodes_Edges.Node
    private void fillArrayNodesEdges() {
        nodes.forEach(node -> {
            edges.forEach(
                    edge -> {
                        if (node.getNodeId().equals(edge.getEdgeStartingNode())) {
                            arrayNodesEdges[nodes.indexOf(node)][edges.indexOf(edge)] = 1;
                        }
                        if (node.getNodeId().equals(edge.getEdgeEndingNode()))
                            arrayNodesEdges[nodes.indexOf(node)][edges.indexOf(edge)] = -1;
                    }
            );
        });
    }
}