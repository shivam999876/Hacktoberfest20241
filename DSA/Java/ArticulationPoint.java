import java.util.ArrayList;
import java.util.List;

public class ArticulationPoint {
    // A recursive function to traverse the graph without
    // considering the ith vertex and its associated edges
    static void dfs(List<Integer>[] adj, int V, List<Integer> vis,
                    int i, int curr) {
        vis.set(curr, 1);
        for (int x : adj[curr]) {
            if (x != i) {
                if (vis.get(x) == 0) {
                    dfs(adj, V, vis, i, x);
                }
            }
        }
    }

    // Function to find Articulation Points in the graph
    static void AP(List<Integer>[] adj, int V) {

        // Iterating over all the vertices and for each vertex i
        // remove the vertex and check whether the graph remains
        // connected.
        for (int i = 1; i <= V; i++) {

            // To keep track of number of components of graph
            int components = 0;

            // To keep track of visited vertices
            List<Integer> vis = new ArrayList<>();
            for (int j = 0; j <= V; j++) {
                vis.add(0);
            }

            // Iterating over the graph after removing vertex i
            // and its associated edges
            for (int j = 1; j <= V; j++) {
                if (j != i) {

                    // If the jth vertex is not visited it will
                    // form a new component.
                    if (vis.get(j) == 0) {

                        // Increasing the number of components.
                        components++;

                        // dfs call for the jth vertex
                        dfs(adj, V, vis, i, j);
                    }
                }
            }
            // If number of components is more than 1 after
            // removing the ith vertex then vertex i is an
            // articulation point.
            if (components > 1) {
                System.out.println(i);
            }
        }
    }

    // Utility function to add an edge
    static void addEdge(List<Integer>[] adj, int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    // Driver Code
    public static void main(String[] args) {
        // Create graphs given in above diagrams
        System.out.println("Articulation points in the graph ");
        int V = 5;
        List<Integer>[] adj1 = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            adj1[i] = new ArrayList<>();
        }
        addEdge(adj1, 1, 2);
        addEdge(adj1, 2, 3);
        addEdge(adj1, 1, 3);
        addEdge(adj1, 3, 4);
        addEdge(adj1, 4, 5);
        AP(adj1, V);
    }
}
