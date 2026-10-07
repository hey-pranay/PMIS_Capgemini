import java.util.Scanner;

public class demo {

    // square pattern
    // public static void main(String[] args) {
    // for (int i = 0; i < 4; i++) {
    // System.out.print(" * ");
    // for (int j = 0; j < 4; j++) {
    // System.out.print(" * ");
    // }
    // System.out.println();
    // }
    // }

    // triangle pattern
    // public static void main(String[] args) {
    // for (int col = 0; col < 4; col++) {
    // System.out.print(" * ");
    // for (int row = 0; row < col; row++) {
    // System.out.print(" * ");
    // }
    // System.out.println();
    // }
    // }

    // hallow square
    // public static void main(String[] args) {

    // int row = 5;
    // int col = 10;

    // // remember the boundary condition with the loop starts
    // // if i = 0, j = 0 then row-1 , col-1
    // // if i = 1, j = 1 then row , col
    // for (int i = 1; i <= row; i++) {
    // for (int j = 1; j <= col; j++) {
    // if (i == 1 || i == row || j == 1 || j == col) {
    // System.out.print("* ");
    // } else {
    // System.out.print(" "); // pay attention to the space
    // }
    // }
    // System.out.println();
    // }
    // }

    // inverted triangle pattern
    // public static void main(String[] args) {
    // int rows = 5;

    // for (int i = 0; i < rows; i++) {
    // for (int j = rows; j > i; j--) {
    // System.out.print(" * ");
    // }
    // System.out.println();
    // }
    // }

    // left triangle pattern
    // public static void main(String[] args) {
    // int rows = 10;

    // for (int i = 1; i <= rows; i++) {
    // // leading spaces
    // for (int j = 1; j <= rows - i; j++) {
    // System.out.print(" ");
    // }

    // for (int k = 1; k <= i; k++) {
    // System.out.print(" *");
    // }
    // System.out.println();
    // }

    // }

    // // number triangle
    // public static void main(String[] args) {
    // for (int i = 1; i <= 5; i++) {
    // for (int j = 1; j < i; j++) {
    // System.out.print(j + " ");
    // }

    // System.out.print(i + " ");
    // System.out.println();
    // }
    // }

    // // inverted number triangle
    // public static void main(String[] args) {
    // int rows = 5;
    // for (int i = 1; i <= 5; i++) {
    // for (int j = rows; j > i; j--) {
    // System.out.print(j + " ");
    // }
    // System.out.print(i + " ");
    // System.out.println();
    // }
    // }

    // continue number triangle;
    // public static void main(String[] args) {

    // int number = 1;
    // for (int i = 1; i <= 4; i++) {
    // for (int j = 1; j <= i; j++) {
    // System.out.print(number + " ");
    // number++;
    // }

    // System.out.println();
    // }
    // }

    // o 1 triangle
    // public static void main(String[] args) {
    // for (int i = 1; i <= 5; i++) {
    // for (int j = 1; j <= i; j++) {
    // if ((i + j) % 2 == 0) {
    // System.out.print(" 1 ");
    // } else {
    // System.out.print(" 0 ");
    // }
    // }
    // System.out.println();
    // }
    // }

    // shape area calcualtor
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean keepRunning = true;

        System.out.println("--- welcome to new calci---");
        while (keepRunning) {
            System.out.println("\n Select the shape to calculate its area : ");
            System.out.println("1. Triangle ");
            System.out.println("2. Square ");
            System.out.println("3. Reactangle ");
            System.out.println("4. Exit");
            System.out.println("Enter your choice (1-4) : ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the base of the triangle");
                    double base = sc.nextDouble();

                    System.out.print("Enter the height of the triangle");
                    double height = sc.nextDouble();

                    double triangleArea = 0.5 * base * height;

                    System.out.println(" The area of the triangle is : " + triangleArea);
                    break;

                case 2:
                    System.out.print("Enter the side length of the square: ");

                    double side = sc.nextDouble();

                    double squareArea = side * side;

                    System.out.println("The area of the Square is: " + squareArea);

                    break;

                case 3:
                    System.out.print("Enter the length of the rectangle: ");
                    double length = sc.nextDouble();

                    System.out.print("Enter the width of the rectangle: ");
                    double width = sc.nextDouble();

                    double rectangleArea = length * width;

                    System.out.println("The area of the Rectangle is: " + rectangleArea);
                    break;

                case 4:
                    System.out.println("Thank you for using the Area Calculator. Goodbye!");
                    keepRunning = false;
                    break;

                default:
                    System.out.println("Invalid option! Please pick a number between 1 and 4.");
            }
        }
        sc.close();
    }

}
