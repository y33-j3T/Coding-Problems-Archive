import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;

public class islands3 {
    public static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int res = 0;
        boolean[][] visited = new boolean[r][c];

        char[][] island = new char[r][c];
        for (int i = 0; i < r; i++) {
            String temp = br.readLine();
            for (int j = 0; j < c; j++) {
                island[i][j] = temp.charAt(j);
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (island[i][j] == 'L' && !visited[i][j]) {
                    res++;
                    DFS(i, j, r, c, island, visited);
                }
            }
        }

        out.write(Integer.toString(res).getBytes());
        br.close();
        out.close();
    }

    public static void DFS(int row, int col, int maxRow, int maxCol, char[][] grid, boolean[][] visitedArr) {
        visitedArr[row][col] = true;
        for (int[] dir : DIRS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            boolean isValidRow = nextRow >= 0 && nextRow < maxRow;
            boolean isValidCol = nextCol >= 0 && nextCol < maxCol;
            if (!isValidRow || !isValidCol) continue;

            boolean isCL = grid[nextRow][nextCol] == 'C' || grid[nextRow][nextCol] == 'L';
            if (!isCL) continue;

            boolean isVisited = visitedArr[nextRow][nextCol];
            if (isVisited) continue;

            DFS(nextRow, nextCol, maxRow, maxCol, grid, visitedArr);
        }
    }
}
