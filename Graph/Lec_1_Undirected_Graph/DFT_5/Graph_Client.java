package Lec_1_Undirected_Graph.DFT_5;


public class Graph_Client {

    public static void main(String[] args) {

        // sending number of vertices
        Graph g = new Graph(10);

        g.AddEdge(1, 4, 6);
        g.AddEdge(1, 2, 10);
        g.AddEdge(2, 3, 7);
        g.AddEdge(3, 4, 5);
        g.AddEdge(4, 5, 1);
        g.AddEdge(5, 6, 4);
        g.AddEdge(7, 5, 2);
        g.AddEdge(6, 7, 3);

        // Different component of a graph.
        g.AddEdge(8,9,40);
        g.AddEdge(9,10,34);
        g.AddEdge(10,8,21);

        g.DFT();
    }
}
