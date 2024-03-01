package Lec_2.Dijkstra_Algo___3;

/*
--------------> We have to use the Priority queue

Toh ham Priority queue se usse nikaalengye jo kii Pq main top par ho i.e. min cost waala node


*/

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

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

    // kis node se nikaalna hain
    public void Dijkstra (int src) {

        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<DijkstraPair> pq = new PriorityQueue<>(new Comparator<DijkstraPair>() {
            @Override
            public int compare(DijkstraPair o1, DijkstraPair o2) {
                return o1.cost - o2.cost;
            }
        });

        pq.add (new DijkstraPair(src , "" + src , 0));

        while (!pq.isEmpty()) {

            // remove
            DijkstraPair rp = pq.poll();

            // Ignore
            if (visited.contains(rp.vtx)) {
                continue;
            }

            // visited
            visited.add(rp.vtx);

            // self work
            System.out.println(rp);

            for (int nbrs : map.get(rp.vtx).keySet()) {

                // insert unvisited neighbours
                if (!visited.contains(nbrs)) {

                    int cost = rp.cost + map.get(rp.vtx).get(nbrs);
                    DijkstraPair np = new DijkstraPair(nbrs , rp.acq , cost);

                    pq.add(np);

                }
            }

        }
    }

    class DijkstraPair {

        int vtx;
        String acq;
        int cost;

        public DijkstraPair (int vtx , String acq , int cost) {

            this.vtx = vtx;
            this.acq = acq;
            this.cost = cost;
        }

        @Override
        public String toString() {

            return this.vtx + " " + this.acq + " @ " + this.cost;
        }
    }
}
