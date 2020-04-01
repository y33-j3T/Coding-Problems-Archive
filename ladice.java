import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;

public class ladice {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nItem = Integer.parseInt(st.nextToken());
        int nDrawer = Integer.parseInt(st.nextToken());

        UnionFind disjSet = new UnionFind(nDrawer);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nItem; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            disjSet.unionSet(a, b);
            System.out.println(disjSet.numDisjointSets());
            boolean filled = disjSet.fillVacancy(disjSet.findSet(a));

            sb.append(filled ? "LADICA\n" : "SMECE\n");
        }

        out.write(sb.toString().getBytes());
        br.close();
        out.close();
    }
}

class UnionFind {
    public int[] p;
    public int[] rank;
    public boolean[] isVacant;
    public int numSets;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        isVacant = new boolean[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            isVacant[i] = true;
        }
    }

    public int findSet(int i) {
        if (p[i] == i) return i;

        p[i] = findSet(p[i]);
        return p[i];
    }

    public boolean fillVacancy(int setN) {
        for (int i = 0; i < p.length; i++) {
            if (p[i] == setN && isVacant[i]) {
                isVacant[i] = false;
                return true;
            }
        }
        return false;
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
