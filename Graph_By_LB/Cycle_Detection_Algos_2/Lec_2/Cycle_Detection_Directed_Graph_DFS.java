package Cycle_Detection_Algos_2.Lec_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*

---------------- If we use Previous Logic used in Undirected Graph for cycle detection -----------------------

If we , see the given graph , so, when we reached to 3 then mark it as true and parent of 3 is 2 then we'll go it's neighbour i.e. 7 and mark
it as true and marked it's parent as 3. Since , no further parent of 7 i.e. return back.

And we again reached to 3. Since , 8 was also the neighbour of 3 which left for visiting. Now mark 8 as visited and it's parent is 3.

Now , neighbour of 8  is 7 which is visited and 7 is also not the parent of 8 it means cycle is present according to the previous logic ,
that we had used in the cycle detection of undirected graph.

Since , no cycle is present in this portion of graph but still we are getting it means previous logic will not work.

See the new logic for the directed graph in the Cycle_Detection_in_Directed_Graph_Using_DFS.png


 */

public class Cycle_Detection_Directed_Graph_DFS {

    // Time complexity is O(N + E)
    // and Space complexity is Linear.

    private boolean checkCycleDFS(int node, HashMap<Integer, Boolean> visited , HashMap<Integer, Boolean> dfsVisited ,
                                  HashMap<Integer, List<Integer>> adj){

        // Pehla kaam jo bhi node aa rahi hain usse true mark kardo.
        visited.put (node , true);
        dfsVisited.put (node , true);

        for(int neighbour : adj.get(node)){

            if(!visited.get(neighbour)){

                // Agar neighbour visited nhi hain toh aagye ke liye call kardo.
                boolean cycleDetected = checkCycleDFS(neighbour , visited , dfsVisited , adj);

                if(cycleDetected)
                    return true;


            }else if(dfsVisited.get (neighbour)){ // If node is visited and dfsVisited is also true.

                return true;
            }
        }

        // Jab sabhi neighbour visited ho gaye node ke aur ham return karengye
        // jab dfsVisited ko baapis false mark kardena.

        dfsVisited.put (node , false); // Jab bhi recursive call se baapas aaye.

        return false;

    }

    public String detectCycleInDirectedGraph(int n , int [][]edges){

        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // --------- Adjacency List Creation -----------
        for (int i = 0 ; i < n ; i++) {

            adj.put (i , new ArrayList<>());
        }

        for (int i = 0 ; i < edges.length ; i++) {

            int u = edges[i][0]; // Vertex - 1
            int v = edges[i][1]; // Vertex - 2


            adj.get(u).add(v); // edge from u to v only

        }

        // ---------- Tracks whether a particular node is visited or not ----------
        HashMap<Integer, Boolean> visited = new HashMap<>();
        HashMap<Integer, Boolean> dfsVisited = new HashMap<>();

        // Initializing the visited hashmap
        for (int i = 0 ; i < n ; i++)
            visited.put (i , false);


        // --------- Call Dfs for all Components ----------
        for(int i = 0; i < n ; i++){

            if(!visited.get(i)){

                boolean cycleFound = checkCycleDFS(i , visited , dfsVisited , adj);

                if(cycleFound){

                    return "YES";

                }
            }
        }

        return "NO";

    }
}
