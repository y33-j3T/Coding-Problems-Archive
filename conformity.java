import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

public class conformity {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);

        Map<HashSet> combo_count = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        // StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            Set<Integer> combo = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                combo.add(Integer.parseInt(st.nextToken()));
            }

            if (combo_count.containsKey(combo)) {
                combo_count.get(combo)++;
            } else {
                combo_count.put(combo, 1);
            }
            // String command = st.nextToken();
            // int x = Integer.parseInt(st.nextToken());
            // String x = Integer.parseInt(br.readLine().replaceAll("\\s+", ""));
            out.write(x);
        }
    }
}
