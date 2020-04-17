import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class millionairemadness {
    public static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] weightGrid = new int[m][n];
        int[][] vertexGrid = new int[m][n];
        int vertex = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                vertexGrid[i][j] = vertex++;
                weightGrid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // make adjList for every coordinate in the grid
        ArrayList<ArrayList<IntegerPair>> adjList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<IntegerPair> tempList = new ArrayList<>();

                for (int[] dir : DIRS) {
                    int nextRow = i + dir[0];
                    int nextCol = j + dir[1];

                    boolean isValidRow = nextRow >= 0 && nextRow < m;
                    boolean isValidCol = nextCol >= 0 && nextCol < n;
                    if (!isValidRow || !isValidCol) continue;

                    int weight = Math.max(0, weightGrid[nextRow][nextCol] - weightGrid[i][j]);

                    tempList.add(new IntegerPair(vertexGrid[nextRow][nextCol], weight));
                }

                adjList.add(tempList);
            }
        }

        int ladderHeight = -1;
        boolean[] visited = new boolean[m * n];
        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
        int source = vertexGrid[0][0], dest = vertexGrid[m - 1][n - 1];

        // init
        pq.add(new IntegerPair(source, 0));

        while (!pq.isEmpty()) {
            IntegerPair v = pq.poll();
            ladderHeight = v.second();
            // System.out.println(v.first());
            // System.out.println(ladderHeight);

            // DFS results in Memory Limit Exceeded (MLE)
            BFS(v.first(), dest, visited, adjList, ladderHeight, pq);

            // System.out.println(Arrays.toString(visited).replace("[", "").replace("]", "").replace("false", ".").replace("true", "T").replace(",", " "));
            // System.out.println();

            if (visited[dest]) break;
        }

        out.write(Integer.toString(ladderHeight).getBytes());
        br.close();
        out.close();
    }

    public static void DFS(int source, int dest, boolean[] visited,
                           ArrayList<ArrayList<IntegerPair>> adjList,
                           int heightToExplore,
                           PriorityQueue<IntegerPair> vertexToExplore) {
        visited[source] = true;
        if (source == dest) return;

        for (IntegerPair v : adjList.get(source)) {
            int val = v.first();
            boolean isValid = heightToExplore >= v.second();

            if (!isValid && !visited[val]) {
                vertexToExplore.add(new IntegerPair(source, v.second()));
                continue;
            }

            if (!visited[val]) {
                DFS(val, dest, visited, adjList, heightToExplore, vertexToExplore);
            }
        }
    }

    public static void BFS(int source, int dest, boolean[] visited,
                           ArrayList<ArrayList<IntegerPair>> adjList,
                           int heightToExplore,
                           PriorityQueue<IntegerPair> vertexToExplore) {
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        visited[source] = true;

        while (!q.isEmpty()) {
            int u = q.poll();

            if (u == dest) return;

            for (IntegerPair v : adjList.get(u)) {
                int val = v.first();
                boolean isValid = heightToExplore >= v.second();

                if (!isValid && !visited[val]) {
                    vertexToExplore.add(new IntegerPair(u, v.second()));
                    continue;
                }

                if (!visited[val]) {
                    visited[val] = true;
                    q.add(val);
                }
            }
        }
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    protected Integer first;
    protected Integer second;

    public IntegerPair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    // sort by weight
    public int compareTo(IntegerPair o) {
        return this.second() - o.second();
    }

    public Integer first() { return first; }
    public Integer second() { return second; }

    public String toString() {
        return String.format("(%d %d)", first, second);
    }
}
