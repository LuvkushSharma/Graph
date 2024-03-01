package Lec_2.Dijkstra_Algo___3;

public class Graph_Client {

    public static void main(String[] args) {

        Graph g = new Graph(7);

        g.AddEdge(1, 4, 2);
        g.AddEdge(1, 2, 4);
        g.AddEdge(2, 3, 5);
        g.AddEdge(3, 4, 1);
        g.AddEdge(4, 5, 6);
        g.AddEdge(5, 6, 11);
        g.AddEdge(7, 5, 1);
        g.AddEdge(6, 7, 3);

        g.Dijkstra(1);

    }
}
