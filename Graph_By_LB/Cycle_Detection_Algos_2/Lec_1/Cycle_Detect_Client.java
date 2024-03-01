package Cycle_Detection_Algos_2.Lec_1;

public class Cycle_Detect_Client {

    public static void main(String[] args) {

       // Cycle_detection_in_Undirected_Graph_BFS g = new Cycle_detection_in_Undirected_Graph_BFS();
        Cycle_detection_in_Undirected_Graph_DFS g = new Cycle_detection_in_Undirected_Graph_DFS();

       int n = 3;
       int [][]edges = {{1,2},{2,3},{1,3}};
       int m = edges.length;

       String ans = g.cycleDetection(edges , n , m);
       System.out.println(ans);
    }
}
