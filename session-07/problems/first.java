/*
Problem Statement
Create a Java program to calculate the areas of a circle and a rectangle using inheritance and
method overriding.
Create a base class named Shape with:
• A field color of type String.
• A method setC010r(String color) to set the shape's color.
• A method calculateArea() to calculate and return the area.
Create two subclasses:
• Circle: stores a radius and calculates its area using (\pi \times r \times r).
• Rectangle: stores its length and width and calculates its area using length x width.
Both subclasses must override the calculateArea() method.
In the main program, create one circle and one rectangle, set their colors, and print their colors and
areas.
Use Math. PI for the value of pi. Display each area rounded to two decimal places.
Input Format
The input consists of four numeric values:
• Radius of the circle
• Length of the rectangle
• Width of the rectangle
• No input is required for the colors; use "Orange" for the circle and "Light Blue" for the
rectangle.
Constraints
• All dimensions must be greater than O.
• Dimensions may contain decimal values.
Output Format
Print the color and calculated area of each shape, with areas rounded to two decimal places.

*/

package problems;

import java.io.IOException;
import java.util.Scanner;

class Shape {
    String color;

    void setColor(String color) {
        this.color = color;
    }

    double calculateArea() {
        return 0.0;
    };

}

class Circle extends Shape {
    double radius;

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Reactangle extends Shape {
    double length;
    double width;

    @Override
    double calculateArea() {
        return length * width;
    }
}

public class first {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        double radius = sc.nextDouble();
        double length = sc.nextDouble();
        double width = sc.nextDouble();

        Circle c = new Circle();
        Reactangle r = new Reactangle();

        c.radius = radius;
        r.length = length;
        r.width = width;

        System.out.println("Circle Color: " + c.color);
        System.out.printf("Circle Area: %.2f%n", c.calculateArea());

        System.out.println("Rectangle Color: " + r.color);
        System.out.printf("Rectangle Area: %.2f%n", r.calculateArea());

        sc.close();

    }
}