package GraphCreator;

import java.util.ArrayList;

public class GraphCreator {
    // List of nodes
    ArrayList<Node> nodes = new ArrayList<Node>();

    public void createGraph(int nodeNumber) {
        for (int i = 0; i < nodeNumber; i++) {

            // create and add the new nodes to the list
            Node newNode = new Node();
            nodes.add(newNode);
        }

        // show the list for test
        for (int j=0; j < nodes.size(); j++)
        System.out.println("The nodes list: " + nodes.get(j).getNodeId());
    }
}
