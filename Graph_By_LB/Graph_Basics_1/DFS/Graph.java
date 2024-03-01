package Graph_Basics_1.DFS;

import java.util.*;

public class Graph {

    private void prepareAdjList (int vertex , HashMap<Integer , List<Integer>> adjList , int [][]edges) {

        for (int i = 0 ; i < vertex ; i++) {

            adjList.put (i , new ArrayList<>());
        }

        for (int i = 0 ; i < edges.length ; i++) {

            int u = edges[i][0];
            int v = edges[i][1];


            adjList.get(u).add(v); // edge from u to v
            adjList.get (v).add(u);

        }
    }


    private void dfs (HashMap<Integer , List<Integer>> adjList , HashMap<Integer, Boolean> visited , ArrayList<Integer> ans , int src){

        Stack<Integer> st = new Stack<>();

        st.push (src);
        visited.put (src , true);

        while (!st.isEmpty()){

            int frontNode = st.peek();
            st.pop();

            ans.add (frontNode);

            for(int neighbour : adjList.get(frontNode)){

                if(!visited.get(neighbour)){

                    st.push (neighbour);
                    visited.put (neighbour , true);
                }
            }

        }

    }

    public ArrayList<Integer> DFS (int vertex , int edges[][]) {

        HashMap<Integer , List<Integer>> adjList = new HashMap<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();

        // It will create a adjacency List
        prepareAdjList (vertex , adjList , edges);

        // Initializing the visited map with all the key having value as false.
        // i.e. initially all the keys are unvisited.
        for (int i = 0 ; i < vertex ; i++) {
            visited.put (i , false);
        }

        // Traverse all the components of a graph
        for (int i = 0 ; i < vertex ; i++) {

            if (!visited.get(i)) {

                dfs (adjList , visited , ans , i);
            }
        }

        return ans;
    }
}
