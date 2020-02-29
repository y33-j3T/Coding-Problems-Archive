import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayDeque;
import java.util.Deque;

public class teque {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> teque = new HashMap<>();
        int head = 0;
        int mid = 0;
        int tail = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x = Integer.parseInt(st.nextToken());

            if (command.equals("get")) {
                continue;
            }

            if (command.equals("push_back")) {

            } else if (command.equals("push_front")) {

            } else {

            }
        }

        out.write(sb.toString().getBytes());

        br.close();
        out.close();
    }
}
