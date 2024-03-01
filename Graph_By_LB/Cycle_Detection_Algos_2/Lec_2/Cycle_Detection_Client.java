package Cycle_Detection_Algos_2.Lec_2;

public class Cycle_Detection_Client {

    public static void main(String[] args) {

        // ------- TestCase-1 -------
        int TotalVertices = 4;
        int [][]edges = {{0,1},{0,2},{1,2},{2,0},{2,3},{3,3}};


        // TestCase-2
//        int TotalVertices = 6;
//        int [][]edges = {{0,1}, {0,2} , {1,3} ,{4,1},{4,5},{5,3}};


        // ------- TestCase-3 -------
//        int TotalVertices = 4;
//        int [][]edges = {{0,1},{0,2},{1,2},{2,3}};

        Cycle_Detection_Directed_Graph_DFS g = new Cycle_Detection_Directed_Graph_DFS();

        String ans = g.detectCycleInDirectedGraph(TotalVertices , edges);

        System.out.println(ans);

    }
}
