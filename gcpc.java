import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;

public class gcpc {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nTeam = Integer.parseInt(st.nextToken());
        int nEvent = Integer.parseInt(st.nextToken());
        AVL<Team> avl = new AVL<>();

        // Team t15 = new Team(15, 0, 0);
        // Team t23 = new Team(23, 0, 0);
        // Team t6 = new Team(6, 0, 0);
        // Team t71 = new Team(71, 0, 0);
        // Team t50 = new Team(50, 0, 0);
        // Team t4 = new Team(4, 0, 0);
        // Team t7 = new Team(7, 0, 0);
        // Team t5 = new Team(5, 0, 0);
        // Team t22 = new Team(22, 0, 0);
        //
        // avl.insert(t15);
        // avl.insert(t23);
        // avl.insert(t6);
        // avl.insert(t71);
        // avl.insert(t50);
        // avl.insert(t4);
        // avl.insert(t7);
        // avl.insert(t5);
        //
        // System.out.println(avl.search(t71));         // found, 71
        // System.out.println(avl.search(t7));          // found, 7
        // System.out.println(avl.search(t22));         // not found, -1
        //
        // System.out.println(avl.findMin());          // 4
        // System.out.println(avl.findMax());          // 71
        //
        // System.out.println(avl.successor(t23));      // 50
        // System.out.println(avl.successor(t7));       // 15
        // System.out.println(avl.successor(t71));      // -1
        // System.out.println(avl.predecessor(t23));    // 15
        // System.out.println(avl.predecessor(t7));     // 6
        // System.out.println(avl.predecessor(t71));    // 50
        //
        // System.out.println("In-order:");
        // avl.inorder();

        int[] solvedArr = new int[nTeam];
        long[] penaltyArr = new long[nTeam];
        Team favouriteTeam = new Team(0, solvedArr[0], penaltyArr[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nEvent; i++) {
            st = new StringTokenizer(br.readLine());
            int team = Integer.parseInt(st.nextToken()) - 1;
            int penalty = Integer.parseInt(st.nextToken());

            avl.remove(new Team(team, solvedArr[team], penaltyArr[team]));
            solvedArr[team]++;
            penaltyArr[team] += penalty;
            avl.insert(new Team(team, solvedArr[team], penaltyArr[team]));

            sb.append(nTeam - avl.rank(favouriteTeam) + 1);
            sb.append("\n");
        }

        out.write(sb.toString().getBytes());
        br.close();
        out.close();
    }
}

class Node<T extends Comparable<T>> {
    public T v;
    public Node<T> left, right, parent;
    public int height;
    public int size;

    public Node(T v) {
        this.v = v;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 0;
        this.size = 1;
    }

    public String toString() {
        return v.toString();
    }
}

class Team implements Comparable<Team> {
    public int id;
    public int totalSolved;
    public long totalPenalty;

    public Team(int id, int totalSolved, long totalPenalty) {
        this.id = id;
        this.totalSolved = totalSolved;
        this.totalPenalty = totalPenalty;
    }

    public int compareTo(Team other) {
        if (this.totalSolved > other.totalSolved || this.totalSolved == other.totalSolved && this.totalPenalty < other.totalPenalty) {
            return 1;
        } else if (this.totalSolved == other.totalSolved && this.totalPenalty == other.totalPenalty) {
            if (this.id < other.id) return 1;
            else if (this.id > other.id) return -1;
            return 0;
        }

        return -1;
    }

    public String toString() {
        return String.format("Team %d: solved - %d, penalty - %d", id, totalSolved, totalPenalty);
    }
}

class AVL<T extends Comparable<T>> {
    Node<T> root;

    public AVL() {
        this.root = null;
    }

    public T search(T val) {
        Node<T> res = search(root, val);
        return res == null ? (T)(new Team(-1, 0, 0)) : res.v;
    }

    public Node<T> search(Node<T> curr, T val) {
        if (curr == null) return null;
        else if (val.compareTo(curr.v) == 0) return curr;
        else if (val.compareTo(curr.v) > 0) return search(curr.right, val);
        else return search(curr.left, val);
    }

    public T findMin() {
        return findMin(root);
    }

    public T findMin(Node<T> curr) {
        if (curr.left == null) return curr.v;
        return findMin(curr.left);
    }

    public T findMax() {
        return findMax(root);
    }

    public T findMax(Node<T> curr) {
        if (curr.right == null) return curr.v;
        return findMax(curr.right);
    }

    public T successor(T val) {
      Node<T> vPos = search(root, val);
      return vPos == null ? (T)(new Team(-1, 0, 0)) : successor(vPos);
    }

    public T successor(Node<T> curr) {
        if (curr.right != null) return findMin(curr.right);

        Node<T> par = curr.parent;
        Node<T> cur = curr;

        while ((par != null) && (cur == par.right)) {
            cur = par;
            par = cur.parent;
        }
        return par == null ? null : par.v;
    }

    public T predecessor(T val) {
      Node<T> vPos = search(root, val);
      return vPos == null ? (T)(new Team(-1, 0, 0)) : predecessor(vPos);
    }

    public T predecessor(Node<T> curr) {
        if (curr.left != null) return findMax(curr.left);

        Node<T> par = curr.parent;
        Node<T> cur = curr;

        while ((par != null) && (cur == par.left)) {
            cur = par;
            par = cur.parent;
        }
        return par == null ? (T)(new Team(-1, 0, 0)) : par.v;
    }

    public void inorder() {
    	inorder(root);
    	System.out.println();
    }

    public void inorder(Node<T> curr) {
        if (curr == null) return;
        inorder(curr.left);
        System.out.println(curr.v);
        inorder(curr.right);
    }

    public void insert(T val) {
        root = insert(root, val);
    }

    private Node<T> insert(Node<T> curr, T val) {
        if (curr == null) return new Node<T>(val);

        if (val.compareTo(curr.v) > 0) {
            curr.right = insert(curr.right, val);
            curr.right.parent = curr;
            curr.height = Math.max(curr.right.height + 1, curr.height);
        } else {
            curr.left = insert(curr.left, val);
            curr.left.parent = curr;
            curr.height = Math.max(curr.left.height + 1, curr.height);
        }

        curr = balance(curr);
        return curr;
    }

    public void remove(T val) {
        root = remove(root, val);
    }

    private Node<T> remove(Node<T> curr, T val) {
        if (curr == null) return curr;

        Node<T> temp = curr.parent;
        if (val.compareTo(curr.v) > 0) {
            curr.right = remove(curr.right, val);
        } else if (val.compareTo(curr.v) < 0) {
            curr.left = remove(curr.left, val);
        } else {
            temp = curr.parent;
            if (curr.left == null && curr.right == null) {
                curr = null;
            } else if (curr.left == null && curr.right != null) {
                curr.right.parent = curr.parent;
                curr = curr.right;
            } else if (curr.left != null && curr.right == null) {
                curr.left.parent = curr.parent;
                curr = curr.left;
            } else {
                T successorV = successor(curr);
                curr.v = successorV;
                curr.right = remove(curr.right, successorV);
            }
        }

        curr = balance(temp);
        return curr;
    }

    private Node<T> balance(Node<T> curr) {

        return curr;
    }

    private Node<T> rightRotate(Node<T> curr) {
        if (curr == null) return null;
        Node<T> child = curr.left;
        Node<T> temp = curr.left.right;
        curr.left.right = curr;
        curr.left = temp;
        return child;
    }

    private Node<T> leftRotate(Node<T> curr) {
        if (curr == null) return null;
        Node<T> child = curr.right;
        Node<T> temp = curr.right.left;
        curr.right.left = curr;
        curr.right = temp;
        return child;
    }

    public int rank(T val) {
        return rank(root, val);
    }

    private int rank(Node<T> curr, T val) {
        if (curr == null) {
            return 0;
        } else if (val.compareTo(curr.v) < 0) {
            return rank(curr.left, val);
        } else if (val.compareTo(curr.v) > 0) {
            return size(curr.left) + 1 + rank(curr.right, val);
        } else {
            return size(curr.left);
        }
    }

    public int size(Node<T> curr) {
        if (curr == null) return -1;
        return curr.size;
    }
}
