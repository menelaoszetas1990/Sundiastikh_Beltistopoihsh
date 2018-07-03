package Algorithms.FloydWarshall;

import Graph_Creators.GraphCreator.GraphCreator;
import Nodes_Edges.Node.Node;

import java.util.Scanner;

public class FloydWarshall {
    private static Scanner userInput = new Scanner(System.in);

    // declare the distances array that will be used to calculate
    // the minimum distance of a pair of nodes
    private static int [][] distances;

    // declare the path array that will be used to keep track of the
    // node used to connect the pair of nodes that is queried
    private static Node [][] path;

    // INF stands for infinite and it is hardcoded here because the Integer.MAX_VALUE
    // caused some arithmetical problems at the program flow
    private static final int INF = 100000;

    public static void runFloydWarshall() {
        // create the graph
        GraphCreator graph = new GraphCreator();
        graph.createGraph();

        // initialize the length of distances array based on the graphs length (how many nodes exist)
        distances = new int[graph.nodes.size()][graph.nodes.size()];
        // fill the distances arrays based on the Nodes - Nodes array of the graph
        fillDistanceArray(graph);

        // initialize the length of path array
        path = new Node[graph.nodes.size()][graph.nodes.size()];
        // initialize the path array
        pathInit(graph);

        // declare the nodes that the user will choose for the Floyd-Warshall algorithm
        // starting Nodes_Edges.Node
        Node startingNode;
        // ending - target Nodes_Edges.Node
        Node endingNode;

        // ask user to pick the starting Nodes_Edges.Node
        for (int i = 1; i <= graph.nodes.size(); i++) {
            System.out.println(i + " " + graph.nodes.get(i - 1));
        }
        System.out.println("Pick a Nodes_Edges.Node number from the list above for the starting Nodes_Edges.Node:");
        int userIn;
        // check and validate what user inserted
        do {
            while (!userInput.hasNextInt()) {
                System.out.println("Give a valid integer number!");
                userInput.next();
            }
            userIn = userInput.nextInt();
            if ((userIn < 1) || (userIn > graph.nodes.size())) {
                System.out.println("Number has to be from 1 to " + graph.nodes.size());
            }
        } while (userIn < 1 || userIn > graph.nodes.size());
        startingNode = graph.nodes.get(userIn - 1);

        System.out.println("Pick a Nodes_Edges.Node number from the list above for the ending Nodes_Edges.Node:");
        // check and validate what user inserted
        do {
            while (!userInput.hasNextInt()) {
                System.out.println("Give a valid integer number!");
                userInput.next();
            }
            userIn = userInput.nextInt();
            if ((userIn < 1) || (userIn > graph.nodes.size())) {
                System.out.println("Number has to be from 1 to " + graph.nodes.size());
            }
        } while (userIn < 1 || userIn > graph.nodes.size());
        endingNode = graph.nodes.get(userIn - 1);

        // calculate all the minimum distances of all the possible pairs
        // setting the "k" node that is queried its time to check his influence
        // at the nodes connection
        for (int k = 0; k < graph.arrayNodesNodes.length; k++) {
            for (int i = 0; i < graph.arrayNodesNodes.length; i++) {
                for (int j = 0; j < graph.arrayNodesNodes.length; j++) {
                    // if a shortest distance is found then save it and update the parent
                    if (distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        path[i][j] = graph.nodes.get(k);
                    }
                }
            }
        }

        // show the results
        System.out.println("\nShortest path from " + startingNode + " to " + endingNode + " is: ");

        // create the path that was followed and show it to the user
        // Every repetition check who is the parent node according to the path array
        // The parent node changes every repetition and checks who is the newest node that has
        // to be reached and this procedure is repeated till the parent node matches the
        // starting node then the path is finished
        boolean richedStartingNode = false;
        Node parentNode = path[graph.nodes.indexOf(startingNode)][graph.nodes.indexOf(endingNode)];
        String finalResult = " -> " + endingNode.getNodeId();
        while (!richedStartingNode) {
            if (parentNode != startingNode) {
                finalResult = " -> " + parentNode.getNodeId() + finalResult;
                parentNode = path[graph.nodes.indexOf(startingNode)][graph.nodes.indexOf(parentNode)];
            }
            else {
                finalResult = parentNode.getNodeId() + finalResult;
                richedStartingNode = true;
            }
        }
        // console log the path and the cost
        System.out.println(finalResult);
        System.out.println("TotalCost: " + distances[graph.nodes.indexOf(startingNode)][graph.nodes.indexOf(endingNode)]);
    }

    // method to initialize and fill the distances array
    private static void fillDistanceArray(GraphCreator graph) {
        // all distances except the ones that are to travel to the same node
        // are set to max value (infinite)
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.size(); j++) {
                if (i != j) {
                    distances[i][j] = INF;
                }
            }
        }

        // insert all the known costs to travel from a node to another node
        // for each node find its adjacent ones and then find the edge that connects them
        // and finally insert at distances table the amount of the edge cost
        graph.nodes.forEach(node -> {
            node.getNearestNodesList().forEach(nearestNode ->
                graph.edges.forEach(edge -> {
                    if (edge.getEdgeStartingNode().equals(node.getNodeId()) && edge.getEdgeEndingNode().equals(nearestNode.getNodeId()))
                        distances[graph.nodes.indexOf(node)][graph.nodes.indexOf(nearestNode)] = edge.getEdgeCost();
                })
            );
        });
    }

    // method to initialize the path array
    private static void pathInit(GraphCreator graph) {
        for (int i = 0; i < graph.nodes.size(); i++){
            for (int j = 0; j < graph.nodes.size(); j++){
                // parent of same node is the same node :D
                if ( i == j) {
                    path[i][j] = graph.nodes.get(i);
                }
                // if the distance shows that there is an edge between the two nodes
                // then add the starting node as the parent
                else if (distances[i][j] != 0 && distances[i][j] != INF) {
                    path[i][j] = graph.nodes.get(i);
                }
                // if no path can be established then leave it null
                else {
                    // because inserting null throws an exception when there is an iteration
                    // at the path array, at the specific place an empty node (special node)
                    // is inserted based on the constructor at the Nodes class
                    path[i][j] = new Node(0);
                }
            }
        }
    }
}
