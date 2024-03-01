package Graph_Basics_1.DFS;

import java.util.ArrayList;

public class Graph_Client {

    public static void main(String[] args) {

        Graph g = new Graph();


        // ------------- Input for Connected graph ---------

        int n = 4; // ----> Number of vertices
        int m = 4; // ----> Edges

        int edges[][] = {{0 , 1} , {0 , 3} , {1 , 2} , {2 , 3}};


        /*

        // ------------- Input for Disconnected graph ---------

        int n = 4; // ----> Number of vertices
        int m = 3; // ----> Edges

        int edges[][] = {{0 , 1} , {0 , 3} , {1 , 3}};


         */

        ArrayList ans = g.DFS(n , edges);

        // Printing BFS
        for (int i = 0 ; i < ans.size() ; i++) {

            System.out.print (ans.get (i) + " ");
        }
    }
}
