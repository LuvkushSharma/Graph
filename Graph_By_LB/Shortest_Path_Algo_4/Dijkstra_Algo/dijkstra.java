package Shortest_Path_Algo_4.Dijkstra_Algo;

import java.util.*;

public class dijkstra {

    // Time complexity : O(Elog(V)) where E is the no. of edges and V is the number of vertices.
// Space complexity : O(V + E)

    public int [] dijkstras(int [][]data , int vertices , int edges ,int source){

        // --------- Adjacency List Creation ------------
        HashMap<Integer , List<Pair>> adj = new HashMap<>(); // Where pair contains the neighbour of a node with it's weight.

        for (int i = 0 ; i < vertices ; i++) {

            adj.put (i , new ArrayList<>());
        }

        for(int i = 0; i < edges ; i++){

            int u = data[i][0]; // Node - 1
            int v = data[i][1]; // Node - 2
            int w = data[i][2]; // Weight of edge from u to v

            // pair of neighbour with it's weight.
            Pair p1 = new Pair();
            p1.first = v;
            p1.second = w;

            adj.get(u).add(p1);

            // In case of undirected graph a edge from v to u also exists.
            // pair of neighbour with it's weight.
            Pair p2 = new Pair();
            p2.first = u;
            p2.second = w;

            adj.get(v).add(p2);

        }

        int []dist = new int[vertices]; // Making distance vector.

        // Initializing distance vector with infinite values.
        for(int i = 0; i < vertices ; i++){

            dist[i] = Integer.MAX_VALUE;
        }

        // Creation of set on basis (distance , node)
        SortedSet<Pair> st = new TreeSet<>();


        // ------------- Initializing distance and set with source node  ------------
        dist[source] = 0;

        Pair p = new Pair();
        p.first = dist[source];
        p.second = source;
        st.add(p); // inserting pair of dist. of node and node.


        while(! st.isEmpty()){

            // ----- fetch top record -----
            Pair top = st.first();

            int nodeDistance = top.first;
            int topNode = top.second;

            // --- Deleting top data from the set ---
            st.remove(st.first());

            // ------------ traverse on neighbours -------------
            for(Pair neighbour : adj.get(topNode)){

                if(nodeDistance + neighbour.second < dist[neighbour.first]){

                    Pair pr1 = new Pair();
                    pr1.first = dist[neighbour.first];
                    pr1.second = neighbour.first;

                    Pair record = null;

                    for (Pair pr : st) {

                        if (pr.first == pr1.first && pr.second == pr1.second) {

                            record = pr1;

                        }
                    }

                    // If record found then delete that record and fill new record in place of it.
                    if(record != null){

                        st.remove(record);
                    }

                    // ----------- Distance update ------------
                    dist[neighbour.first] = nodeDistance + neighbour.second;

                    // ----------- push record in set ----------
                    Pair pr2 = new Pair();
                    pr2.first = dist[neighbour.first];
                    pr2.second = neighbour.first;
                    st.add(pr2);
                }
            }
        }


        return dist;
    }

    private class Pair {

        int first;
        int second;

        public Pair () {}

        public Pair (int first , int second){

            this.first = first;
            this.second = second;
        }

    }
}
