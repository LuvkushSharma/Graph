package Shortest_Path_Basics_3._2_Shortest_Path_in_Directed_Acyclic_Graph;

public class Shortest_Path_Directed_Client {

    public static void main(String[] args) {

        int n = 6; // total number of nodes.
        Shortest_Path_Directed g = new Shortest_Path_Directed(n);

        int src = 1;

        g.addEdge(0,1,5);
        g.addEdge(0,2,3);
        g.addEdge(1,2,2);
        g.addEdge(1,3,6);
        g.addEdge(2,3,7);
        g.addEdge(2,4,4);
        g.addEdge(2,5,2);
        g.addEdge(3,4,-1);
        g.addEdge(4,5,-2);

        int ans[] = g.getShortestPath(src , n);

        for (int val : ans) {

            String s = val == Integer.MAX_VALUE ? "INF" : "" + val;

            System.out.print (s + ",");
        }

    }
}
