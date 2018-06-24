package Dijkstra;

// http://www.baeldung.com/java-dijkstra

import GraphCreator.GraphCreator;
import Node.Node;
import Edge.Edge;

import java.util.ArrayList;
import java.util.Scanner;


// review it
/*
    Steps to solve the Dijkstra algorithm:
        1) Set distance to startNode to zero.
        2) Set all other distances to an infinite value.
        3) We add the startNode to the unsettled nodes set.
        4) While the unsettled nodes set is not empty we:
            a) Choose an evaluation node from the unsettled nodes set, the evaluation node should be
               the one with the lowest distance from the source.
            b) Calculate new distances to direct neighbors by keeping the lowest distance at each evaluation.
            c) Add neighbors that are not yet settled to the unsettled nodes set.
        5) Pick the lowest distance
*/

public class Dijkstra {
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        // create the graph
        GraphCreator graph = new GraphCreator();
        graph.createGraph();

        // declare the nodes that the user will chose for the Dijkstra algorithm
        // starting Node
        Node startingNode;
        // ending - target Node
        Node endingNode;

        // user input
        int input;

        // array list of nodes that are settled (nodes with known minimum distance from the starting node)
        ArrayList<Node> settledNodes = new ArrayList<Node>();
        // array list of nodes that are unsettled (nodes that can be reached from the starting node but distance
        // is not yet calculated)
        ArrayList<Node> unsettledNodes = new ArrayList<Node>();

        // ask user to pick the starting Node
        for (int i = 1; i <= graph.nodes.size(); i++) {
            System.out.println(i + " " + graph.nodes.get(i - 1));
        }
        System.out.println("Pick a Node number from the list above for the starting Node:");
        while (!userInput.hasNextInt()) {
            System.out.println("Give a valid integer number!");
            userInput.next();
        }
        startingNode = graph.nodes.get(userInput.nextInt() - 1);
        // System.out.println(startingNode);

        System.out.println("Pick a Node number from the list above for the ending Node:");
        while (!userInput.hasNextInt()) {
            System.out.println("Give a valid integer number!");
            userInput.next();
        }
        endingNode = graph.nodes.get(userInput.nextInt() - 1);
        // System.out.println(endingNode);

        // initialize the unsettle Nodes list
        unsettledNodes.add(startingNode);

        do {
            // find the nearest node with the smallest distance
            for (Node node : unsettledNodes) {
                Node lowestDistanceNode = null;
                int lowestDistance = Integer.MAX_VALUE;
                for (Node nearestNode: node.getNearestNodesList()) {
                    for (Edge edge : graph.edges) {
                        if (edge.getEdgeStartingNode().equals(node.getNodeId()) && edge.getEdgeEndingNode().equals(nearestNode.getNodeId())) {
                            if (edge.getEdgeCost() < lowestDistance) {
                                lowestDistance = edge.getEdgeCost();
                                lowestDistanceNode = nearestNode;
                            }
                        }
                    }
                }
            }
            // currenct node problem.. think of it...
            // unsettledNodes.remove()
        } while (unsettledNodes.size() == 0);
    }
}
