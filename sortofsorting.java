import java.util.Scanner;
import java.util.Arrays;

/* Pseudocode
while hasNextInt
    1. Get number of names, which is the integer input
    2. If number of names == 0, break & end program
    3. For number of names, get name input into an array
    4. Sort the array for first two alphabets
    5. Print out all names in array
    6. Print one new line
*/

public class sortofsorting{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while (s.hasNextInt()) {
            int numNames = s.nextInt();
            s.nextLine();

            if (numNames == 0) {
                break;
            }

            String[] nameArr = new String[numNames];
            for (int i = 0 ; i < numNames ; i++) {
                nameArr[i] = s.nextLine();
            }

            insertionSort(nameArr, 1);
            insertionSort(nameArr, 0);

            for (String name : nameArr) {
                System.out.println(name);
            }
            System.out.println();
        }

        return;
    }

    public static void insertionSort(String[] arr, int index) {

        // from the second element to the last
        for (int i = 1; i < arr.length; i++) {
            // insert arr[i] into a sorted array
            String currentElement = arr[i];
            int k;
            for (k = i - 1; k >= 0 && arr[k].substring(index, index + 1).compareTo(currentElement.substring(index, index + 1)) > 0; k--) {
                arr[k + 1] = arr[k];
            }

            // insert the current element into arr[k+1]
            arr[k + 1] = currentElement;
        }

    }
}
