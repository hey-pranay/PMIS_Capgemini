package interface_and_wrappers;

import java.util.ArrayList;

/*

    Problem 3: Filter odd numbers from an ArrayList Task: Create an ArrayList<Integer> with mixed   numbers, remove all odd numbers, and print the remaining list. Key concept: Autoboxing, unboxing, and   collections requiring wrapper types instead of primitives. Key concept: Integer cache between -128 and  127. 

*/
public class third {
    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>();

        input.add(5);
        input.add(15);
        input.add(20);
        input.add(30);
        input.add(45);

        ArrayList<Integer> output = new ArrayList<>();

        for (Integer num : input) {
            if (num % 2 == 0) {
                output.add(num);

            }
        }

        System.out.println("remaining list :" + output);
    }

}
