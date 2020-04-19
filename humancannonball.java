import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class humancannonball {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st;

        ArrayList<Edge> edgeList = new ArrayList<>();
        ArrayList<Coordinate> coorList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        Coordinate start = new Coordinate(Double.parseDouble(st.nextToken()),
                                          Double.parseDouble(st.nextToken()),
                                          false);
        st = new StringTokenizer(br.readLine());
        Coordinate end = new Coordinate(Double.parseDouble(st.nextToken()),
                                        Double.parseDouble(st.nextToken()),
                                        false);

        coorList.add(start);
        coorList.add(end);

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coorList.add(new Coordinate(Double.parseDouble(st.nextToken()),
                                        Double.parseDouble(st.nextToken()),
                                        true));
        }

        for (Coordinate v1 : coorList) {
            for (Coordinate v2 : coorList) {
                if (v1.compareTo(v2) == 0) continue;

                double dist = v1.distance(v2);
                double time1 = dist / 5;
                double time2 = v1.isCannon() ? (2 + Math.abs(dist - 50) / 5) : Double.MAX_VALUE;

                edgeList.add(new Edge(v1, v2, Math.min(time1, time2)));
            }
        }

        HashMap<Coordinate, Double> duration = new HashMap<>();
        for (Coordinate v : coorList) {
            duration.put(v, Double.MAX_VALUE);
        }
        duration.replace(start, new Double(0));

        // Bellman Ford's
        for (int i = 0; i < duration.size() - 1; i++) {
            for (Edge edge : edgeList) {
                if (duration.get(edge.src()) + edge.duration() < duration.get(edge.dest()))
                    duration.put(edge.dest(), duration.get(edge.src()) + edge.duration());
            }
        }

        out.write(Double.toString(duration.get(end)).getBytes());
        br.close();
        out.close();
    }
}

// respresentation for coordinates
class Coordinate implements Comparable<Coordinate> {
    protected Double x;
    protected Double y;
    protected boolean isCannon;

    public Coordinate(Double x, Double y, boolean isCannon) {
        this.x = x;
        this.y = y;
        this.isCannon = isCannon;
    }

    public int compareTo(Coordinate o) {
        double num1 = Math.sqrt(Math.pow(this.x(), 2) + Math.pow(this.y(), 2));
        double num2 = Math.sqrt(Math.pow(o.x(), 2) + Math.pow(o.y(), 2));
        if (num1 - num2 > 0) return 1;
        else if (num1 - num2 < 0) return -1;
        return 0;
    }

    public Double distance(Coordinate o) {
        double dX = this.x - o.x;
        double dY = this.y - o.y;
        return Math.sqrt(dX * dX + dY * dY);
    }

    public Double x() { return x; }
    public Double y() { return y; }
    public boolean isCannon() { return isCannon; }

    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}

class Edge implements Comparable<Edge> {
    protected Coordinate src;
    protected Coordinate dest;
    protected Double duration;

    public Edge(Coordinate src, Coordinate dest, Double duration) {
        this.src = src;
        this.dest = dest;
        this.duration = duration;
    }

    public int compareTo(Edge o) {
        if (this.duration() - o.duration() > 0) return 1;
        else if (this.duration() - o.duration() < 0) return -1;
        return 0;
    }

    public Coordinate src() { return src; }
    public Coordinate dest() { return dest; }
    public Double duration() { return duration; }
}
