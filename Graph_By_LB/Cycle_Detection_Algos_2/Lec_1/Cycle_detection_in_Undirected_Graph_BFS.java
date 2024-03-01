package Cycle_Detection_Algos_2.Lec_1;

import java.util.*;

/*

Cycle detection in Undirected Graph Using BFS

You have been given an undirected graph with "N" vertices and "M" edges. The vertices are labelled from
1 to "N".

Your task is to find if the graph contains a cycle or not.

A path that starts from a given vertex and ends at the same vertex traversing the edges only once is called cycle.

*/


public class Cycle_detection_in_Undirected_Graph_BFS {

    private boolean isCyclic(int src , HashMap<Integer, Boolean> visited , HashMap<Integer, List<Integer>> adj){

        // ---------- Creating Parent Data Structure --------------
        // It will store the parents of a node.
        // Like parent of 2 is 1 , parent of 1 is -1 i.e null

        HashMap <Integer , Integer > parent = new HashMap<>();

        parent.put (src , -1); // Mark source node's parent as -1.
        visited.put(src , true);

        Queue<Integer> q = new LinkedList<>(); // It will store the node's data
        q.add(src); // Pushing source node for the first time.

        while(!q.isEmpty()){

            int front = q.peek(); // Accessing front node's data from the queue.
            q.poll();

            for(int neighbour : adj.get (front)){ // Accessing all the neighbours of front

                // Condition for cycle.
                if(visited.get(neighbour) == true && neighbour != parent.get(front)){

                    return true;

                }else if(!visited.get (neighbour)){ // If that node is not visited yet than push it in the queue. And mark it as visited.

                    q.add(neighbour);
                    visited.put (neighbour , true);
                    parent.put (neighbour , front);

                }

            }
        }

        return false;

    }

    public String cycleDetection (int [][]edges , int n , int m){

        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // --------- Adjacency List Creation -----------
        // vertices are labelled from -----> 1 to "N".
        for (int i = 1 ; i <= n ; i++) {

            adj.put (i , new ArrayList<>());
        }

        for (int i = 0 ; i < m ; i++) {

            int u = edges[i][0]; // Vertex - 1
            int v = edges[i][1]; // Vertex -2


            adj.get(u).add(v); // edge from u to v
            adj.get(v).add(u);  // Since graph is undirected so, their will be an edge from node2 to node1 as well.

        }

        // ---------- Tracks whether a particular node is visited or not ----------
        HashMap<Integer, Boolean> visited = new HashMap<>();

        // Initializing the visited hashmap
        for (int i = 1 ; i <= n ; i++)
            visited.put (i , false);


        // --------- To handle disconnected components ----------
        for(int i = 1; i <= n; i++){ // Covers all the nodes.

            if( !visited.get(i)){

                boolean ans = isCyclic(i , visited , adj);

                if(ans){

                    return "YES";
                }

            }

        }

        // --------- At the end after traversing all the nodes if we are unable to get the cycle in the graph then --------

        return "NO";

    }
}
