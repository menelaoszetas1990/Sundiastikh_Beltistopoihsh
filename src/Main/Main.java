package Main;

import Algorithms.Dijkstra.Dijkstra;
import Algorithms.FloydWarshall.FloydWarshall;
import Graph_Creators.GraphRandomCreator.GraphRandomCreator;
import Nodes_Edges.Edge.Edge;
import Nodes_Edges.Node.Node;

import java.util.Scanner;

// main program
public class Main {

    public static void main(String[] args) {
        // Scanner variable for user input
        Scanner userInput = new Scanner(System.in);

        // user choice variable
        int userChoice = -1;

        // repetition for user to see the menu and pick his choice
        do {
            // menu for the user
            System.out.println("\n----------------------------------------");
            System.out.println("|                 MENU                 |");
            System.out.println("----------------------------------------");
            System.out.println("0) Exit");
            System.out.println("1) Dijkstra (For the given graph)");
            System.out.println("2) Floyd-Warshall  (For the given graph)");
            System.out.println("3) Random Graph Generator");
            System.out.println();
            System.out.println("Please give your choice (0 to 3):");

            // use of try ... catch for user's bad input and to avoid exit on error
            try {
                // if user does not enter an int an exception will be thrown and repetition will start over
                userChoice = userInput.nextInt();
                // if user enters an invalid int then throw an exception and repetition will start over
                if (userChoice < 0 || userChoice > 3) {
                    throw new Exception();
                }

                // do some actions based on user's choice
                switch (userChoice) {
                    // solve the Dijkstra based on the Graph that is created at GraphCreator class
                    case 1:
                        Dijkstra dijkstara = new Dijkstra();
                        dijkstara.runDijkstra();
                        // reset the Graph when a solution is found
                        resetGraph();
                        break;
                    // solve the Floyd-Warshall based on the Graph that is created at GraphCreator class
                    case 2:
                        FloydWarshall floydWarshall = new FloydWarshall();
                        floydWarshall.runFloydWarshall();
                        // reset the Graph when a solution is found
                        resetGraph();
                        break;
                    // create a graph with the characteristics the user wants
                    case 3:
                        // ask for the number of nodes of the graph
                        // number of nodes has to be at least 3 in order for the creation to have a point
                        System.out.println("\nGive the number of nodes for the graph (greater or equal to 3)");
                        // avoid bad input
                        // could be solved with exceptions also
                        while (!userInput.hasNextInt()) {
                            System.out.println("Give an integer greater or equal to 3");
                            userInput.next();
                        }
                        userChoice = userInput.nextInt();
                        // check the validity of the integer inserted
                        while (userChoice < 3) {
                            System.out.println("Give an integer greater or equal to 3");
                            while (!userInput.hasNextInt()) {
                                System.out.println("Give an integer greater or equal to 3");
                                userInput.next();
                            }
                            userChoice = userInput.nextInt();
                        }
                        int nodesNumber = userChoice;
                        // ask for the maximum number of edges that may start from each node of the graph
                        // number of edges has to be at least 2 in order for the creation to have a point
                        System.out.println("\nGive the maximum possible number of edges that may start from a node (greater or equal to 2)");
                        System.out.println("(Range from 2 to " + (nodesNumber - 1) + ")");
                        // avoid bad input
                        // could be solved with exceptions also
                        while (!userInput.hasNextInt()) {
                            System.out.println("Give an integer greater or equal to 2");
                            userInput.next();
                        }
                        userChoice = userInput.nextInt();
                        // check the validity of the integer inserted
                        while (userChoice < 2 || userChoice > (nodesNumber - 1)) {
                            System.out.println("Give an integer greater or equal to 2 and smaller or equal to " + (nodesNumber - 1));
                            while (!userInput.hasNextInt()) {
                                System.out.println("Give an integer greater or equal to 2 and smaller or equal to " + (nodesNumber - 1));
                                userInput.next();
                            }
                            userChoice = userInput.nextInt();
                        }
                        int edgesMaxNumber = userChoice;
                        // start the creation of the new graph as found at GraphRandomCreator class
                        GraphRandomCreator graphRandomCreator = new GraphRandomCreator();
                        graphRandomCreator.createGraph(nodesNumber, edgesMaxNumber);
                        // reset the Graph when a solution is found
                        resetGraph();
                        break;
                    case 0:
                        System.out.println("Exit");
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
                userInput.nextLine();
            }
        } while (userChoice != 0);
    }

    // reset graph is used to empty the nodes and edges counter in order to avoid
    // confusion when the user will pick another menu choice after different of 0
    private static void resetGraph() {
        Node node = new Node(0);
        node.reset();
        Edge edge = new Edge();
        edge.reset();
    }
}
