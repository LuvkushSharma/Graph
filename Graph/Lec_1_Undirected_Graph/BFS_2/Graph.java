package Lec_1_Undirected_Graph.BFS_2;

/*

Tree ke level Order traversal ko BFS kehte hain.
And level Order Traversal main queue use hota hain.

1. Remove
2. Ignore  ----> Will get to see when two 3 removes simultaneously.
3. Visited
4. Self work
5. Woi Neighbours add karengye jo unvisited hain.

  --------
  |  1   |  ---> Queue
  --------

  remove 1 and visited mark kar do 1 ko.

  Then 1 ke unvisited neighbour i.e. 2 and 4  ko add kardo queue main.

  ---------------
  |  2   |  4   |
  ---------------

  Then , 2 niklega and mark it as visited.

  2 kaa wahi neighbour dalega jo unvisited hain i.e. 3

  ---------------
  |  4   |  3   |
  ---------------

  abb 4 niklega and uske unvisited neighbour daal do i.e. 3 and 5

  -----------------------
  |  3   |  3   |   5   |
  -----------------------

  Now , 3 nikaalo and insert nhi karega as 2 and 4 both are visited.

  Again 3 nikla isska matlab pehle se 3 nikla thaa and again 3 nikla i.e. Cycle hain graph main i.e. iss step ko skip kardo i.e. 5 par jaoo

  i.e. Ignore waala step tab chalaana hain jab ek visited nikal chuka hain and again woh nikal raha hain dubaara se. i.e 3 tak jaane kaa 2-2 tareeka hain i.e. Cycle presents in the graph.

  --------
  |  5   |
  --------

  5 nikaalo and insert it's unvisited neighbour i.e. 6 and 7

  --------------
  |  7   |  6  |
  --------------

  7 niklega and uska unvisited neighbour insert kardo.

  --------------
  |  6   |  6  |
  --------------

  6 niklega and koi insert nhi hoga and mark 6 as visited.

 */



import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    // Har ek Key ke corresponding <Integer , Integer> kaa hashmap store kar liya
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

    public boolean bfs (int src , int des) {

        HashSet <Integer> visited = new HashSet<>();
        Queue <Integer> q = new LinkedList<>();

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

            // 4. self work
            if (rv == des) {

                return true;
            }

            // 5. add unvisited neighbours
            for (int nbrs : map.get(rv).keySet()) {

                if (!visited.contains (nbrs)) {

                    q.add (nbrs);
                }
            }
        }

        // Agar loop se bahaar aya then it means path nhi thaa.
        return false;
    }
}
