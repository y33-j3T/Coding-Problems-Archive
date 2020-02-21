import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class coconut {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Queue<Hand> q = new LinkedList<>();

        String[] input = s.nextLine().split(" ");

        int numSyllables = Integer.parseInt(input[0]);
        int numPlayers = Integer.parseInt(input[1]);

        for (int i = 1; i < numPlayers + 1; i++) {
            q.add(new Hand(i));
            q.add(new Hand(i));
        }

        boolean splitFlag = false;
        while (q.size() > 1) {
            int count = numSyllables;

            // keep passing while not the last person
            while(count > 1) {
                Hand hand = q.peek();

                if (hand.getStatus() == "intact") {
                    q.add(q.remove());
                    q.add(q.remove());
                } else {
                    q.add(q.remove());
                }

                // split occured, change next hand status from "intact" -> "fist"
                if (splitFlag) {
                    q.peek().setStatus("fist");
                    splitFlag = false;
                }

                count--;
            }

            // last person
            Hand hand = q.peek();
            if (hand.getStatus() == "intact") {
                hand.setStatus("fist");
                splitFlag = true;
            } else if (hand.getStatus() == "fist") {
                hand.setStatus("down");
                q.add(q.remove());
            } else {
                q.remove();
            }
        }

        System.out.println(q.peek().getId());
    }
}

// player hand statuses: intact, fist, down
class Hand {
    protected int id;
    protected String status;

    public Hand(int id) {
        this.id = id;
        this.status = "intact";
    }

    public int getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
