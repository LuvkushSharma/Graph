package Cycle_Detection_Algos_2.Lec_3_Topological_Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/*


                       1 --------------> 4
                       ^                 |
                       |                 |
                       |                 V
                       2 <-------------- 3

                         Cyclic graph

          Adjacency List :
          1 --> 4
          2 --> 1
          3 --> 2
          4 --> 3

          is this ordering : 1 2 3 4     is valid Topological ordering.

          is 1 comes before 4 in the given ordering. --> True
          is 2 comes before 1 in the given ordering. --> false

          Hence given graph is cyclic since if it was acyclic then above ordering will be correct.


          So, if we are unable to find the valid topological sort then it means there is a cycle in the graph.


 */

// TC : O(N + E)
public class Topo {


    private void topoSort(int node , HashMap<Integer, Boolean> visited , Stack<Integer> st , HashMap<Integer, List<Integer>> adj){

        // ----- Pehla kaam node ko visited kardo -----
        visited.put (node , true);

        // node ke saare neighbours ke liye call kar diya.
        for(int neighbour : adj.get(node)){

            if(!visited.get(neighbour)){ // Agar neighbour visited nhi hain toh

                topoSort(neighbour , visited , st , adj); // Uske liye function call kardo.

            }
        }

        // ------- Jab bhi ham call se baapis aayengye toh stack main push kardengye uss node ko -------

        st.push(node); // Imp

    }

    public ArrayList<Integer> topologicalSort(int [][]edges , int n , int e){

        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // --------- Adjacency List Creation -----------
        for (int i = 0 ; i < n ; i++) {

            adj.put (i , new ArrayList<>());
        }

        for (int i = 0 ; i < edges.length ; i++) {

            int u = edges[i][0]; // Vertex - 1
            int v = edges[i][1]; // Vertex - 2

            adj.get(u).add(v); // edge from u to v

        }

        // -------- Call dfs topological sort function for all components --------
        HashMap<Integer, Boolean> visited = new HashMap<>();
        Stack<Integer> st = new Stack<>();

        // Initializing the visited hashmap
        for (int i = 0 ; i < n ; i++)
            visited.put (i , false);

        // Since nodes starting from 0 i.e we start i from 0.
        for(int i = 0; i < n ; i++){

            if(!visited.get(i)){

                topoSort(i , visited ,st, adj);
            }
        }

        // ------- Accessing data from the stack -------

        ArrayList<Integer> ans = new ArrayList<>();

        while( !st.empty()){

            int val = st.peek();
            st.pop();
            ans.add(val);
        }

        return ans;
    }

}
