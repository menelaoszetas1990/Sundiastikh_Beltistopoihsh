package Nodes_Edges.Edge;

import RandomGenerator.RandomGenerator;

public class EdgeTest {
    public static void main (String[] args) {
        RandomGenerator randomCost = new RandomGenerator();
        Edge edge1 = new Edge(1,"a", "b");
        Edge edge2 = new Edge(randomCost.generateNumber(15),"a", "b");
        Edge edge3 = new Edge(randomCost.generateNumber(),"b", "c");

        System.out.println(edge1);
        System.out.println(edge2);
        System.out.println(edge3);
    }
}
