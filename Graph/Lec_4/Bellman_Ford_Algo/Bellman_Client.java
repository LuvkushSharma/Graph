package Lec_4.Bellman_Ford_Algo;

public class Bellman_Client {

    public static void main(String[] args) {

        Bellman bf = new Bellman(5);

        bf.addEdge(1, 2, 8);
        bf.addEdge(1, 3, 4);
        bf.addEdge(1, 4, 5);
        bf.addEdge(2, 5, 1);
        bf.addEdge(5, 2, 2);
        bf.addEdge(3, 4, -3);
        bf.addEdge(4, 5, 1);
        bf.bellmanford(1);
    }
}
