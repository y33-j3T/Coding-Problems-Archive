/* Pseudocode
1. Initialize a HashMap which maps the characters to the numbers to press
2. Take in number of inputs
3. For number of inputs
    1. Take in input
    2. For input.length
        1. Take input[i] & put it into HashMap as key
        2. Append value to result string (check if a spece char is needed)
    3. Print result string
*/

import java.util.Scanner;
import java.util.HashMap;

public class t9spelling {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);

        HashMap<Character, String> map = new HashMap<>();
        map.put('a', "2");
        map.put('b', "22");
        map.put('c', "222");
        map.put('d', "3");
        map.put('e', "33");
        map.put('f', "333");
        map.put('g', "4");
        map.put('h', "44");
        map.put('i', "444");
        map.put('j', "5");
        map.put('k', "55");
        map.put('l', "555");
        map.put('m', "6");
        map.put('n', "66");
        map.put('o', "666");
        map.put('p', "7");
        map.put('q', "77");
        map.put('r', "777");
        map.put('s', "7777");
        map.put('t', "8");
        map.put('u', "88");
        map.put('v', "888");
        map.put('w', "9");
        map.put('x', "99");
        map.put('y', "999");
        map.put('z', "9999");
        map.put(' ', "0");

        int n = s.nextInt();
        s.nextLine();

        for (int i = 1 ; i <= n ; i++) {
            String chStr = s.nextLine();

            String res = "";
            for (int j = 0 ; j < chStr.length() ; j++) {
                if (!res.isEmpty() &&
                    res.charAt(res.length() - 1) == map.get(chStr.charAt(j)).charAt(0))
                        res += " ";

                res += map.get(chStr.charAt(j));
            }

            System.out.println("Case #" + i + ": " + res);
        }
    }
}

/* Pseudocode - A better approach
1. Take in number of cases, N
2. For (i, i < N, i++)
	    1. Take in input
	    2. For (j, j < length of input, j++)
 	   1. Take character and put it into hash map as key
		   2. Append the value to the result string
	    3. Print result string

Offset Trick:
Consider an array of the following: arr = ["2", "22", "222", "3", "33"...]
Given a character, say, ch = 'a', i can do the magical block: arr[ch - 'a'] to obtain "2".
*/
