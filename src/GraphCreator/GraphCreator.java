package GraphCreator;

import Edge.Edge;
import Node.Node;
import RandomGenerator.RandomGenerator;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphCreator {
    // List of nodes
    private ArrayList<Node> nodes = new ArrayList<Node>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private int[][] arrayNodesEdges;
    private int[][] arrayNodesNodes;
    private ArrayList<ArrayList<Node>> adjacencyArrayList = new ArrayList<ArrayList<Node>>();

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
                fillArrayNodesEdges(nodeNumber, edges.size(), i);

                arrayNodesNodes = new int[nodeNumber][nodeNumber];
                fillArrayNodesNodes(nodeNumber, i);
            }

            completeGraph = checkGraph1(nodeNumber, edges.size());
            if (completeGraph) {
                completeGraph = checkGraph2(nodeNumber);
            }

        } while (!completeGraph);

        System.out.println("Perase");

        // list of adjacency
        nodes.forEach(
                node -> {
                    adjacencyArrayList.add(node.getNearestNodesList());
                }
        );

        // show the list for test
        // for (int j=0; j < nodes.size(); j++)
        // System.out.println("The nodes list: " + nodes.get(j).getNodeId());
    }

    private boolean checkGraph1(int rows, int columns) {
        boolean goodGraph;
        for (int i = 0; i < rows; i++) {
            goodGraph = false;

            // check if the node accepts an edge from other nodes
            for (int j = 0; j < columns; j++) {
                if (arrayNodesEdges[i][j] == -1) {
                    goodGraph = true;
                }
            }
            // if at graph it is not possible to visit all nodes from one starting point
            // then reset all the variables of the graph
            if (!goodGraph) {
                System.out.println("Not a good graph");
                nodes.clear();
                edges.clear();

                Node node = new Node();
                node.reset();
                Edge edge = new Edge();
                edge.reset();

                arrayNodesEdges = null;

                return false;
            }
        }
        return true;
    }

    private boolean checkGraph2(int nodeNumber) {
        Node startingNode, currentNode, nextNode;
        boolean isNodesNodesEliminationArrayEmpty = false;
        ArrayList<Node> nodeListToEliminate = (ArrayList<Node>) nodes.clone();
        int[][] nodesNodesEliminationArray = arrayNodesNodes.clone();

        // lets suppose we start form the 1st node.
        // we want to get back to it as soon us we have passed from all the other nodes
        startingNode = nodes.get(0);
        currentNode = nodes.get(0);
        do {
            for (int j = 0; j < nodeNumber; j++) {
                if (nodesNodesEliminationArray[nodes.indexOf(currentNode)][j] == 1) {
                    nextNode = nodes.get(j);

                    System.out.println(currentNode.getNodeId());

                    nodesNodesEliminationArray[nodes.indexOf(currentNode)][j] = 0;

                    for (int i = 0; i < nodeNumber; i++) {
                        for (int k = 0; k < nodeNumber; k++) {
                            System.out.print(nodesNodesEliminationArray[i][k]);
                            System.out.print("\t");
                        }
                        System.out.println();
                    }

                    if (nodeListToEliminate.indexOf(currentNode) != -1)
                        nodeListToEliminate.remove(nodeListToEliminate.indexOf(currentNode));
                    if (nodeListToEliminate.size() == 0)
                        break;
                    currentNode = nextNode;

                    System.out.println(nodeListToEliminate.size());
                    System.out.println(nodeListToEliminate);

                    break;
                }
                else {
                    if (j == nodeNumber - 1) {
                        isNodesNodesEliminationArrayEmpty = true;
                        break;
                    }
                }
            }

            // ftiakse na to kanei meta apo to last kombo
            // na mporei sigoura kapws na dei ksana ton starting

            if (nodeListToEliminate.size() == 0) {
                return true;
            }
//            for (int i = 0; i < nodeNumber; i++) {
//                for (int j = 0; j< nodeNumber; j++) {
//                    if (nodesNodesEliminationArray[i][j] == 0) {
//                        isNodesNodesEliminationArrayEmpty = false;
//                        break;
//                    }
//                }
//            }

        } while (!isNodesNodesEliminationArrayEmpty);


        // if at graph it is not possible to visit all nodes from one starting point
        // then reset all the variables of the graph
        System.out.println("Not a good graph 2");
        nodes.clear();
        edges.clear();

        Node node = new Node();
        node.reset();
        Edge edge = new Edge();
        edge.reset();

        arrayNodesEdges = null;

        return false;
    }

    private void fillArrayNodesEdges(int rows, int columns, int k) {
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
        // test reasons
        if (k == rows - 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(arrayNodesEdges[i][j]);
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }

    private void fillArrayNodesNodes(int n, int k) {
        nodes.forEach(node -> {
            edges.forEach(
                    edge -> {
                        node.getNearestNodesList().forEach(
                                nearestNode -> {
                                    if (edge.getEdgeEndingNode().equals(nearestNode.getNodeId())
                                            && edge.getEdgeStartingNode().equals(node.getNodeId())) {
                                        arrayNodesNodes[nodes.indexOf(node)][nodes.indexOf(nearestNode)] = 1;
                                    }
                                }
                        );
                    }
            );
        });

        // test reasons
        // System.out.println(nodes.get(14).getNearestNodesList());

        // test reasons
        if (k == n - 1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(arrayNodesNodes[i][j]);
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }
}