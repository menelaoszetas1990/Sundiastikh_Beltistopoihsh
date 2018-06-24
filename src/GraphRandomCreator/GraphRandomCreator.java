package GraphRandomCreator;

import Edge.Edge;
import Node.Node;
import RandomGenerator.RandomGenerator;

import java.util.ArrayList;

public class GraphRandomCreator {
    // List of Nodes
    public ArrayList<Node> nodes = new ArrayList<Node>();
    // List of Edges
    public ArrayList<Edge> edges = new ArrayList<Edge>();
    // Array of Nodes to Nodes
    public int[][] arrayNodesEdges;
    // Array of Nodes to edges
    public int[][] arrayNodesNodes;
    // List of lists of nearby Nodes that a Node can go (adjacency list)
    private ArrayList<ArrayList<Node>> adjacencyArrayList = new ArrayList<ArrayList<Node>>();

    // main method that creates a Graph
    public void createGraph(int nodeNumber) {

        // flag boolean variable that will be true only when the graph is minimally connected
        boolean completeGraph = false;

        // repetition to create the Graph
        do {
            // repetition to create the number of nodes asked from the user
            for (int i = 0; i < nodeNumber; i++) {
                // create a Node
                Node newNode = new Node();
                // add the new Nodes to the Nodes list
                nodes.add(newNode);
            }

            // create for each Node, Edges that start from that Node and end up to another Node
            for (int i = 0; i < nodeNumber; i++) {
                // create an ArrayList with all the possible indexes of nodes that the Node can connect.
                // Do not include the index of the Node that matches that Node because it is useless
                ArrayList<Node> possibleEndNodes = new ArrayList<Node>();
                for (int j = 0; j < nodeNumber; j++) {
                    // skip the Node that the list is created for
                    if (j != i) {
                        possibleEndNodes.add(nodes.get(j));
                    }
                }

                // create a random number of Nodes that the specific Node will connect to
                // This number will be at least 1 and smaller or equal to the total number of possible
                // Nodes that it can connect to (nodesNumber -1) because in order to have minimally connected
                // Graph at least one edge has to start from every Node
                int numbersOfNodesToConnect = new RandomGenerator().generateNumber(possibleEndNodes.size());
                numbersOfNodesToConnect = new RandomGenerator().generateNumber(4);

                // repetition till the generated number to create and add new Edges from the specific Node
                while (numbersOfNodesToConnect > 0) {
                    // create a random number of node that the specific node will connect to.
                    // that number will be transformed to an index number (add to it -1)
                    int randomNode = new RandomGenerator().generateNumber(possibleEndNodes.size());

                    // decrease the number of nodes to connect to
                    numbersOfNodesToConnect--;

                    // create a new Edge with a random cost, starting from the Specific Node
                    // and ending at a random one from the list of possible Nodes indexes created above (possibleEndNodes)
                    // Because it is asked to give the Nodes id, getNodeId() is used for every starting and ending Node
                    Edge newEdge = new Edge(new RandomGenerator().generateNumber(), nodes.get(i).getNodeId(), possibleEndNodes.get(randomNode - 1).getNodeId());

                    // add to the specific Node the nodes it connects to
                    nodes.get(i).setNearestNodesList(possibleEndNodes.get(randomNode - 1));

                    // remove the already used Node index from the list
                    possibleEndNodes.remove((randomNode - 1));

                    // add the new Edge at Edges List
                    edges.add(newEdge);
                }
            }

            // create the Node - Edges array with the appropriate sizes
            arrayNodesEdges = new int[nodeNumber][edges.size()];
            // fill the Node - Edges array
            fillArrayNodesEdges(nodeNumber, edges.size());

            // create the Nodes - Nodes array with the appropriate sizes
            arrayNodesNodes = new int[nodeNumber][nodeNumber];
            // fill the Nodes - Nodes array
            fillArrayNodesNodes(nodeNumber);


            // console printing of the Nodes - Nodes array
            System.out.println("------------------------------------------------------------");
            System.out.println("This is the nodes - nodes final table. Ignore all the above");
            for (int i = 0; i < nodeNumber; i++) {
                for (int j = 0; j < nodeNumber; j++) {
                    System.out.print(arrayNodesNodes[i][j]);
                    System.out.print("\t");
                }
                System.out.println();
            }

            // in order to secure that Graph is minimally connected 2 tests need to be passed
            // both test methods return a boolean
            // first test ensures that all Nodes accept at least 1 edge
            completeGraph = checkGraph1(nodeNumber, edges.size());

            // if 1st test is passed 2nd test takes place
            if (completeGraph) {
                // second test ensures that the raph is minimally connected
                completeGraph = checkGraph2(nodeNumber);
            }

            // if both test pass then the creation of the raph is complete
        } while (!completeGraph);



        // console printing of the Edges list
        edges.forEach(
                edge -> System.out.println(edge)
        );

        // list of adjacency
        nodes.forEach(
                node -> adjacencyArrayList.add(node.getNearestNodesList())
        );
    }

    // 1st test to ensure that Graph is minimally connected
    private boolean checkGraph1(int rows, int columns) {
        boolean minimallyConnectedGraph;
        for (int i = 0; i < rows; i++) {
            minimallyConnectedGraph = false;

            // check if the specific i Node accepts an edge from other nodes
            for (int j = 0; j < columns; j++) {
                if (arrayNodesEdges[i][j] == -1) {
                    minimallyConnectedGraph = true;
                }
            }

            // if at least one Node fails to pass the 1st test reset the Graph
            if (!minimallyConnectedGraph) {
                // clear the lists
                nodes.clear();
                edges.clear();
                adjacencyArrayList.clear();

                // reset the Node class variables
                Node node = new Node();
                node.reset();

                // reset the Edge class variables
                Edge edge = new Edge();
                edge.reset();

                // empty the array of NodeEdges
                arrayNodesEdges = null;
                // empty the array of Nodes - Nodes
                arrayNodesNodes = null;

                return false;
            }
        }
        return true;
    }

    // 2nd test to ensure that Graph is minimally connected
    private boolean checkGraph2(int nodeNumber) {

        // creation of variables to keep the corresponding Nodes
        Node startingNode, previousNode, currentNode, nextNode;
        // boolean variable that becomes true only when a row of Nodes - Nodes array is full of 0
        boolean isNodesNodesEliminationArrayRowEmpty = false;
        // list of Nodes that must be passed from the test. If that list is empty then stage one
        // of the minimally connected Graph is passed
        ArrayList<Node> nodeListToEliminate = (ArrayList<Node>) nodes.clone();
        // create a clone of Nodes - Nodes array so as not to override it
        int[][] nodesNodesEliminationArray = arrayNodesNodes.clone();

        // Assuming the start is form the 1st node.
        // Success of test is as soon us all Nodes are passed and we get back to the starting Node
        startingNode = nodes.get(0);
        previousNode = nodes.get(0);
        currentNode = nodes.get(0);
        do {
            int counter = 0;
            int index = 0;
            // repetition for all the Nodes following the 1st path that will be available
            while (!isNodesNodesEliminationArrayRowEmpty) {
                // if there is a connection from the current Node to another one then ...
                if (nodesNodesEliminationArray[nodes.indexOf(currentNode)][index] == 1) {

                    counter = 0;
                    // assign the next Node based on the index that was found the 1 declaring the possible connection
                    nextNode = nodes.get(index);

                    // if there is a return to a previous Node (ex A -> B -> A) overpass it
                    if (nextNode == previousNode) {
                        // close that possible connection to avoid infinite repetition
                        nodesNodesEliminationArray[nodes.indexOf(currentNode)][index] = 0;
                        index++;
                        // if index is at the top limit return it to 0
                        if (index == nodeNumber)
                            index = 0;
                        // break the repetition so a new Node connection lookup starts
                        break;
                    }

                    // close that possible connection to avoid infinite repetition
                    nodesNodesEliminationArray[nodes.indexOf(currentNode)][index] = 0;

                    // remove from the list of Nodes that must be crossed the Node
                    // that was just crossed if it exists there
                    if (nodeListToEliminate.indexOf(currentNode) != -1)
                        nodeListToEliminate.remove(currentNode);

                    // if the list of Nodes that must be crossed is empty then break the
                    // repetition, the first stage is complete
                    if (nodeListToEliminate.size() == 0)
                        break;

                    // set the new corresponding Node values
                    previousNode = currentNode;
                    currentNode = nextNode;

                    // if a connection from the Node is not found then ...
                } else {
                    // increase a counter
                    counter++;
                    // if counter reaches the max number of nodes and still no connection is found
                    // then test failed because we are stack at a Node
                    if (counter == nodeNumber) {
                        isNodesNodesEliminationArrayRowEmpty = true;
                    }
                }

                // proceed to the next Node
                index++;
                // if index is at the top limit then return it to 0
                if (index == nodeNumber)
                    index = 0;
            }

            // if the list of Nodes that must be crossed is empty then proceed
            // to the next stage of the test
            if (nodeListToEliminate.size() == 0) {
                // the final stage checks if the last Node from stage 2 can connect to the starting Node
                // in order to secure that the Graph is minimally connected completely
                boolean anyNodeCanBeConnectedToAnyNode = checkGraph3(currentNode, startingNode, nodeNumber);
                // if stage 2 is passed then return true that all tests are passed
                if (anyNodeCanBeConnectedToAnyNode) {
                    return true;
                }
            }

        } while (!isNodesNodesEliminationArrayRowEmpty);


        // if at graph it is not possible to visit all nodes from one starting point
        // then reset all the variables of the graph
        // clear the lists
        nodes.clear();
        edges.clear();
        adjacencyArrayList.clear();

        // reset the Node class variables
        Node node = new Node();
        node.reset();

        // reset the Edge class variables
        Edge edge = new Edge();
        edge.reset();

        // empty the array of NodeEdges
        arrayNodesEdges = null;
        // empty the array of Nodes - Nodes
        arrayNodesNodes = null;

        return false;
    }

    // 2nd test stage 2 ensures that the Graph is minimally connected completely
    // It is needed for the last Node of stage 1 to connect to the starting Node
    private boolean checkGraph3(Node lastNode, Node startNode, int nodeNumber) {

        // the same procedure as stage 1 is held
        int[][] nodesNodesEliminationArray = arrayNodesNodes.clone();
        Node currentNode, nextNode, previousNode;

        boolean startAndEndNodesCanConnect = false;

        currentNode = lastNode;
        previousNode = lastNode;

        int counter = 0;
        int index = 0;
        while (true) {
            if (nodesNodesEliminationArray[nodes.indexOf(currentNode)][index] == 1) {

                counter = 0;
                nextNode = nodes.get(index);

                // if a connection is found then break the repetition and return true
                if (nextNode == startNode) {
                    startAndEndNodesCanConnect = true;
                    break;
                }

                if (nextNode == previousNode) {
                    nodesNodesEliminationArray[nodes.indexOf(currentNode)][index] = 0;
                    index++;
                    if (index == nodeNumber)
                        index = 0;
                    break;
                }

                nodesNodesEliminationArray[nodes.indexOf(currentNode)][index] = 0;

                previousNode = currentNode;
                currentNode = nextNode;

                // if dead end is reached then return that test failed
            } else {
                counter++;
                if (counter == nodeNumber)
                    break;
            }
            index++;
            if (index == nodeNumber)
                index = 0;
        }

        // return the result of the test
        return startAndEndNodesCanConnect;
    }

    // method to fill the array of Nodes - Edges
    // A lookup at each edge is done and an 1 is assign at its starting Node
    // A lookup at each edge is done and a -1 is assign at its ending Node
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
    }

    // method to fill the array of Nodes - Nodes
    // A lookup at each edge is done and an 1 is assigned at the corresponding
    // place of the array where the startingNode and the endNode of the edge meet
    private void fillArrayNodesNodes(int n) {
        nodes.forEach(node ->
            edges.forEach(
                edge ->
                    node.getNearestNodesList().forEach(
                        nearestNode -> {
                            if (edge.getEdgeEndingNode().equals(nearestNode.getNodeId())
                                && edge.getEdgeStartingNode().equals(node.getNodeId())) {
                                    arrayNodesNodes[nodes.indexOf(node)][nodes.indexOf(nearestNode)] = 1;
                            }
                        }
                    )

            )
        );
    }
}