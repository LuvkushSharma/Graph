package Lec_4.Bellman_Ford_Algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/*

 Dijkstra sab jagye chalega bas -ve weight waale edge par nhi chalega

 Bellmann ford bolta hain sab jagye chalega bas -ve weight cycle par nhi chalega.

    2         4
 1 -----> 2 -----> 3
 ^                 |
 |                 |
 ------------------
      -10

 123123

 1 se 2 jaane par cost : 2
 2 se 3 jaane par cost : 2 + 4 ==> 6

 3 se 1 jaane par : 6 + (-10) ==> -4

 and in each cycle cost from going 123 will decrease a lot which is not possible. Ek hee jagye baar baar jaane par distance thodi kam hota hain.

 Vtx to another vtx kaa mapping with cost

 Now , we will do relaxing.

 Pehle saare edges ek ek baar karne hain.

 We have to relax the edges (V-1) times.

 ------------------- How to identify -ve weight cycle.


    2         4
 1 -----> 2 -----> 3
 ^                 |
 |                 |
 ------------------
      -10

  ArrayList<EdgePair> --->

  3 to 1  --> -10

  2 to 3  --> 4

  1 to 2  --> 2

  -----------------------------------
  |       |       |        |        |
  |       |   0   |   INF  |   INF  |
  -----------------------------------
     0        1       2         3


   When i = 1:

     3 to 1 relax nhi hoga.

     2 to 3 relax nhi hoga.

     But 1 to 2 relax hoga.


   After 2 times relax

  -----------------------------------
  |       |       |        |        |
  |       |   0   |   2    |   6    |
  -----------------------------------
     0        1       2         3

  Ek baar aur relax karaaya

  -----------------------------------
  |       |       |        |        |
  |       |   -10 |   2    |   6    |
  -----------------------------------
     0        1       2         3



 */


public class Bellman {

    HashMap<Integer, HashMap<Integer, Integer>> map;

    public Bellman (int v) {

        this.map = new HashMap<>();
        for (int i = 1; i <= v; i++) {
            map.put(i, new HashMap<>());

        }

    }

    public void addEdge(int v1, int v2, int cost) {
        map.get(v1).put(v2, cost);

    }

    public class EdgePair {

        int v1;
        int v2;
        int cost;

        public EdgePair(int v1, int v2, int cost) {

            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override

        public String toString() {

            return this.v1 + " " + this.v2 + " @ cost " + this.cost;

        }

    }

    public ArrayList<EdgePair> GetAllEdge() {
        ArrayList<EdgePair> list = new ArrayList<>();

        for (int vtx : map.keySet()) {

            for (int nbrs : map.get(vtx).keySet()) {
                EdgePair ep = new EdgePair(vtx, nbrs, map.get(vtx).get(nbrs));
                list.add(ep);
            }

        }

        return list;

    }

    public void bellmanford(int src) {

        int v = map.size();
        int[] distance = new int[v + 1]; // since vtx value starts from 1 isliye 0th index will be left alone.

        // Sabhi jagye par INF
        Arrays.fill(distance, 10000_000);

        distance[src] = 0;

        ArrayList<EdgePair> alledge = GetAllEdge();

        /*


        for (int i = 1; i <= v-1; i++) {

            // Agar edges reverse order main hain then we have to relax all the edges V-1 times.
            // i.e. Ek dam end se suru ho raha ho pair wajaaye src se suru hokar.
            for (EdgePair eg : alledge) {

                int oldcost = distance[eg.v2]; // v2 kaa puuraana cost
                int newcost = distance[eg.v1] + eg.cost; // new cost if we take that path.

                // relax v2's cost
                if (oldcost > newcost) {

                    distance[eg.v2] = newcost;
                }

            }

        }


         */

        // ============= Used to detect the -ve cycle ===============

        // In case we want to check the -ve weight cycle then ek baar aur chala do.
        for (int i = 1; i <= v; i++) {
            for (EdgePair eg : alledge) {

                int oldcost = distance[eg.v2];
                int newcost = distance[eg.v1] + eg.cost;

                if (oldcost > newcost) {

                    // Matlab agar 5 vertex hain toh ham 4 time relax karaate the. But,
                    // Agar hamne 5th time bhi chalaya and 5th time bhi relax kiya iska matlab Cycle hain and woh bhi -ve.
                    if (i != v) {
                        distance[eg.v2] = newcost;
                    } else {
                        System.out.println("-ve cycle h");
                        return;
                    }
                }

            }

        }

        for (int i = 1; i < distance.length; i++) {

            System.out.println(i + " --> " + distance[i]);
        }

    }

}