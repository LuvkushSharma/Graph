package Cycle_Detection_Algos_2.Lec_4_Kahns_Algo;

import java.util.ArrayList;

public class kahns_client {

    public static void main(String[] args) {

       kahns g = new kahns();

        int TotalVertices = 4;
        int TotalEdges = 4;
        int [][]edges = {{0,1},{0,3},{1,2},{3,2}};

        ArrayList<Integer> ans = g.topologicalSort(edges , TotalVertices , TotalEdges);

        for (int val : ans)
            System.out.print (val + " ");
    }
}
