package Lec_1_Undirected_Graph.DFS_3;

import java.util.*;

/*

  1. remove
  2. Ignore
  3. Visited
  4. Self work
  5. insert unvisited neighbours


  Same work is done but in this case we insert nbrs in the stack instead of queue.

  In , this case if visited element again comes then it means Cycle is present.


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

    public boolean dfs (int src , int des) {

        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> st = new Stack<>();

        st.push(src);

        while (!st.isEmpty()) {

            // 1. remove
            int rv = st.pop();

            // 2. Ignore , if already visited
            if (visited.contains (rv)) {
                continue;
            }

            // 3. marked visited
            visited.add (rv);

            // 4. self work
            if (rv == des) {

                return true;
            }

            // 5. add unvisited neighbours
            for (int nbrs : map.get(rv).keySet()) {

                if (!visited.contains (nbrs)) {

                    st.push (nbrs);
                }
            }
        }

        // Agar loop se bahaar aya then it means path nhi thaa.
        return false;
    }
}
