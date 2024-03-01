package Lec_2.Is_Graph_Bipartite__2;

/*

There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
More formally, for each v in graph[u], there is an undirected edge between node u and node v.
The graph has the following properties:

 -----> There are no self-edges (graph[u] does not contain u).
 -----> There are no parallel edges (graph[u] does not contain duplicate values).
 -----> If v is in graph[u], then u is in graph[v] (the graph is undirected).
 -----> The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.

A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph
connects a node in set A and a node in set B.

Return true if and only if it is bipartite.



Example 1:


Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
Example 2:


Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.


Constraints:

graph.length == n
1 <= n <= 100
0 <= graph[u].length < n
0 <= graph[u][i] <= n - 1
graph[u] does not contain u.
All the values of graph[u] are unique.
If graph[u] contains v, then graph[v] contains u.

 */

/*

Given :

No need to form adjacency lists as Neighbours of each node is given already.

[[1,2,3],[0,2],[0,1,3],[0,2]]

0 kaa adjacent -----> 1 , 2 , 3
1 kaa neighbours ----> 0 and 2
2 kaa neighbours -----> 0 , 1 , 3
3 kaa neighbours -----> 0 and 2


Set A        Set B
0 , 4        1 , 2

1. SetA Intersection SetB == null

2. Set A union SetB = Whole graph

3. Edges should be across the set.
i.e. 0 edge banaayega toh setB ke elements se banaayega and same with 4.
Also, setB ke elements agar edge banaayega toh setA ke elements se hee.

Set A     Set B
           1
         /
       /
   0
       \
         \
           2


     same with 4

           1
         /
       /
   4
       \
         \
           2

---------> We will solve the problem using Graph Coloring : Red and Black color nodes.

-----> Intuition : The goal is to assign colors to the nodes in such a way that no two adjacent nodes have the same color.
If it is possible to color all the nodes without any conflicts, the graph is bipartite.

Note : All the Acyclic graphs are Bipartite. Acyclic graph can be disconnected but each of it's component are also acyclic.

----> In case of Cyclic graph :-
Two sets banayengye and har ek nodes ko uske uske uske neighbour ke opposite set main daalengye.

NOTE : Agar graph main even length kaa cycle hain then it will be easily divided into two parts.

Following steps should be considered.

1. remove
2. ignore
3. visited
4. self work
5. add unvisited neighbours in the queue in case of BFS.


           1
         /    \
        2      3
        |      |
        4 ---- 5

        1 --> queue main daalo and remove it and insert it into setA
        remove 2 and insert it into setB and insert it's neighbour i.e 4 in the queue

        now remove 4 and insert it into setA and insert 5 in the queue
        remove 5 and put it in the setB and insert 3 in the queue

        remove 3 and insert it in the setA

        SetA          SetB
         1             2
         4             5
         3

        But 1 and 3 neighbours hain it means Odd length kii cycle waala graph Bipartite nhi hain.

                                           Summary
              👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍
               1. Graph having no cycle will be acyclic i.e. it is bipartite
               2. Agar cycle hain toh if cycle of odd length then Bipartite nhi hoga.
               3. Agar cycle hain and if it is of even length then the graph is bipartite.
               👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍👍


     Visited map banaayengye and woh node kii value and distance from src store karega.


 */

/*
   ---------------------------- Dry Run ---------------------------

// ---------------------- Imp -------------------

// 2. Ignore , if already visited

Let's take an example of a graph having even cycle and , then we'll take an example of odd cycle.

1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣1️⃣

Ex-1 :

 src
  1 ------------- 4
  |               |
  |               |
  |               |
  |               |
  2 ------------- 3


 Step-1 : (1,0) queue main dalega pehle and then while loop suru.
          (1,0) is popped from the queue. and 2nd case will not run. So, simply mark 1 as visited with it's distance.

          Visited
 --------------------------
 | (1,0) |
 --------------------------

 rv.vtx = 1
 rv.distance = 0

          Push all the unvisited neighbours of 1 i.e. 2 and 4 with distance = (rv.distance + 1)

         Queue
 --------------------------
 | (2,1) | (4,1) |
 --------------------------



 Step-2 : (2,1) is popped from the queue. and 2nd case will not run. So, simply mark 2 as visited with it's distance.

          Visited
 --------------------------
 | (1,0) | (2,1) |
 --------------------------

 rv.vtx = 2
 rv.distance = 1

          Push all the unvisited neighbours of 2 i.e. 3 with distance = (rv.distance + 1)

         Queue
 --------------------------
 | (4,1) | (3 , 2) |
 --------------------------



 Step-3 : (4,1) is popped from the queue. and 2nd case will not run. So, simply mark 4 as visited with it's distance.

          Visited
 -------------------------------
 | (1,0) | (2,1) | (4,1) |
 --------------------------------

 rv.vtx = 4
 rv.distance = 1

          Push all the unvisited neighbours of 4 i.e. 3 with distance = (rv.distance + 1)

         Queue
 --------------------------
 | (3 , 2) | (3 , 2) |
 --------------------------

 Step-4 : (3,2) is popped from the queue. and 2nd case will not run. So, simply mark 3 as visited with it's distance.

          Visited
 ----------------------------------
 | (1,0) | (2,1) | (4,1) | (3,2)  |
 ----------------------------------

 rv.vtx = 3
 rv.distance = 2

          Push all the unvisited neighbours of 3. Since 2 and 4 are already visited , so, nothing will be pushed in the queue.

         Queue
 --------------------------
 | (3 , 2) |
 --------------------------

 Step-5 : (3,2) is popped from the queue.

          Since , 3 is already visited and , it again comes it means there is a cycle in the graph.

          and both the visited 3 have the same distance it means.

          Ham kisi bhi raaste se 3 par jaaye we will get the same distance from the source node to 3.

          It means their is even length cycle in the graph.

          So, simply , continue.

          As a single graph may contains several cycles, so the given graph should contain only even length cycles if even a single cycle is of odd length then simply return false.


 2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣2️⃣

 Ex-2 :

 src
  1 ------------- 5
  |                 \
  |                   \ 4
  |                  /
  |                 /
  2 ------------- 3


    Pentagon


 Step-1 : (1,0) queue main dalega pehle and then while loop suru.
          (1,0) is popped from the queue. and 2nd case will not run. So, simply mark 1 as visited with it's distance.

          Visited
 --------------------------
 | (1,0) |
 --------------------------

 rv.vtx = 1
 rv.distance = 0

          Push all the unvisited neighbours of 1 i.e. 2 and 5 with distance = (rv.distance + 1)

         Queue
 --------------------------
 | (2,1) | (5,1) |
 --------------------------



 Step-2 : (2,1) is popped from the queue. and 2nd case will not run. So, simply mark 2 as visited with it's distance.

          Visited
 --------------------------
 | (1,0) | (2,1) |
 --------------------------

 rv.vtx = 2
 rv.distance = 1

          Push all the unvisited neighbours of 2 i.e. 3 with distance = (rv.distance + 1)

         Queue
 --------------------------
 | (5,1) | (3 , 2) |
 --------------------------



 Step-3 : (5,1) is popped from the queue. and 2nd case will not run. So, simply mark 5 as visited with it's distance.

          Visited
 -------------------------------
 | (1,0) | (2,1) | (5,1) |
 --------------------------------

 rv.vtx = 5
 rv.distance = 1

          Push all the unvisited neighbours of 5 i.e. 4 with distance = (rv.distance + 1)

         Queue
 --------------------------
 | (3 , 2) | (4 , 2) |
 --------------------------

 Step-4 : (3,2) is popped from the queue. and 2nd case will not run. So, simply mark 3 as visited with it's distance.

          Visited
 ----------------------------------
 | (1,0) | (2,1) | (5,1) | (3,2)  |
 ----------------------------------

 rv.vtx = 3
 rv.distance = 2

          Push all the unvisited neighbours of 3. i.e. 4

         Queue
 --------------------------
 | (4 , 2) | (4 , 3) |
 --------------------------

 Step-5 : (4,2) is popped from the queue. and 2nd case will not run. So, simply mark 3 as visited with it's distance.

           Visited
 -------------------------------------------
 | (1,0) | (2,1) | (5,1) | (3,2)  | (4,2) |
 -------------------------------------------

 rv.vtx = 4
 rv.distance = 2

 Since all the neighbours of 4 are already visited it means nothing will be pushed in the queue.


         Queue
 --------------------------
 | (4 , 3) |
 --------------------------


 Step-6 : (4,3) is popped from the queue. and 2nd case will arise

 Since , distance of 4 from two different paths is different it means graph is of odd length. As , when we start from 0 and move till 4 crossing 5 then (4,2)
 But , when we are trying to reach 4 from 0 crossing 2 then (4,3).

 So, odd length comes it means not a Bipartite Graph.



 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    public boolean isBipartite (int graph[][]) {

        HashMap<Integer, Integer> visited = new HashMap<>();

        // source node se kitna distance duur hain.
        Queue<BipartitePair> q = new LinkedList<>();


        for (int src = 0 ; src < graph.length ; src++) {

            // 1 ke liye chalne ke baad 2 ke liye nhi chalega , 3 , 4 ke liye bhi nhi chalega then 5 ke liye chalega.
            if (visited.containsKey(src)) {
                continue;
            }

            q.add(new BipartitePair(src , 0));

            while (!q.isEmpty()) {

                // 1. remove
                BipartitePair rv = q.poll();

                System.out.println("Removed : ------> (" + rv.vtx + " , " + (rv.distance) + ")");

                // 2. Ignore , if already visited
                if (visited.containsKey(rv.vtx)) {

                    // Check node kaa puuranaa waala distance and naya waala

                    if (visited.get(rv.vtx) != rv.distance) {

                        System.out.println("Odd Cycle 💀 : ------> (" + rv.vtx + " , " + (rv.distance) + ")");

                        // Odd cycle
                        return false;

                    } else {

                        System.out.println("Continue : 💀 : ------> (" + rv.vtx + " , " + (rv.distance) + ")");
                        continue;
                    }
                }

                // 3. marked visited
                // Initially : (0,0)
                visited.put (rv.vtx , rv.distance);
                System.out.println(rv.vtx + " : marked as visited.");


                // 5. add unvisited neighbours
                // neighbour of 0 are graph[0] , neighbour of 1 are graph[1]

                // 1 , 2 and 3
                // abhi waala neighbour i.e. 0 kaa distnace + 1 i.e. (1,1)
                // (2,1) and (3,1)

                // same happens
                for (int nbrs : graph[rv.vtx]) {

                    if (!visited.containsKey(nbrs)) {

                        BipartitePair np = new BipartitePair(nbrs , rv.distance + 1);
                        q.add (np);

                        System.out.println("nbrs  : ---->  (" + nbrs + " , " + (rv.distance+1) + ")");
                    }
                }
            }


        }

        // Yaa toh acyclic yaa fir even length kaa graph hain.
        return true;
    }

    class BipartitePair {

        int vtx;
        int distance;

        public BipartitePair (int vtx , int distance) {

            this.vtx = vtx;
            this.distance = distance;
        }
    }

}

