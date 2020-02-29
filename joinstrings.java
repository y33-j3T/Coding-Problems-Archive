import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;
import java.io.IOException;

public class joinstrings {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedOutputStream out = new BufferedOutputStream(System.out);

        int n = Integer.parseInt(br.readLine());

        String[] strArr = new String[n + 1];
        int[] idxMap = new int[n + 1];
        int[] cache = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            strArr[i] = br.readLine();
        }

        int a = 1;
        int b = 1;
        int x = a;
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (idxMap[a] == 0) {
                idxMap[a] = b;
                cache[a] = b;
                continue;
            }

            x = a;
            while (cache[x] != 0) {
                x = cache[x];
            }
            cache[a] = b;
            idxMap[x] = b;
        }

        StringBuilder sb = new StringBuilder();
        while (idxMap[a] != 0) {
            sb.append(strArr[a]);
            a = idxMap[a];
        }
        sb.append(strArr[a]);

        out.write(sb.toString().getBytes());

        br.close();
        out.close();
    };
}
