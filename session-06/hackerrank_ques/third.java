
/*
Problem Statement
You are given an array of integers. Your task is to find the second-largest distinct element in the array.
The second-largest element must be strictly smaller than the largest element. Repeated occurrences of
the largest number must not be considered the second-largest element.
If the array does not contain at least two distinct elements, print Not Possible.
Input Format
The first line contains an integer N, representing the number of elements in the array.
The second line contains N space-separated integers.
Constraints
• -10000 each array element 10000
Output Format
Print the second-largest distinct element. If no such element exists, print Not Possible.
Sample Input
6
10 20 5 20 8 15
Sample Output
15
Explanation
The largest element is 20. The next-largest distinct element is 15, so the output is 15.
*/

package hackerrank_ques;

import java.util.Scanner;

public class third {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] < largest && arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("Not Possible");
        } else {
            System.out.println(secondLargest);
        }

        sc.close();
    }
}