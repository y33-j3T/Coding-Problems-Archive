import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.*;

class TestClass {
    public static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        String[][] weightGrid = new String[n][n];
        int[][] vertexGrid = new int[n][n];

        // label and initialize weights for each coordinate
        int vertex = 0;
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < n ; j++) {
                vertexGrid[i][j] = vertex++;
                weightGrid[i][j] = st.nextToken();
            }
        }

        // make adjacency list for every coordinate
        ArrayList<ArrayList<IntegerPair>> adjList = new ArrayList<>();
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                ArrayList<IntegerPair> tmpList = new ArrayList<>();

                for (int[] dir : DIRS) {
                    int nextRow = i + dir[0];
                    int nextCol = j + dir[1];

                    boolean isValidRow = nextRow >= 0 && nextRow < n;
                    boolean isValidCol = nextCol >= 0 && nextCol < n;

                    if (!isValidRow || !isValidCol) continue;

                    int weight = 1;
                    if ((dir[0] == 1 && weightGrid[i][j].equals("U")) ||
                        (dir[0] == -1 && weightGrid[i][j].equals("D")) ||
                        (dir[1] == 1 && weightGrid[i][j].equals("R")) ||
                        (dir[1] == -1 && weightGrid[i][j].equals("L"))) {
                        weight = 0;
                    } 

                    tmpList.add(new IntegerPair(vertexGrid[nextRow][nextCol], weight));
                }

                adjList.add(tmpList);
            }
        }

        // main
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()); 
        int x2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int y1 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        int ladderHeight = -1;
        boolean[] visited = new boolean[n * n];
        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
        int src = vertexGrid[x1][x2];
        int dst = vertexGrid[y1][y2];

        while (!pq.isEmpty()) {
            IntegerPair v = pq.poll();
            ladderHeight = v.second();

            BFS(v.first(), dst, visited, adjList, ladderHeight, pq);

            if (visited[dst]) break;
        }

        out.write(Integer.toString(ladderHeight).getBytes());
        br.close();
        out.close();
    }

    // Breadth-First Search
    public static void BFS(int src, int dst, boolean[] visited, 
                            ArrayList<ArrayList<IntegerPair>> adjList,
                            int heightToExplore,
                            PriorityQueue<IntegerPair> vertexToExplore) {

        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = true;

        while (!q.isEmpty()) {
            int u = q.poll();

            if (u == dst) return;

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
    Integer first;
    Integer second;

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
}

