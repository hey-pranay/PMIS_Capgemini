/*

problem 2: check if a string is a palindrome 

problem statement : take a string as input and determine whether it reads the same backwards as forwards using the two-pointer technique. 

*/

package string;

import java.util.Scanner;

public class second {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string : ");
        String str = sc.nextLine();

        int left = 0;
        int right = str.length() - 1;

        boolean isPalindrome = true;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        if (isPalindrome) {
            System.out.println("Given string is palindrome.");
        } else {
            System.out.println("Given string is not palindrome.");
        }
    }

}
