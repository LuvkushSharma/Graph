package Graph_Basics_1.Implementation;

import java.util.HashMap;

public class Graph {

    // Har ek Key ke corresponding Integer , Integer kaa hashmap store kar liya
    private HashMap<Integer , HashMap<Integer , Integer>> adj;

    // Direction = false ----> undirected graph
    // Direction = true ----> directed graph
    private boolean isDirected = false;

    public Graph (int v , boolean direction) {

        this.adj = new HashMap<>(); // Giving space to HashMap in the heap.
        this.isDirected = direction;

    }

    public void AddEdge(int v1, int v2 , int cost) {

        // It means first time mapping of v1
        if (adj.containsKey(v1) == false) {

            HashMap<Integer , Integer> nbr = new HashMap<>();

            nbr.put (v2 , cost);
            adj.put (v1 , nbr);

        } else {

            // Since , graph is undirected so , agar v1 se v2 tak edge hain toh baapis aane kii bhi edge banaani padegyi.
            adj.get(v1).put(v2, cost); // v1 to v2 edge lag gaya
        }


        // i.e. if we want a directed graph then in that case no path from v2 to v1.

        // So, edge from v2 to v1 only forms if graph is undirected.
        if (!this.isDirected) {

            // It means first time mapping of v2
            if (adj.containsKey(v2) == false) {

                HashMap<Integer , Integer> nbr = new HashMap<>();

                nbr.put (v1 , cost);
                adj.put (v2 , nbr);

            } else {

                adj.get(v2).put(v1, cost); // v2 to v1 edge lag gaya
            }

        }

    }

    public void display() {

        for (int key : adj.keySet()) {
            System.out.println(key + "-->" + adj.get(key));
        }
    }
}




