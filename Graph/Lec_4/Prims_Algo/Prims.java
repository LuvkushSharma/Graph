package Lec_4.Prims_Algo;

/*

         Using DFS


                   5               7
           1 ------------- 4 ------------- 5
           |               |               |\
        2  |               |  4            |  \ 6
           |               |             1 |    \
           |               |               |      \
           2 ------------- 3               6 ----- 7
                 3                             8

      Let's start from 7. 7 will acquire 6 and 5

      ----------------------
      |   7  |   7  |   0  |
      ----------------------

      Mark 7 as visited after removing 7 from pq

      Priority queue

        vtx     acq     cost

      ----------------------
      |   6  |   7  |   8  |
      ----------------------
      |   5  |   7  |   6  |
      ----------------------

      pq main se 5 niklega pehle because of  min heap.

            5
              \   6
                \
                  \
                    7

       5 will insert it's unvisited neighbour.


       vtx     acq     cost
      ----------------------
      |   6  |   7  |   8  |
      ----------------------
      |   4  |   5  |   7  |
      ----------------------
      |   6  |  5   |   1  |
      ----------------------

      Note : Visited ke case main ignore karengye.

      Now , pq main se 6 niklega with cost 1.

             5
             | \   6
           1 |   \
             |     \
             6      7



 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Prims {

    // <key : neighbour , cost>
    HashMap<Integer , HashMap<Integer , Integer>> map;

    public Prims (int v) {

        map = new HashMap<>();
        for (int i = 1 ; i <= v ; i++) {

            map.put (i , new HashMap<>());
        }
    }

    public void AddEdge (int v1 , int v2 , int cost) {

        map.get(v1).put (v2 , cost);
        map.get(v2).put (v1 , cost);
    }

    public int Prims_Min_Cost () {

        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cost - o2.cost;
            }
        });

        int sum = 0;

        // We can start from any vertex. Let's say we start from 3.
        pq.add (new Pair(3 , 3 , 0));

        while (!pq.isEmpty()) {

            // remove
            Pair rp = pq.poll();

            // ignore
            if (visited.contains (rp.vtx)) {

                continue;
            }

            // visited
            visited.add (rp.vtx);

            // self work
            System.out.println(rp);
            sum += rp.cost;

            for (int nbrs : map.get (rp.vtx).keySet()) {

                // neighbour visited nhi hooga tabhi baat karengye aagye
                if (!visited.contains (nbrs)) {

                    int cost = map.get(rp.vtx).get (nbrs);

                    pq.add (new Pair (nbrs , rp.vtx , cost));
                }
            }
        }

        return sum;
    }

    class Pair {

        int vtx;
        int aqurvtx;
        int cost;

        public Pair (int vtx , int aqurvtx , int cost) {

            this.vtx = vtx;
            this.aqurvtx = aqurvtx;
            this.cost = cost;
        }

        @Override
        public String toString () {

            return this.vtx + " " + this.aqurvtx+ " @ " + this.cost;
        }
    }
}
