import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class workstations {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        final int inactiveThreshold = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new sortTimeSlot());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = start + Integer.parseInt(st.nextToken());
            pq.add(new Integer[] {start, end});
        }

        int nSaved = 0;
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.add(pq.poll()[1]);

        while (!pq.isEmpty()) {
            Integer[] timeSlot = pq.poll();
            endTimes.add(timeSlot[1]);  // add in newest expire time of a workstation

            if (timeSlot[0] < endTimes.peek()) {
                continue;
            }

            while (!endTimes.isEmpty()) {
                int time = endTimes.poll();  // get earliest opened computer
                int timeRange = timeSlot[0] - time;
                if (timeRange > inactiveThreshold) {  // expired, continue searching
                    continue;
                } else if (timeRange < 0) {  // still in use, open new workstation
                    endTimes.add(time);
                    break;
                } else {  // not expired, not in use, number of work saved + 1
                    nSaved++;
                    break;
                }
            }
        }

        out.write(Integer.toString(nSaved).getBytes());

        br.close();
        out.close();
    }
}

class sortTimeSlot implements Comparator<Integer[]> {
    public int compare(Integer[] intArr1, Integer[] intArr2) {
        if (intArr1[0] == intArr2[0]) {
            return intArr1[1].compareTo(intArr2[1]);
        }
        return intArr1[0].compareTo(intArr2[0]);
    }
}
