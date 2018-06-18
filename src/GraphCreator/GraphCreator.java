package GraphCreator;

import Edge.Edge;
import Node.Node;
import RandomGenerator.RandomGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphCreator {
    // List of nodes
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private int[][] arrayNodesEdges;

    public void createGraph(int nodeNumber) {

        boolean completeGraph = false;
        do {
            // with a repetition we create the number of nodes asked from the user
            for (int i = 0; i < nodeNumber; i++) {
                // create and add the new nodes to the list
                Node newNode = new Node();
                nodes.add(newNode);
            }

            // after we create the nodes, we create for each node edges that start from that node and end up at another node
            for (int i = 0; i < nodeNumber; i++) {
                // create an ArrayList with all the possible nodes that our node can connect.
                // Do not include the number that matches our node because it is useless
                ArrayList<Integer> possibleEndNodes = new ArrayList<Integer>();
                for (int j = 0; j < nodeNumber; j++) {
                    if (j == i) {
                        continue;
                    }
                    possibleEndNodes.add(j);
                }

                // create a random number of nodes that our node will connect to
                // that number will be at least 1 and smaller or equal to the total number of possible
                // nodes that we can connect to (nodeNumber -1)
                int numbersOfNodesToConnect = new RandomGenerator().generateNumber(nodeNumber - 1);

                // for the generated number we do a repetition to add new edges
                for (int k = numbersOfNodesToConnect; k > 0; k--) {
                    int randomNode = new RandomGenerator().generateNumber(k);

                    Edge newEdge = new Edge(new RandomGenerator().generateNumber(), nodes.get(i).getNodeId(), nodes.get(possibleEndNodes.get(randomNode - 1)).getNodeId());

                    // add to the node from witch we start the edges, the nodes it connects to
                    nodes.get(i).setNearestNodesList(nodes.get(possibleEndNodes.get(randomNode - 1)));

                    // remove the already used node from the list
                    possibleEndNodes.remove(randomNode - 1);
                    edges.add(newEdge);
                }

                arrayNodesEdges = new int[nodeNumber][edges.size()];
                fillArrayNodesEdges(nodeNumber, edges.size());
            }

            completeGraph = checkGraph(nodeNumber, edges.size());
        } while (!completeGraph);

        // show the list for test
        for (int j=0; j < nodes.size(); j++)
        System.out.println("The nodes list: " + nodes.get(j).getNodeId());
    }

    private boolean checkGraph(int rows, int columns) {
        boolean goodGraph;
        for (int i = 0; i < rows; i++) {
            goodGraph = false;
            for (int j = 0; j < columns; j++) {
                if (arrayNodesEdges[i][j] == -1) {
                    goodGraph = true;
                }
            }
            if (!goodGraph) {
                System.out.println("Not a good graph");
                nodes.clear();
                Node node = new Node();
                node.reset();
                edges.clear();
                Edge edge = new Edge();
                edge.reset();
                arrayNodesEdges = null;
                return false;
            }
        }
        return true;
    }

    private void fillArrayNodesEdges(int rows, int columns) {
        nodes.forEach(node -> {
            edges.forEach(
                edge -> {
                    if (node.getNodeId().equals(edge.getEdgeStartingNode()))
                        arrayNodesEdges[nodes.indexOf(node)][edges.indexOf(edge)] = 1;
                    if (node.getNodeId().equals(edge.getEdgeEndingNode()))
                        arrayNodesEdges[nodes.indexOf(node)][edges.indexOf(edge)] = -1;
                }
            );
        });
//        test reasons
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                System.out.print(arrayNodesEdges[i][j]);
//                System.out.print("\t");
//            }
//            System.out.println();
//        }
    }
}