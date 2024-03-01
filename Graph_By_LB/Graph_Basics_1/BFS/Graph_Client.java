package Graph_Basics_1.BFS;

/*

----> Input-1 :

Sample Input 1:
4 4
0 1
0 3
1 2
2 3

Sample Output 1:
0 1 3 2

Ex_1.png

Sample Input 2:
4 3
0 1
0 3
1 3

Sample Output 2:
0 1 3 2

Disconnected_graph.png

 */



import java.util.ArrayList;

public class Graph_Client {

    public static void main(String[] args) {

        Graph g = new Graph();

        int n = 9;
        int edges[][] = {{0 , 8}, {1 , 6} , {1 , 7} , {1 , 8} , {5 , 8} , {6 , 0} , {7 , 3} , {7 , 4} , {8 , 7} , {2 , 5}};

        /*

        // ------------- Input for Connected graph ---------

        int n = 4; // ----> Number of vertices
        int m = 4; // ----> Edges

        int edges[][] = {{0 , 1} , {0 , 3} , {1 , 2} , {2 , 3}};


         */


        /*

        // ------------- Input for Disconnected graph ---------

        int n = 4; // ----> Number of vertices
        int m = 3; // ----> Edges

        int edges[][] = {{0 , 1} , {0 , 3} , {1 , 3}};


         */

        ArrayList ans = g.BFS(n , edges);

        // Printing BFS
        for (int i = 0 ; i < ans.size() ; i++) {

            System.out.print (ans.get (i) + " ");
        }
    }
}
