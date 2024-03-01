package Lec_2.Is_Graph_Bipartite__2;

public class Graph_Client {

    public static void main(String[] args) {

        Graph g = new Graph();

        // int graph[][] = {{1 , 2 , 3} , {0 , 2} , {0 , 1 , 3} , {0 , 2}};

        // Even length cycle
        int graph[][] = {{1,3} , {0,2} , {1,3} , {0,2}};

        // Odd length cycle
        // int graph[][] = {{1,4} , {0,2} , {1,3} , {2,4} ,{0,3}};

        boolean ans = g.isBipartite(graph);

        System.out.println(ans);
    }
}
