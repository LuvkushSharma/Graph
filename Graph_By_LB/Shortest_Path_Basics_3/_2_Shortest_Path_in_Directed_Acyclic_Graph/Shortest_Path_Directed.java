package Shortest_Path_Basics_3._2_Shortest_Path_in_Directed_Acyclic_Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Shortest_Path_Directed {

    // Har ek Key ke corresponding ek List hogi jisme Pairs hongye. Each pair contains neighbour and weight.
    private HashMap<Integer , List<Pair>> adj;

    public Shortest_Path_Directed (int v) {

        this.adj = new HashMap<>(); // Giving space to HashMap in the heap.

        // Initializing the adjacency list
        for (int i = 0 ; i < v ; i++)
            adj.put (i , new ArrayList<>());

    }

    public void addEdge (int u , int v , int weight) {

        Pair pr = new Pair();
        pr.first = v;
        pr.second = weight;

        // adj does not contains this key
         if (!adj.containsKey(u)) {

             List<Pair> ls = new ArrayList<>();
             ls.add(pr);

             adj.put (u , ls);

         } else {

             adj.get(u).add(pr);
         }
    }

    private void dfs(int node, HashMap<Integer,Boolean> visited , Stack<Integer> topo){

        visited.put (node , true);

        for(Pair pr : adj.get(node)){

            int neighbour = pr.first; // since pr.second is the weight.

            if(! visited.get(neighbour)){

                dfs(neighbour , visited , topo);
            }
        }

        // ---- push returned node in the stack ----
        topo.push(node);
    }

    public int[] getShortestPath(int source , int n){

        // --------------- topological sort function ------------------
        HashMap<Integer,Boolean> visited = new HashMap<>();

        // Initializing the visited map
        for (int i = 0 ; i < n ; i++)
            visited.put (i , false);

        Stack<Integer> topo = new Stack<>();

        for(int i = 0; i < n ; i++){

            if(!visited.get(i)){
                dfs(i , visited , topo);
            }
        }

        int []distance = new int[n];

        for(int i = 0; i < n ; i++){

            distance[i] = Integer.MAX_VALUE; // i.e initializing distance array with infinite distances.
        }

        // -------- Initialize distance[src] = 0 ---------

        distance[source] = 0;

        while(!topo.empty()){

            int Top = topo.peek();
            topo.pop();

            if(distance[Top] != Integer.MAX_VALUE){

                // Processing starts
                for(Pair pr : adj.get(Top)){

                    // ------ Updating the distance array ---------
                    if(distance[Top] + pr.second < distance[pr.first]){

                        distance[pr.first] = distance[Top] + pr.second;
                    }
                }
            }
        }
     return distance;
    }

    private class Pair {

         int first;
         int second;

         public Pair () {}

         public Pair (int first , int second){

             this.first = first;
             this.second = second;
         }
    }

}
