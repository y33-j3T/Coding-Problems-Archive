import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class millionairemadness {
    public static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static final int INF = Integer.MAX_VALUE;
    public static ArrayList<ArrayList<IntegerPair>> adjList = new ArrayList<>();
    public static ArrayList<Integer> D = new ArrayList<>();
    public static ArrayList<Integer> p = new ArrayList<>();

    public static void initSSSP(int n, int s) {
        D.clear();
        D.addAll(Collections.nCopies(n, INF));
        p.clear();
        p.addAll(Collections.nCopies(n, -1));
        D.set(s, 0);
    }

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
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<IntegerPair> tempList = new ArrayList<>();

                for (int[] dir : DIRS) {
                    int nextRow = i + dir[0];
                    int nextCol = j + dir[1];

                    boolean isValidRow = nextRow >= 0 && nextRow < m;
                    boolean isValidCol = nextCol >= 0 && nextCol < n;
                    if (!isValidRow || !isValidCol) continue;

                    int weight = weightGrid[nextRow][nextCol] - weightGrid[i][j];
                    if (weight < 0) weight = 0;

                    tempList.add(new IntegerPair(weight, vertexGrid[nextRow][nextCol]));
                }

                adjList.add(tempList);
            }
        }

        // Modified Dijkstra's Algorithm
        int size = m * n;
        int sourceVertex = vertexGrid[0][0];
        initSSSP(size, sourceVertex);

        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
        pq.add(new IntegerPair(0, sourceVertex));
        while (!pq.isEmpty()) {
            IntegerPair temp = pq.poll();
            int d = temp.first(), u = temp.second();
            if (d == D.get(u)) {
                for (IntegerPair v : adjList.get(u)) {
                    int w_u_u2 = v.first(), u2 = v.second();
                    if (D.get(u2) > D.get(u) + w_u_u2) {
                        D.set(u2, D.get(u) + w_u_u2);
                        p.set(u2, u);
                        pq.add(new IntegerPair(D.get(u2), u2));
                    }
                }
            }
        }

        int res = -1;
        int u = vertexGrid[m - 1][n - 1];
        System.out.println(D.get(u));
        while (p.get(u) != -1) {
            System.out.printf(" %d", u);
            int tempRes = D.get(u) - D.get(p.get(u));
            if (tempRes > res) res = tempRes;
            u = p.get(u);
        }
        System.out.println();
        // System.out.println(Arrays.toString(p.toArray()));
        out.write(Integer.toString(res).getBytes());
        br.close();
        out.close();
    }

    public static void backtrack(int s, int u) {
      if (u == -1 || u == s) {
        System.out.printf("%d", u);
        return;
      }
      backtrack(s, p.get(u));
      System.out.printf(" %d", u);
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    protected Integer first;
    protected Integer second;

    public IntegerPair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    // put weight in first position, sort by weight
    public int compareTo(IntegerPair o) {
        return this.first() - o.first();
    }

    public Integer first() { return first; }
    public Integer second() { return second; }

    public String toString() {
        return String.format("(%d %d)", first, second);
    }
}
