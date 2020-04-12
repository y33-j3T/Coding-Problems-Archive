import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

/**
* Kruskal's MST Algorithm
* 1. Make edge list, sort it by weight
* 2. Set up UFDS
* 3. for edge in edge list:
*       if (u, v) are not in the same set:
*           union set containing u & v
*           output edge (u, v)
*/

public class lostmap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] adjMat = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adjMat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<IntegerTriplet> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edgeList.add(new IntegerTriplet(i, j, adjMat[i][j]));
            }
        }

        Collections.sort(edgeList);

        StringBuilder sb = new StringBuilder();
        UnionFind disjSet = new UnionFind(n);
        for (IntegerTriplet edge : edgeList) {
            int v1 = edge.first(), v2 = edge.second();
            if (!disjSet.isSameSet(v1, v2)) {
                disjSet.unionSet(v1, v2);
                sb.append(v1 + 1).append(" ").append(v2 + 1).append("\n");
            }
        }

        out.write(sb.toString().getBytes());
        br.close();
        out.close();
    }
}

class IntegerTriplet implements Comparable<IntegerTriplet> {
    protected Integer first;
    protected Integer second;
    protected Integer third;

    public IntegerTriplet(Integer first, Integer second, Integer third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    // sort by weight
    public int compareTo(IntegerTriplet o) {
        return this.third() - o.third();
    }

    public Integer first() { return first; }
    public Integer second() { return second; }
    public Integer third() { return third; }
}

class UnionFind {
    public int[] p;
    public int[] rank;
    public int numSets;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    public int findSet(int i) {
        if (p[i] == i) return i;

        p[i] = findSet(p[i]);
        return p[i];
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (isSameSet(i, j)) return;

        numSets--;
        int x = findSet(i);
        int y = findSet(j);

        // use rank to keep tree short
        if (rank[x] > rank[y]) {
            p[y] = x;
        } else {
            p[x] = y;
            if (rank[x] == rank[y]) rank[y]++;
        }
    }

    public int numDisjointSets() {
        return numSets;
    }
}

/**
* Alternative algorithm: Prim's
* 1. Make adjList (optional)
* 2. Set first vertex to taken, add neighbours to priority queue of IntegerTriplets
* 3. while queue is not empty:
*       1. dequeue edge
*       2. if v of edge is not taken:
*           1. ouput edge (u, v)
*           2. set v to taken
*           3. add neighbors of v that are not taken to queue
*/
