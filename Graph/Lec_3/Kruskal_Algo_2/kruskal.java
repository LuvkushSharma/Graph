package Lec_3.Kruskal_Algo_2;

import Lec_3.Disjoint_Set_Algo_1.DisJointSet;

import java.util.*;


public class kruskal {

    HashMap<Integer, HashMap<Integer, Integer>> map;

    public kruskal (int v) {

        this.map = new HashMap<>();

        for (int i = 1; i <= v; i++) {
            map.put(i, new HashMap<>());

        }
    }

    public void addEdge(int v1, int v2, int cost) {

        map.get(v1).put(v2, cost);
        map.get(v2).put(v1, cost);

    }


    // Step-1 :
    public ArrayList<EdgePair> getalledge() {

        ArrayList<EdgePair> list = new ArrayList<>();

        for (int e1 : map.keySet()) {

            for (int e2 : map.get(e1).keySet()) {

                int cost = map.get(e1).get(e2);
                EdgePair eg = new EdgePair(e1, e2, cost);
                list.add(eg);

            }
        }

        return list;
    }

    public void KruskalsAlgorithm() {

        ArrayList<EdgePair> ll = getalledge();

        // Edges ko sort kardiya on the basis of cost.
        Collections.sort(ll , new Comparator<EdgePair>() {

            @Override
            public int compare(EdgePair o1, EdgePair o2) {

                return o1.cost - o2.cost;
            }
        });


        int ans = 0;
        DisJointSet ds = new DisJointSet();

        // Jitne vertices utne hee set
        for (int k : map.keySet()) {
            ds.CreateSet(k);

        }


        // Sorted Order Main Edges niklegyi.
        for (EdgePair edge : ll) {

            int e1 = edge.e1;
            int e2 = edge.e2;

            int re1 = ds.find(e1); // Representative element of e1
            int re2 = ds.find(e2); // Representative element of e2

            if (re1 == re2) {

                // nothing ---> Cycle ban jaayegyi agar join kiya toh isliye SKIP

            } else {

                ds.union(re1, re2);
                System.out.println(edge);
                ans += edge.cost;

            }

        }

        System.out.println(ans);

    }

    // e1 , e1 neighbour , cost b/w e1 and e1's neighbour
    public class EdgePair {

        int e1;
        int e2;
        int cost;

        public EdgePair(int e1, int e2, int cost) {

            this.e1 = e1;
            this.e2 = e2;
            this.cost = cost;
        }

        @Override
        public String toString() {

            return this.e1 + " - " + this.e2 + " @ " + this.cost;
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = 7;

        kruskal ks = new kruskal(n);

//        for (int i = 0; i < m; i++) {
//
//            int v1 = sc.nextInt();
//            int v2 = sc.nextInt();
//            int cost = sc.nextInt();
//            ks.addEdge(v1, v2, cost);
//        }

        ks.addEdge(1 , 2 , 3);
        ks.addEdge(1 , 4 , 4);
        ks.addEdge(2 , 3 , 5);
        ks.addEdge(3 , 4 , 6);
        ks.addEdge(4  , 5 , 8);
        ks.addEdge(5 , 6 , 2);
        ks.addEdge(5 , 7 , 1);
        ks.addEdge(6 , 7 , 9);

        ks.KruskalsAlgorithm();
    }

}
