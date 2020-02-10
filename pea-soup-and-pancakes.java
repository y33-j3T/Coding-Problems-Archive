import java.util.*;

public class lab1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String res = "";

        // System.out.println("Enter number of restaurants:");
        int num_restaurant = s.nextInt();
        

        for (int i = 0 ; i < num_restaurant ; i++) {
            // System.out.println("Enter number of items:");
            int num_item = s.nextInt();
            s.nextLine();
            String[] item_arr = new String[num_item];

            // System.out.println("Enter restaurant name:");
            String restaurantName = s.nextLine();

            // System.out.println("Enter item names:");
            for (int j = 0 ; j < num_item ; j++) {
                String item = s.nextLine().trim().toLowerCase();
                // System.out.println(item);
                item_arr[j] = item;
            }

            List<String> list = Arrays.asList(item_arr);

            if (list.containsAll(Arrays.asList("pea soup", "pancakes")) && res.equals("")) {
                res = restaurantName;
            }
        }

        if (res.equals("")) {
            System.out.println("Anywhere is fine I guess");
        } else {
            System.out.println(res);
        }

        return;
    }
}


