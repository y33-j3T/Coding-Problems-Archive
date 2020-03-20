import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Collections;

public class kattissquest {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);

        int n = Integer.parseInt(br.readLine());

        TreeMap<Integer, PriorityQueue<Integer>> quest = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("add")) {
                int energy = Integer.parseInt(st.nextToken());
                int gold = Integer.parseInt(st.nextToken());

                if (quest.containsKey(energy)) {
                     quest.get(energy).add(gold);
                } else {
                    PriorityQueue<Integer> goldQ = new PriorityQueue<>(Collections.reverseOrder());
                    goldQ.add(gold);
                    quest.put(energy, goldQ);
                }

                continue;
            }

            int energy = Integer.parseInt(st.nextToken());
            Map.Entry<Integer, PriorityQueue<Integer>> temp = quest.floorEntry(energy);

            long totalGold = 0;
            while (temp != null) {
                PriorityQueue<Integer> goldQ = temp.getValue();
                int energyUsed = temp.getKey();

                totalGold += goldQ.poll();
                if (goldQ.isEmpty()) {
                    quest.remove(energyUsed);
                }

                energy -= energyUsed;
                temp = quest.floorEntry(energy);
            }

            sb.append(totalGold);
            sb.append("\n");
        }

        out.write(sb.toString().getBytes());
        br.close();
        out.close();
    }
}
