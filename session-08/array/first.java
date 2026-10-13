/*

sum and average of array elements

problem statement : take n integers as input from the user, store them in an array, and find the sum and average of all the elements

*/

package array;

import java.util.Scanner;

public class first {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of array : ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        int sum = 0;

        for (int i : arr) {
            sum += i;
        }
        System.out.println("sum of array : " + sum);

        double avg = (double) sum / arr.length;

        System.out.println("average of array : " + avg);
        sc.close();

    }
}
