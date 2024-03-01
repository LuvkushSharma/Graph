package Shortest_Path_Basics_3._1_Shortest_Path_in_Undirected_Graph;

import java.util.ArrayList;

public class Shortest_Path_Undirected_Client {

    public static void main(String[] args) {

        Shortest_Path_Undirected g = new Shortest_Path_Undirected();

        int TotalVertices = 8;
        int TotalEdges = 9;

        int [][]edges = {{1,2},{1,4},{1,3},{2,5},{5,8},{4,6},{6,7},{3,8},{8,7}};

        int source = 5;
        int destination = 7;

        ArrayList<Integer> ans = g.shortestPath(edges , TotalVertices , TotalEdges , source , destination);

        for (int val : ans)
            System.out.print (val + " ");
    }
}
