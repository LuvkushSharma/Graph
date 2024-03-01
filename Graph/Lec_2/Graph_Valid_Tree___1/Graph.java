package Lec_2.Graph_Valid_Tree___1;

/*

   All the trees are graph but not all the graphs are trees.

   Given a Graph and we have to prove whether a graph is tree or not.

   A graph can be tree when :
     1. Graph has no cycle
     2. Single Component

 */

/*

Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false


Given , list of edges so, we have to form a Adjacency lists from it.

edges  = [[0,1], [0,2], [0,3], [1,4]]

their is a edge b/w 0 and 1 so, we have to form a edge from 1 to 0 also as graph is undirected.



 */

import java.util.*;

public class Graph {

    public static void main(String[] args) {

        int edges[][] = {{0 , 1} , {0 , 2} , {0 , 3} , {1 , 4}};
        boolean ans = createAdjacencyList (5 , edges);

        System.out.println(ans);
    }

    public static boolean createAdjacencyList (int n , int edges[][]) {

        HashMap <Integer , List<Integer>>  adj = new HashMap<>();

        for (int i = 0 ; i < n ; i++) {

            adj.put (i , new ArrayList<>());
        }

        for (int i = 0 ; i  < edges.length ; i++) {

            // edges contains 2 columns
            int a = edges[i][0];
            int b = edges[i][1];

            adj.get (a).add (b);
            adj.get (b).add (a);
        }

        return validTree (adj);
    }

    public static boolean validTree (HashMap <Integer , List<Integer>>  adj) {

        HashSet <Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        int countComponents = 0;

        // -------------> BFT

        for (int src : adj.keySet()) {

            // Pehle se visited Component ke liye dubaara mat chalaao.
            if (visited.contains (src)) {

                continue;
            }

            countComponents += 1;

            q.add (src);

            while (!q.isEmpty()) {

                int rv = q.poll();

                // ignore
                // Cycle exists i.e. return false
                if (visited.contains (rv)) {

                    return false;
                }

                visited.add(rv);

                // Add unvisited neighbours of rv
                for (int nbrs : adj.get(rv)) {

                    if (!visited.contains(nbrs)) {

                        q.add (nbrs);
                    }
                }

            }
        }

        // If more than 1 component then it will return false
        return countComponents == 1;
    }
}
