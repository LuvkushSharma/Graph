package Lec_1_Undirected_Graph.DFT_5;

import java.util.*;

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



    // DFT : Puure graph par chalta hain
    // Whereas DFS works for a single component.
    public void DFT () {

        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> st = new Stack<>();

        for (int src : map.keySet()) {

            // 1 ke liye chalne ke baad 2 ke liye nhi chalega , 3 , 4 ke liye bhi nhi chalge then 5 ke liye chalga.
            if (visited.contains(src)) {
                continue;
            }

            st.push (src);

            while (!st.isEmpty()) {

                // 1. remove
                int rv = st.pop();

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

                        st.push (nbrs);
                    }
                }
            }

        }

        System.out.println();
    }
}
