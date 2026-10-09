
/*Problem Statement
A number is called a Special Number if the sum of the factorials of its digits is equal to the original
number.
For example, the number 145 is a Special Number because:
• 11=1
• 41=24
• 5! = 120
• Sum 1+24 + 120 = 145
Your task is to determine whether a given positive integer is a Special Number.
Input Format
A single positive integer N.
Constraints
1 N 1,000,000
Output Format
Print Special Number if the given number satisfies the condition. Otherwise, print Not a Special
Number.
Sample Input O
145
Sample Output O
Special Number
Sample Input 1
123
Sample Output 1
Not a Special Number
Explanation
For 123, the sum of the factorials of its digits is:
1! + 2! + 3! +2+6=9
Since 9 is not equal to 123, the number is not a Special Number. */

package hackerrank_ques;

import java.util.Scanner;

public class second {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int original = n;
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;

            int fact = 1;
            for (int i = 1; i <= digit; i++) {
                fact = fact * i;
            }

            sum = sum + fact;
            n = n / 10;
        }

        if (sum == original) {
            System.out.println("Special Number");
        } else {
            System.out.println("Not a Special Number");
        }

        sc.close();
    }
}