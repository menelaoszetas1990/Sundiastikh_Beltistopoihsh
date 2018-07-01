package Alorithms.Dijkstra;

import Graph_Creators.GraphCreator.GraphCreator;
import Nodes_Edges.Node.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/*
    Steps to solve the Alorithms.Dijkstra algorithm:
        1) Set distance to startNode to zero.
        2) Set all other distances to an infinite value.
        3) We add the startNode to the unsettled nodes set.
        4) While the unsettled nodes set is not empty we:
            a) Choose an evaluation node from the unsettled nodes set, the evaluation node should be
               the one with the lowest distance from the source.
            b) Calculate new distances to direct neighbors by keeping the lowest distance at each evaluation.
            c) Add neighbors that are not yet settled to the unsettled nodes set.
        5) Show the result
*/

public class Dijkstra {
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        // create the graph
        GraphCreator graph = new GraphCreator();
        graph.createGraph();

        // create a Map with distance from each node to each adjacent ones
        graph.nodes.forEach(node ->
                node.getNearestNodesList().forEach(nearestNode ->
                        graph.edges.forEach(edge -> {
                                    if (edge.getEdgeStartingNode().equals(node.getNodeId()) &&
                                            edge.getEdgeEndingNode().equals(nearestNode.getNodeId())) {
                                        node.addDistanceToAdjacentNodes(nearestNode, edge.getEdgeCost());
                                    }
                                }
                        )
                )
        );

        // declare the nodes that the user will chose for the Alorithms.Dijkstra algorithm
        // starting Nodes_Edges.Node
        Node startingNode;
        // ending - target Nodes_Edges.Node
        Node endingNode;

        // array list of nodes that are settled (nodes with known minimum distance from the starting node)
        ArrayList<Node> settledNodes = new ArrayList<Node>();
        // array list of nodes that are unsettled (nodes that can be reached from the starting node but distance
        // is not yet calculated)
        ArrayList<Node> unsettledNodes = new ArrayList<Node>();

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
        // System.out.println(startingNode);

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
        // System.out.println(endingNode);

        // Step one: Set distance of startNode to source to zero (it points at itself)
        startingNode.setDistanceFromSource(0);

        // Step two: Set all other distances from source to an infinite value
        for (Node node: graph.nodes) {
            if (node != startingNode) {
                node.setDistanceFromSource(Integer.MAX_VALUE);
            }
        }

        // Step three: add the startNode to the unsettled nodes list
        unsettledNodes.add(startingNode);

        // Step four:
        Node lowestDistanceNode;
        do {
            // find the nearest node with the smallest distance from the unsettled nodes list
            lowestDistanceNode = getLowestDistanceNode(unsettledNodes);
            // as soon as the node from unsettled nodes list with the smallest distance from the
            // source is found, remove it from the unsettled list
            unsettledNodes.remove(lowestDistanceNode);

            // iterate through all the nodes that the lowestDistanceNode can connect to
            for (Map.Entry<Node,Integer> adjacentPair: lowestDistanceNode.getDistanceToAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacentPair.getKey();
                Integer edgeWeight = adjacentPair.getValue();
                // check if the adjacent nodes have not already been evaluated at the unsettled nodeslist
                if (!settledNodes.contains(adjacentNode)) {
                    // if they have not add them to the unsettled nodes list for evaluation
                    // calculate their distance from the starting point
                    calculateMinimumDistance(adjacentNode, edgeWeight, lowestDistanceNode);
                    // add them to the unsettled list to evaluate later those nodes adjacent nodes
                    unsettledNodes.add(adjacentNode);
                }
            }

            // after a node has been evaluated add him to the settled nodes list
            settledNodes.add(lowestDistanceNode);

        // iterate through nodes till all have been settled (or the ones needed to)
        } while (unsettledNodes.size() != 0);

        // Step five: show the results
        System.out.println("\nShortest path from " + startingNode + " to " + endingNode + " is: ");
        // ending node is picked because all distances are calculated for all nodes
        // so they will have been calculated for that one also
        for (Node node: endingNode.getShortestPath()) {
            System.out.print(node + " -> ");
        }
        System.out.println(endingNode);
        System.out.println("Total cost: " + endingNode.getDistanceFromSource());
    }

    // get the node from unsettled nodes list with the lowest distance from the starting node
    private static Node getLowestDistanceNode(ArrayList<Node> unsettledNodes) {
        // initialize method variables so a test can take place
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        // iterate through all nodes that exist at unsettled nodes list
        for (Node node : unsettledNodes) {
            // find the node from unsettled nodes list with the smallest distance from the source
            if (node.getDistanceFromSource() < lowestDistance) {
                lowestDistance = node.getDistanceFromSource();
                lowestDistanceNode = node;
            }
        }
        // return the node from unsettled nodes list with the smallest distance from the source because he has
        // the biggest probability to be a part of the shortest path
        return lowestDistanceNode;
    }

    // calculate the distance of the node
    private static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node ascendantNode) {
        // first calculate the distance its ascendant node has from the starting node
        Integer ascendantNodeDistance = ascendantNode.getDistanceFromSource();
        // if the nodes total distance from the starting node is lower than what was
        // already calculated or lower than the initial value (infinite) then create
        // its shortest path and update its distance from the starting node
        if ((ascendantNodeDistance + edgeWeight) < evaluationNode.getDistanceFromSource()) {
            evaluationNode.setDistanceFromSource(ascendantNodeDistance + edgeWeight);
            // with the use of linked list a path can be established between the nodes
            LinkedList<Node> shortestPath = new LinkedList<>(ascendantNode.getShortestPath());
            shortestPath.add(ascendantNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
