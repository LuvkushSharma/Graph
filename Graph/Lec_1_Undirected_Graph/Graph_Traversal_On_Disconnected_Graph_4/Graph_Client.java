package Lec_1_Undirected_Graph.Graph_Traversal_On_Disconnected_Graph_4;

public class Graph_Client {

    public static void main(String[] args) {

        // sending number of vertices
        Graph g = new Graph(7);

        g.AddEdge(1, 4, 6);
        g.AddEdge(1, 2, 10);
        g.AddEdge(2, 3, 7);
        g.AddEdge(3, 4, 5);
        g.AddEdge(4, 5, 1);
        g.AddEdge(5, 6, 4);
        g.AddEdge(7, 5, 2);
        g.AddEdge(6, 7, 3);

        g.BFT();
    }
}
