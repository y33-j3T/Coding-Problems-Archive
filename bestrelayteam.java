/* Pseudocode (ref: https://nordic.icpc.io/ncpc2017/ncpc17slides.pdf)
1. Pre-sort runners by their flying start time
2. Try every runner on the first leg
3. For every choice, fill up with 3 fastest remaining flying start runners
*/

import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;

public class bestrelayteam {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int numRunners = s.nextInt();
        s.nextLine();

        Runner[] runnerArr = new Runner[numRunners];

        for (int i = 0 ; i < numRunners ; i++) {
            String[] input = s.nextLine().split(" ");
            String runnerName = input[0];
            double timeFirst = Double.parseDouble(input[1]);
            double timeOther = Double.parseDouble(input[2]);
            Runner runner = new Runner(runnerName, timeFirst, timeOther);
            runnerArr[i] = runner;
        }

        Arrays.sort(runnerArr, new SortByOtherLeg());

        double minTime = Double.MAX_VALUE;
        Runner minTimeRunner = new Runner();
        for (Runner runner : runnerArr) {
            double time = runner.getFirstLeg();

            int vacancy = 3;
            for (Runner runner2 : runnerArr) {
                if (runner2 == runner) continue;
                time += runner2.getOtherLeg();
                vacancy -= 1;
                if (vacancy == 0) break;
            }

            if (time < minTime) {
                minTime = time;
                minTimeRunner = runner;
            }
        }

        System.out.println(String.format("%.2f", minTime));
        System.out.println(minTimeRunner.getName());
        int vacancy = 3;
        for (Runner runner : runnerArr) {
            if (runner == minTimeRunner) continue;
            System.out.println(runner.getName());
            vacancy -= 1;
            if (vacancy == 0) break;
        }
    }
}

class Runner {
    protected String name;
    protected double firstLeg;
    protected double otherLeg;

    public Runner() {}

    public Runner(String name, double firstLeg, double otherLeg) {
        this.name = name;
        this.firstLeg = firstLeg;
        this.otherLeg = otherLeg;
    }

    public String getName() {
        return this.name;
    }
    public double getFirstLeg() {
        return this.firstLeg;
    }

    public double getOtherLeg() {
        return this.otherLeg;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class SortByOtherLeg implements Comparator<Runner> {
    public int compare(Runner a, Runner b) {
        if (a.otherLeg - b.otherLeg > 0) return 1;
        if (a.otherLeg - b.otherLeg < 0) return -1;
        return 0;
    }
}
