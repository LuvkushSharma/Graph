package Cycle_Detection_Algos_2.Lec_4_Kahns_Algo;

/*

--------------- Topological Sort using BFS traversal (Kahns Algo)

 */

import java.util.*;

public class kahns {

    ArrayList<Integer> topologicalSort(int [][]edges, int n , int e){

        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // --------- Adjacency List Creation -----------
        for (int i = 0 ; i < n ; i++) {

            adj.put (i , new ArrayList<>());
        }

        for (int i = 0 ; i < e ; i++) {

            int u = edges[i][0]; // Vertex - 1
            int v = edges[i][1]; // Vertex - 2


            adj.get(u).add(v); // edge from u to v

        }

        // --------- Find all indegrees -----------
        int []indegree = new int[n];

        for(int key : adj.keySet()){

            for(int j : adj.get(key)){

                indegree[j]++;
            }
        }

        // ----- 0 indegrees waalo ko push kardo queue main -----
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n ; i++){

            if(indegree[i] == 0){

                q.add(i);

            }
        }

        // ----------- Do BFS ------------
        ArrayList<Integer> ans = new ArrayList<>();

        while(!q.isEmpty()){

            int front = q.peek();
            q.poll();

            // ans store
            ans.add(front);

            // Neighbours indegree update
            for(int neighbour : adj.get(front)){

                // Decrementing indegrees of neighbour.
                indegree[neighbour]--;

                if(indegree[neighbour] == 0){

                    q.add(neighbour);
                }
            }

        }

        return ans;
    }
}
