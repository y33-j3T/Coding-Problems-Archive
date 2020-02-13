import java.util.Scanner;

public class trainpassengers {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");

        int capacity = Integer.parseInt(input[0]);
        int numStops = Integer.parseInt(input[1]);
        boolean isPossible = true;
        int passengerCount = 0;

        for (int i = 0 ; i < numStops ; i++) {
            String[] input2 = s.nextLine().split(" ");
            int leftCount = Integer.parseInt(input2[0]);
            int enterCount = Integer.parseInt(input2[1]);
            int waitCount = Integer.parseInt(input2[2]);

            if (i == 0 && leftCount != 0) {
                isPossible = false;
            }

            passengerCount = passengerCount + enterCount - leftCount;

            if (passengerCount > capacity ||
                passengerCount < 0 ||
                (capacity - passengerCount > 0 && waitCount > 0)) {
                    isPossible = false;
            }

            if (i == numStops - 1 && (waitCount != 0 || passengerCount != 0)) {
                isPossible = false;
            }

        }

        System.out.println(isPossible ? "possible" : "impossible");

        return;
    }
}
