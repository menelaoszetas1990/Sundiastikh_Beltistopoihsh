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

            try {
                userChoice = userInput.nextInt();
                if (userChoice < 0 || userChoice > 3) {
                    throw new Exception();
                }
                switch (userChoice) {
                    case 1:
                        Dijkstra dijkstara = new Dijkstra();
                        dijkstara.runDijkstra();
                        resetGraph();
                        break;
                    case 2:
                        FloydMarshall floydMarshall = new FloydMarshall();
                        floydMarshall.runFloydMarshall();
                        resetGraph();
                        break;
                    case 3:
                        System.out.println("Δώστε αριθμό κόμβων που θέλετε να έχει ο γράφος (μεγαλύτερο ή ίσο του 3)");
                        while (!userInput.hasNextInt()) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 3");
                            userInput.next();
                        }
                        userChoice = userInput.nextInt();
                        while (userChoice < 3) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 3");
                            while (!userInput.hasNextInt()) {
                                System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 3");
                                userInput.next();
                            }
                            userChoice = userInput.nextInt();
                        }
                        int nodesNumber = userChoice;
                        System.out.println("Δώστε μέγιστο αριθμό ακμών που θέλετε να ξεκινούν από κάθε κόμβο (μεγαλύτερο ή ίσο του 2)");
                        System.out.println("(Από το 2 έως το " + (nodesNumber - 1) + ")");
                        while (!userInput.hasNextInt()) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 2");
                            userInput.next();
                        }
                        userChoice = userInput.nextInt();
                        while (userChoice < 2 || userChoice > (nodesNumber - 1)) {
                            System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 2 και μικρότερο ή ίσο του " + (nodesNumber - 1));
                            while (!userInput.hasNextInt()) {
                                System.out.println("Εισάγετε ακέραιο μεγαλύτερο ή ίσο του 2 και μικρότερο ή ίσο του " + (nodesNumber - 1));
                                userInput.next();
                            }
                            userChoice = userInput.nextInt();
                        }
                        int edgesMaxNumber = userChoice;
                        GraphRandomCreator graphRandomCreator = new GraphRandomCreator();
                        graphRandomCreator.createGraph(nodesNumber, edgesMaxNumber);
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

    private static void resetGraph() {
        Node node = new Node(0);
        node.reset();
        Edge edge = new Edge();
        edge.reset();
    }
}
