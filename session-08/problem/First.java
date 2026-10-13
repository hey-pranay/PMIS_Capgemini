package problem;

import java.util.Scanner;

/*

Write a program that takes two integer inputs from the user and calculates their division.If the second number is zero,handle the resulting ArithmeticException gracefully and print an error message instead of crashing.

*/

public class First {

    static int division(int a, int b) {
        return a / b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a : ");
        int a = sc.nextInt();

        System.out.println("Enter b : ");
        int b = sc.nextInt();

        try {
            if (b == 0) {
                throw new ArithmeticException("b cannot be zero.");
            }

            System.out.println("Result: " + division(a, b));

        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }

}
