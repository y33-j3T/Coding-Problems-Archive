import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class _10kindsofpeople {
    public static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][c];
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String temp = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = temp.charAt(j);
                visited[i][j] = false;
            }
        }

        // start marking regions
        // cells with same regionIdx are in same region
        int regionIdx = 0;
        int[][] regionGrid = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j]) {
                    char charToExplore = map[i][j];
                    BFS(i, j, r, c, map, visited, regionGrid, regionIdx, charToExplore);
                    // using DFS results in TLE
                    regionIdx++;
                }
            }
        }

        // start query
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            if (regionGrid[r1][c1] != regionGrid[r2][c2]) {
                sb.append("neither\n");
            } else {
                boolean isZero = map[r1][c1] == '0';
                sb.append(isZero ? "binary" : "decimal");
                sb.append("\n");
            }
        }

        out.write(sb.toString().getBytes());
        br.close();
        out.close();
    }

    public static void DFS(int row, int col, int maxRow, int maxCol,
    char[][] grid, boolean[][] visited,
    int[][] regionGrid, int regionIdx, char charToExplore) {
        visited[row][col] = true;
        regionGrid[row][col] = regionIdx;

        for (int[] dir : DIRS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            boolean isValidRow = nextRow >= 0 && nextRow < maxRow;
            boolean isValidCol = nextCol >= 0 && nextCol < maxCol;
            if (!isValidRow || !isValidCol) continue;

            boolean isCharToExplore = grid[nextRow][nextCol] == charToExplore;
            if (!isCharToExplore) continue;

            boolean isVisited = visited[nextRow][nextCol];
            if (isVisited) continue;

            DFS(nextRow, nextCol, maxRow, maxCol, grid, visited, regionGrid, regionIdx, charToExplore);
        }
    }

    public static void BFS(int row, int col, int maxRow, int maxCol,
    char[][] grid, boolean[][] visited,
    int[][] regionGrid, int regionIdx, char charToExplore) {
        Queue<IntegerPair> q = new LinkedList<>();
        visited[row][col] = true;
        regionGrid[row][col] = regionIdx;

        q.add(new IntegerPair(row, col));

        while (!q.isEmpty()) {
            IntegerPair u = q.poll();
            row = u.first();
            col = u.second();
            for (int[] dir : DIRS) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];

                boolean isValidRow = nextRow >= 0 && nextRow < maxRow;
                boolean isValidCol = nextCol >= 0 && nextCol < maxCol;
                if (!isValidRow || !isValidCol) continue;

                boolean isCharToExplore = grid[nextRow][nextCol] == charToExplore;
                if (!isCharToExplore) continue;

                boolean isVisited = visited[nextRow][nextCol];
                if (isVisited) continue;

                visited[nextRow][nextCol] = true;
                regionGrid[nextRow][nextCol] = regionIdx;
                q.add(new IntegerPair(nextRow, nextCol));
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

    public int compareTo(IntegerPair o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else
            return this.second() - o.second();
    }

    public Integer first() { return first; }
    public Integer second() { return second; }
}
