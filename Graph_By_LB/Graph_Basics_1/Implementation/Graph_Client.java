package Graph_Basics_1.Implementation;

import java.util.Scanner;

/*

--------------- Undirected Graph ----------------

Input :

Enter the number of nodes :
5
Enter the number of edges :
6

0 1 3
1 2 2
2 3 4
3 1 1
3 4 5
0 4 8


----------- Directed Graph --------------

Input - 1:

Enter the number of nodes :
5
Enter the number of edges :
6

1 0 3
2 1 2
0 2 8
0 3 1
3 4 6
4 0 7

Graph Img : Directed_Graph_Ex

Input - 2:

Enter the number of nodes :
7
Enter the number of edges :
12

1 2 1 <--------- Let's say cost of each edge as 1
1 3 1
1 4 1
2 4 1
2 5 1
3 6 1
4 3 1
4 6 1
4 7 1
5 4 1
5 7 1
7 6 1


 */

public class Graph_Client {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n , m;
        System.out.println("Enter the number of vertex : ");
        n = sc.nextInt();

        System.out.println("Enter the number of edges : ");
        m = sc.nextInt();

//        Graph g = new Graph(n , false);
          Graph g = new Graph(n , true);

        for (int i = 0 ; i < m ; i++) {

            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt(); // Cost b/w the edges or we can say that weight b/w the edges.

            // Creating an undirected graph. false --> represents that we want to create an undirected graph.
            g.AddEdge (u , v , cost);
        }

        // Printing the graph
        g.display();

    }
}
