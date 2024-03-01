package Shortest_Path_Algo_4.Dijkstra_Algo;

import java.util.ArrayList;

public class dijkstra_client {

    public static void main(String[] args) {

        dijkstra g = new dijkstra();

        // ------------------------- Testcase - 1 -----------------------
        int vertices = 5;
        int edges = 7;

        int [][]data = {{0,1,7},{0,2,1},{0,3,2},{1,2,3},{1,3,5},{1,4,1},{3,4,7}};

        // ------------------------- Testcase -2 ------------------------
        // int vertices = 4;
        // int edges = 5;
        // int [][]data = {{0,1,5},{0,2,8},{1,2,9},{1,3,2},{2,3,6}};

        // Here in this {0,1,5} 0 is u , 1 is v and 5 is the weight of the edge from u to v.

        int source = 0;

        int []ans = g.dijkstras(data , vertices , edges , source);

        for (int val : ans)
            System.out.print (val + " ");
    }
}
