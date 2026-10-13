package interface_and_wrappers;

/*

    Problem 1: Parse and sum a comma-separated string Task: Given a string of
    numbers separated by commas, extract each number and calculate the total sum.
    Key concept: Integer.parseInt() or Integer.valueOf().
  
 */

public class first {
    public static void main(String[] args) {

        String input = "20,10,50,60";
        String parts[] = input.split(",");

        int total = 0;

        for (String part : parts) {
            total += Integer.parseInt(part);
        }

        System.out.println(total);
    }

}
