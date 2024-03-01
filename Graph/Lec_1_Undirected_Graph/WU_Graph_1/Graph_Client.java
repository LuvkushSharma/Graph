package Lec_1_Undirected_Graph.WU_Graph_1;

import java.util.HashSet;

/*

        2             7        11
    1------------4----------- 5--- 7
    |            |            |   /
 3  |            |        13  |  / 1
    |            |            | /
    |            |            |/
    2------------3            6
         4


 */

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
        //g.display();
        // System.out.println(g.numberOfEdge());
        //g.removeEdge(4, 5);
        //g.removevertex(4);
        // g.display();

        g.printallpath(1,  6, new HashSet<>(), "");


    }
}
