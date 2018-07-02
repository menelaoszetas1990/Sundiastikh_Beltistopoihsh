package Main;

import Algorithms.Dijkstra.Dijkstra;
import Algorithms.FloydMarshall.FloydMarshall;
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
            System.out.println("\n------------------------------------------------------");
            System.out.println("------------------- ΜΕΝΟΥ ΕΠΙΛΟΓΩΝ -------------------");
            System.out.println("------------------------------------------------------");
            System.out.println("1) Dijkstra (Στον γράφο που έχει παραδοθεί)");
            System.out.println("2) Floyd-Marshall  (Στον γράφο που έχει παραδοθεί)");
            System.out.println("3) Random Graph Generator");
            System.out.println("0 για έξοδο");
            System.out.println("------------------------------------------------------");
            System.out.println("Παρακλώ δώστε την επιλογή σας (1 έως 3):");

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
                    // solve the Floyd-Marshall based on the Graph that is created at GraphCreator class
                    case 2:
                        FloydMarshall floydMarshall = new FloydMarshall();
                        floydMarshall.runFloydMarshall();
                        // reset the Graph when a solution is found
                        resetGraph();
                        break;
                    // create a graph with the characteristics the user wants
                    case 3:
                        // ask for the number of nodes of the graph
                        // number of nodes has to be at least 3 in order for the creation to have a point
                        System.out.println("Δώστε αριθμό κόμβων που θέλετε να έχει ο γράφος (μεγαλύτερο ή ίσο του 3)");
                        // avoid bad input
                        // could be solved with exceptions also
                        while (!userInput.hasNextInt()) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 3");
                            userInput.next();
                        }
                        userChoice = userInput.nextInt();
                        // check the validity of the integer inserted
                        while (userChoice < 3) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 3");
                            while (!userInput.hasNextInt()) {
                                System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 3");
                                userInput.next();
                            }
                            userChoice = userInput.nextInt();
                        }
                        int nodesNumber = userChoice;
                        // ask for the maximum number of edges that may start from each node of the graph
                        // number of edges has to be at least 2 in order for the creation to have a point
                        System.out.println("Δώστε μέγιστο αριθμό ακμών που θέλετε να ξεκινούν από κάθε κόμβο (μεγαλύτερο ή ίσο του 2)");
                        System.out.println("(Από το 2 έως το " + (nodesNumber - 1) + ")");
                        // avoid bad input
                        // could be solved with exceptions also
                        while (!userInput.hasNextInt()) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 2");
                            userInput.next();
                        }
                        userChoice = userInput.nextInt();
                        // check the validity of the integer inserted
                        while (userChoice < 2 || userChoice > (nodesNumber - 1)) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 2 και μικρότερο ή ίσο του " + (nodesNumber - 1));
                            while (!userInput.hasNextInt()) {
                                System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 2 και μικρότερο ή ίσο του " + (nodesNumber - 1));
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
                        System.out.println("Έξοδος");
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("Λαθος εισαγωγή");
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
