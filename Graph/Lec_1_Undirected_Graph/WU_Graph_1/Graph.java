package Lec_1_Undirected_Graph.WU_Graph_1;

import java.util.HashMap;
import java.util.HashSet;

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

    // Check 2 vertex ke beech main edge hain kii nahi.
    public boolean ContainsEdge(int v1, int v2) {

        return map.get(v1).containsKey(v2) && map.get(v2).containsKey(v1);

    }

    // Graph ke andarr koi vertex hain kii nhi.
    // Toh map main as a key v1 hain kii nhi bas ye hee check karna hain.
    public boolean Containsvertex(int v1) {
        return map.containsKey(v1);
    }

    // Total number rof edges in the graph.
    // Total number of values jo hain keys ke corresponding woh hain total number of edges. And totalEdges/2 is the Unique edge.
    public int numberOfEdge() {
        int sum = 0;
        for (int key : map.keySet()) {
            sum = sum + map.get(key).size(); // Keys ke corresponding jo map hain uska size.
        }
        return sum / 2;
    }


    public void removeEdge(int v1, int v2) {
        if (ContainsEdge(v1, v2)) {

            map.get(v1).remove(v2);
            map.get(v2).remove(v1);
        }

    }

    // Pehle V1 ke neighbours main jaake V1 ko delete maarengye uske baad V1 ko delete maarengye.
    public void removevertex(int v1) {

        // Pehle v1 ke neighbour manga liye. If v1 is 3 then keySet contains (2,4)
        for (int nbrs : map.get(v1).keySet()) {
            // removeEdge(key, v1);
            map.get(nbrs).remove(v1);
        }
        map.remove(v1);

    }

    public void display() {
        for (int key : map.keySet()) {
            System.out.println(key + "-->" + map.get(key));
        }
    }

    // Source given hain and destination given check v1 se v2 ke beech main direct path hain kii nhi.

    // 1  to 6 check karo
    // 1 kaa neighbour 2 and 4
    // 2 tere se direct 6 kaa raasta hain ? --> nhi
    // 2 bolega mere neighbour se puuch le i.e 3 se  3 bolega mere se bhi direct nhi hain mere neighbour se puuch le i.e. 4
    // 4 bolega mere neighbour se puuch le i.e 5
    // 5 bolega mere se direct path hain 6 kaa.
    // It means agar 5 se direct path hain 6 kaa then 4 se bhi hoga as 5 is the neighbour of 4 and 3 se bhi hoga and 2 se bhi and then 1 se bhi.

    // But , below code will give Stack OverFlow error due to infinite loop.

    // Maine 1 ke neighbour se puucha i.e. 2 se kii tere se direct path hain kya 6 tak woh bolega nhi the 2 apne neighbour main jaayega.
    // And 2 ke neighbour main 1 bhi hoga it means firse 2 puuchega 1 se kii tere se direct path hain kya --> Nhi. then again 1 apne neighbour se puuchega i.e 2
    // And this will continue forever.

    // Toh hame kya karna hain kii 1 visited hain isliye hame usse store karna padega. i.e 1 is visited toh dubaara agar 1 src par ayega toh further function call mat lagao.

    // We can take ArrayLists or array for visited. But let's try HashSet

    /*

    public boolean hashpath(int src, int dis) {

        // Path mil gaya.
        if (src == dis) {
            return true;
        }

        for (int nbrs : map.get(src).keySet()) {

                boolean ans = hashpath(nbrs, dis);
                if (ans) {
                    return ans;
                }
        }

        return false;
    }

     */

    // Error Resolved Code
    public boolean hashpath(int src, int dis, HashSet<Integer> visited) {

        if (src == dis) {
            return true;
        }

        // Marking source as visited.
        visited.add(src);

        // Initially nbrs main (2,4) main se values agyengyi as 1 ke corresponding jo values hain unkaa key set i.e (2,4)
        for (int nbrs : map.get(src).keySet()) {

            // Call ussi ke liye jaayega jo neighbour Visited nhi ho.
            if (!visited.contains(nbrs)) {
                boolean ans = hashpath(nbrs, dis, visited);

                // Jaise hee direct path mil gaya then further check karne kii koi need nhi hain just return true.
                if (ans) {
                    return ans;
                }
            }
        }

        visited.remove(src); // No need to do but it's a good habit.
        return false;

    }

    // Printing all the paths from 1 to 6
    public void printallpath(int src, int dis, HashSet<Integer> visited, String ans) {

        if (src == dis) {
            System.out.println(ans+dis);
            return;
        }

        visited.add(src);
        for (int nbrs : map.get(src).keySet()) {
            if (!visited.contains(nbrs)) {

                // Path milte jaa rahe hain then unnhe string main concat kar do.
                printallpath(nbrs, dis, visited, ans + src + " ");

            }
        }

        // Remove karna padega jisse kii saare path mile.
        // Otherwise ek he path milengye.
        // So, for more possibilities we have to remove the changes after backtracking.
        visited.remove(src);

    }
}
