package Lec_3.Disjoint_Set_Algo_1;

import java.util.HashMap;

public class DisJointSet {

    public class Node {
        int val;
        int rank;
        Node parent;
    }

    private HashMap<Integer, Node> map = new HashMap<>();

    // TC : O(1)
    public void CreateSet(int v) {

        Node nn = new Node();
        nn.val = v;
        nn.rank = 0;
        nn.parent = nn;

        // Map main set kaa address put kardo.
        map.put(v, nn);

    }

    // TC is not O(1) i.e we'll use path compression.
    public int find(int v) {

        /*

                     4
                   /   \
                  5     6
                  ^
                  |
                  3
                  ^
                  |
                  2

               find (3)

               3 kis parent se belong karta hain i.e. 5 and 5 kis set se belong kar raha hain i.e. 4 and 4 bolega mera parent main khudi huun.

               i.e return 4k


         */

        // Element v kis set se belong karta hain. woh patah karna hain.
        Node nn = map.get(v); // 3 kaa address nn main.
        return find(nn).val; // this will call the below method.

    }

    // Recursive call yaha par ho raha hain.
    private Node find(Node nn) {

        // Main khud hee apna representative huun
        if (nn.parent == nn) {
            return nn;
        }

        Node n = find(nn.parent);

        // Isse TC : O(1) ho raha hain
        nn.parent = n; // <--------- path Compression : Sab kaa parent n kardo. Without this Time complexity of find will be O(n)

        return n;

    }

    // Union By Rank
    public void union(int v1, int v2) {

        Node n1 = map.get(v1);
        Node n2 = map.get(v2);

        Node rn1 = find(n1); // Finding representative node of v1
        Node rn2 = find(n2); // Finding representative node of v2

        // Equal main kisi kaa bhi badal do.
        if (rn1.rank == rn2.rank) {

            rn1.parent = rn2;
            rn2.rank = rn2.rank + 1;

        // Union by rank : bolta hain Jiski rank kam uske parent ko badal do
        } else if (rn1.rank > rn2.rank) {

            rn2.parent = rn1;

        } else {

            rn1.parent = rn2;
        }

    }

}