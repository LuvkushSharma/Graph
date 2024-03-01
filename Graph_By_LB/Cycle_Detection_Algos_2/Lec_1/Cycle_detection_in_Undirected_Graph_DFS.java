package Cycle_Detection_Algos_2.Lec_1;

/*



Cycle detection in Undirected Graph Using BFS

Let's say we have a graph having two components C1 and C2 i.e graph is disconnected.


   1----- 2             4 ------- 5 ------ 6
          |                       |        |
          |                       7 ------ 8
          3                                 \
                                             9

       C1                            C2

    |                                          |
    --------------------------------------------
                      |
             Combined to give a graph (G)


  Dry Run :-

  Pehle 1 par gaye kya cycle present hain --> Nhi
  Fir 2 par gaye kya cycle present hain --> Nhi
  Fir 3 par gaye kya cycle present hain --> Nhi aur component khatam ho gaya.

  Agle Component kii pehl node par gaye i.e 4 kay cycle present hain ---> Nhi.
  Fir 5 par gaye kya cycle present hain --> Nhi
  Fir 6 par gaye kya cycle present hain --> Nhi
  Fir 8 par gaye kya cycle present hain --> Nhi
  Fir 9 par gaye kya cycle present hain --> Nhi

  Fir 9 se return hue 8 par aur 8 se gaye 7 par.
  Kya cycle present hain nhi.
  Fir 7 se jab 5 par jaane kii kosis kari toh dekha kii 5 toh already visited hain and 7 kaa parent bhi nhi hain.
  8 is the parent of 7. It means cycle is present.


  Run-1 :

      Parent                  Visited                  Function Call(C-1)       Function Call(C-2)
      1 ---> -1               1 ---> true               DFS(1,-1)
      2 ---> 1                2 ---> true               DFS(2,1)
      3 ---> 2                3 ---> true               DFS(3,2)

      4 ---> -1               4 ---> true                                          DFS(4,-1)
      5 ---> 4                5 ---> true                                          DFS(5,4)
      6 ---> 5                6 ---> true                                          DFS(6,5)
      8 ---> 6                8 ---> true                                          DFS(8,6)------> After returning to 8 DFS(7,8)
      9 ---> 8                9 ---> true                                             |                                     |
      7 ---> 8                7 ---> true                                             V                                     V
                                                                                   DFS(9,8)                              DFS(5,7)
                                                                                    return                               5 is already Visited
                                                                                                                         and 5 is not the parent of 7.
                                                                                                                         return true







 */

import java.util.*;

public class Cycle_detection_in_Undirected_Graph_DFS {

    private boolean isCyclicDFS(int node ,  int parent  , HashMap<Integer, Boolean> visited , HashMap<Integer, List<Integer>> adj){

        visited.put (node , true);

        for(int neighbour : adj.get(node)){

            if(!visited.get (node)){

                boolean cycleDetected = isCyclicDFS(neighbour , node , visited , adj);

                if(cycleDetected){

                    return true;

                }
            }else if(neighbour != parent){ // neighbour visited hain already checked by the above if.

                // Cycle present
                return true;
            }
        }

        return false;

    }

    public String cycleDetection (int [][]edges , int n , int m){

        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // --------- Adjacency List Creation -----------
        // vertices are labelled from 1 to "N".
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

                boolean ans = isCyclicDFS(i , -1 , visited , adj);

                if(ans){

                    return "YES";
                }

            }

        }

        // --------- At the end after traversing all the nodes if we are unable to get the cycle in the graph then --------

        return "NO";

    }
}
