import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedOutputStream;

public class joinstrings {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); BufferedOutputStream out = new BufferedOutputStream(System.out)) {
            int n = Integer.parseInt(br.readLine());

            String[] strArr = new String[n];

            for (int i = 0; i < n; i++) {
                strArr[i] = br.readLine();
            }

            int idxOut = 0;
            String input;
            while ((input = br.readLine()) != null) {
                if (input.equals("")) break;
                StringTokenizer st = new StringTokenizer(input);
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                strArr[a] += strArr[b];
                strArr[b] = "";
                idxOut = a;
            }

            out.write(strArr[idxOut].getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
