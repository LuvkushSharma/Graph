package Lec_4.Prims_Algo;

public class Prims_Client {

    public static void main(String[] args) {

        Prims g = new Prims(7);

        g.AddEdge (1 , 2 , 2);
        g.AddEdge (1 , 4 , 5);
        g.AddEdge (2 , 3 , 3);
        g.AddEdge (4 , 3 , 4);
        g.AddEdge (4 , 5 , 7);
        g.AddEdge (5 , 6 , 1);
        g.AddEdge (5 , 7 , 6);
        g.AddEdge (6 , 7 , 8);


       int ans = g.Prims_Min_Cost();

        System.out.println (ans);
    }
}
