package Shortest_Path_Basics_3._1_Shortest_Path_in_Undirected_Graph;

import java.util.*;

public class Shortest_Path_Undirected {

    // Time complexity is : O(N + E)
// Space complexity is : O(N + E)

// We have to find the shortest path from s to t.
// Where n is the totalVertices and m is the total edges.

    public ArrayList<Integer> shortestPath(int [][]edges , int n , int m , int s , int t){

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

        // ---------- do BFS ------------
        HashMap<Integer, Boolean> visited = new HashMap<>();
        HashMap<Integer, Integer> parent = new HashMap<>();

        // Initializing the visited hashmap
        for (int i = 1 ; i <= n ; i++)
            visited.put (i , false);

        Queue<Integer> q = new LinkedList<>();
        q.add(s); // Pushing source node in the queue

        visited.put (s , true);
        parent.put (s , -1);

        while(!q.isEmpty()){

            int frontNode = q.peek();
            q.poll();

            for(int neighbour : adj.get(frontNode)){

                if(!visited.get (neighbour)){

                    visited.put (neighbour , true);
                    parent.put (neighbour , frontNode);
                    q.add(neighbour);

                }
            }
        }

        // ----------- Prepare shortest Path -----------
        ArrayList<Integer> ans = new ArrayList<>();

        int currentNode = t; // Destination Node.
        ans.add(currentNode);

        // Loop will run till we our current Node becomes our source node.
        while(currentNode != s){

            currentNode = parent.get (currentNode);
            ans.add(currentNode);

        }

        // Since we get vector from destination to source and we want from source to destination
        // i.e reverse the vector.
        Collections.reverse(ans);

        return ans;
    }

}
