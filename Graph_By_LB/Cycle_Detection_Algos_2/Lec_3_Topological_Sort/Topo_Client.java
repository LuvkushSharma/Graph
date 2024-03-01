package Cycle_Detection_Algos_2.Lec_3_Topological_Sort;

import java.util.ArrayList;

public class Topo_Client {

    public static void main(String[] args) {

        Topo g = new Topo();

        // TestCase-1
//        int TotalVertices = 4;
//        int TotalEdges = 4;
//        int [][]edges = {{0,1},{0,3},{1,2},{3,2}};

        // TestCase-2
        int TotalVertices = 6;
        int TotalEdges = 6;
        int [][]edges = {{5,0},{5,2},{2,3},{1,3},{4,1},{4,0}};

        ArrayList<Integer> ans = g.topologicalSort(edges , TotalVertices , TotalEdges);

        for (int val : ans)
            System.out.print (val + " ");
    }
}
