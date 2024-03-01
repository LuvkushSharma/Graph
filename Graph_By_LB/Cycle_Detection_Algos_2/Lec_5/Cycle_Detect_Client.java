package Cycle_Detection_Algos_2.Lec_5;

public class Cycle_Detect_Client {

    public static void main(String[] args) {

        Cycle_Detection_in_Directed_Graph_Using_BFS g = new Cycle_Detection_in_Directed_Graph_Using_BFS();

        int TotalVertices = 5;
        int [][]edges = {{1,2},{4,1},{2,4},{3,4},{5,2},{1,3}};

        boolean ans = g.detectCycleInDirectedGraph(TotalVertices , edges);

        String toPrint = ans ? "Cycle is present" : "Cycle is not present";

        System.out.println(toPrint);
    }
}
