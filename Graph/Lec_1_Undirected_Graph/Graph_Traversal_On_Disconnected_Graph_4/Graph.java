package Lec_1_Undirected_Graph.Graph_Traversal_On_Disconnected_Graph_4;

/*

Jab apka graph disconnected hota hain i.e. containing multiple components then we have to apply BFS on all the components

BFT : Breadth First Traversal.

Pehle BFS lagao and then we return it means ek componenet Traverse ho gaya. Now traverse from the vertex which is not visited yet.





 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*

    1 ----- 4                                       5
    |       |                                     /   \
    |       |         8 ----- 9                  /      \
    2 ------3                                  6 ------- 7

   We have 3 components.

   So, if we start from 1 then puura graph traverse ho jaayega and sabhi vertex visited ho jaayengye.

   Then , 1 , 2 , 3 , 4 visited hain i.e. dubara 5 BFS behjo and src as 5 bhej do.

 */

public class Graph {

    // Har ek Key ke corresponding Integer , Integer kaa hashmap store kar liya
    private HashMap<Integer , HashMap<Integer , Integer>> map;

    public Graph (int v) {

        this.map = new HashMap<>();

        // Step-1 :
        // Har ek Key ke corresponding ek HashMap daal diya
        for (int i = 1 ; i <= v ; i++) {

            map.put (i , new HashMap<>());
        }
    }

    public void AddEdge(int v1, int v2, int cost) {

        // Since , graph is undirected so , agar v1 se v2 tak edge hain toh baapis aane kii bhi edge banaani padegyi.
        map.get(v1).put(v2, cost); // v1 to v2 edge lag gaya
        map.get(v2).put(v1, cost); // v2 to v1 edge lag gaya
    }

    // BFT : Puure graph par chalta hain
    // Whereas BFS works for a single component.
   public void BFT () {

       HashSet<Integer> visited = new HashSet<>();
       Queue<Integer> q = new LinkedList<>();

       for (int src : map.keySet()) {

           // 1 ke liye chalne ke baad 2 ke liye nhi chalega , 3 , 4 ke liye bhi nhi chalega then 5 ke liye chalega.
           if (visited.contains(src)) {
               continue;
           }

           q.add (src);

           while (!q.isEmpty()) {

               // 1. remove
               int rv = q.poll();

               // 2. Ignore , if already visited
               if (visited.contains (rv)) {
                   continue;
               }

               // 3. marked visited
               visited.add (rv);

               // 4. Self work
               System.out.print (rv + " ");

               // 5. add unvisited neighbours
               for (int nbrs : map.get(rv).keySet()) {

                   if (!visited.contains (nbrs)) {

                       q.add (nbrs);
                   }
               }
           }

       }

       System.out.println();
   }
}
