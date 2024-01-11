import java.util.Scanner;
import java.util.Vector;

public class Main {
    static Vector<Node>[] tree;
    static boolean[] visited;
    static int maxDistance = 0;
    static int farthestNode = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        tree = new Vector[n + 1];

        for (int i = 0; i <= n; i++) {
            tree[i] = new Vector<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            int weight = sc.nextInt();

            tree[parent].add(new Node(child, weight));
            tree[child].add(new Node(parent, weight));
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
        sc.close();
    }

    static void dfs(int v, int distance) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = v;
        }

        for (Node node : tree[v]) {
            if (!visited[node.child]) {
                dfs(node.child, distance + node.weight);
            }
        }
    }
}

class Node {
    int child;
    int weight;

    Node(int child, int weight) {
        this.child = child;
        this.weight = weight;
    }
}
