/*

problem 1: count vowels and consonants 

problem statement : take a string as input from the user and count the total number of vowels and consonants present in it, ignoring spaces and non-alphabet characters. 

*/

package string;

import java.util.Scanner;

public class first {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string : ");

        String str = sc.nextLine();

        str = str.replaceAll("[^a-zA-Z]", "");

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == 'a' |
                    ch == 'e' |
                    ch == 'i' |
                    ch == 'o' |
                    ch == 'u' |
                    ch == 'A' |
                    ch == 'E' |
                    ch == 'I' |
                    ch == 'O' |
                    ch == 'U') {
                vowels++;
            } else {
                consonants++;
            }

        }

        System.out.println("Vouwels count : " + vowels);
        System.out.println("consonants count : " + consonants);

        sc.close();
    }

}
