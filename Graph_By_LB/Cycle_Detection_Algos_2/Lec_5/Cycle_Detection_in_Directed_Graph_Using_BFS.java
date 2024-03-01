package Cycle_Detection_Algos_2.Lec_5;

/*

 We will use the logic of topological sort concept.
 Since topological sort is only valid for directed acyclic graph.
 So, if given graph is cyclic then it gives us invalid topological sort.

 ------------ We will use Kahn's Algorithm --------------


 Time complexity is : O(V + E)
 Space complexity is : O(V + E)

 */

import java.util.*;

public class Cycle_Detection_in_Directed_Graph_Using_BFS {

    public boolean detectCycleInDirectedGraph(int n , int [][]edges){

        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // --------- Adjacency List Creation -----------
        for (int i = 1 ; i <= n ; i++) {

            adj.put (i , new ArrayList<>());
        }

        for (int i = 0 ; i < edges.length ; i++) {

            int u = edges[i][0]; // Vertex - 1
            int v = edges[i][1]; // Vertex - 2


            adj.get(u).add(v); // edge from u to v

        }

        // --------- Find all indegrees -----------
        int []indegree = new int[n+1];

        for(int key : adj.keySet()){

            for(int j : adj.get(key)){

                indegree[j]++;
            }
        }

        // ----- 0 indegrees waalo ko push kardo -----
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n ; i++){

            if(indegree[i] == 0){

                q.add(i);

            }
        }

        // ----------- Do BFS ------------
        // Ek valid Topological sort main number of elements is equal to the number of nodes.
        int count = 0;

        while(!q.isEmpty()){

            int front = q.peek();
            q.poll();

            // Incrementing count
            count++;

            // Neighbours indegree update
            for(int neighbour : adj.get(front)){

                // Decreamenting indegrees of neighbour.
                indegree[neighbour]--;

                if(indegree[neighbour] == 0){

                    q.add(neighbour);
                }
            }


        }

        // In case of valid topological sort. count == n i.e acyclic graph.
        if(count == n){

            return false;

        }else{ // invalid topological sort i.e. cycle is present.

            return true;
        }
    }

}
