/*

problem 2: find the largest and smallest element 

problem statement given an array of integers, traverse through the array to find and print the maximum and minimum values present in it. 

*/

package array;

public class second {
    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4, 5 };

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i : arr) {
            if (i > max) {
                max = i;
            }

            if (i < min) {
                min = i;
            }
        }

        System.out.println("max : " + max);
        System.out.println("min : " + min);

    }
}
