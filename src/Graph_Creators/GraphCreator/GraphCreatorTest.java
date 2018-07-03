package Graph_Creators.GraphCreator;

// class for test reasons
public class GraphCreatorTest {
    public static void main (String[] args) {
        GraphCreator graph = new GraphCreator();
        graph.createGraph();

        // show the nodes list
        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("Nodes list");
        System.out.println("-----------------------------------------------------------------------------");
        graph.nodes.forEach(node ->
                System.out.println(node)
        );

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("Edges list");
        System.out.println("-----------------------------------------------------------------------------");
        graph.edges.forEach(edge ->
                System.out.println(edge)
        );

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("Nodes Nodes array");
        System.out.println("-----------------------------------------------------------------------------");
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.size(); j++) {
                System.out.print(graph.arrayNodesNodes[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("Nodes Edges array");
        System.out.println("-----------------------------------------------------------------------------");
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.edges.size(); j++) {
                System.out.print(graph.arrayNodesEdges[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.println("Adjacency array");
        System.out.println("-----------------------------------------------------------------------------");
        graph.nodes.forEach(node -> {
                    System.out.print(node + ":\t\t");
                    node.getNearestNodesList(). forEach(adjNode->
                            System.out.print(adjNode + ",\t\t")
                    );
                    System.out.println();
                }
        );
    }
}
