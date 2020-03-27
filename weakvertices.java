import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class weakvertices {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedOutputStream out = new BufferedOutputStream(System.out);
		StringBuilder sb = new StringBuilder();

		int n;
		while((n = Integer.parseInt(br.readLine())) != -1) {
			// build adjacency matrix
			int[][] adjMat = new int[n][n];
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					adjMat[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// mark whether vertex is part of triangle
			int[] isTriangle = new int[n];

			// try all possible permutations of 3 vertices to see if they form a triangle
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (j == i) continue;
					for (int k = 0; k < n; k++) {
						if (k == i || k == j) continue;

						// mark vertices as part of triangle
						if (adjMat[i][j] == 1 && adjMat[j][k] == 1 && adjMat[i][k] == 1) {
							isTriangle[i] = 1;
							isTriangle[j] = 1;
							isTriangle[k] = 1;
						}
					}
				}
			}

			// vertices that are not part of triangle
			for (int i = 0; i < n; i++) {
				sb.append(isTriangle[i] == 0 ? i: "");
				sb.append(" ");
			}

			sb.append("\n");
		}

		out.write(sb.toString().getBytes());
		br.close();
		out.close();
	}
}
