import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class conformity {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);

        Map<Set, Integer> comboCount = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            Set<Integer> combo = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                combo.add(Integer.parseInt(st.nextToken()));
            }

            if (comboCount.containsKey(combo)) {
                comboCount.put(combo, comboCount.get(combo) + 1);
            } else {
                comboCount.put(combo, 1);
            }
        }
		
		int max = Collections.max(comboCount.values());
		int count = Collections.frequency(comboCount.values(), max);

		out.write(Integer.toString(count * max).getBytes());

		out.close();
		br.close();
    }
}