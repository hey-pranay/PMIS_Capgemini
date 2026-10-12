/**
 * 
 * Problem Statement
Create a Java class named Car to manage a vehicleS speed.
The class must contain the following fields:
— a String representing the car's model.
• model
• speed — an integer representing its current speed.
Implement the following methods:
1. Constructor: Initialize the car's model and starting speed.
2. start(): Print {model} has started.
3. accelerate(int increment): Increase the current speed by the given increment and print the
updated speed.
4. brake(int decrement): Decrease the current speed by the given decrement and print the
updated speed.
In the main program, create a car using the provided input, start it, accelerate it, and then apply the
brakes.
Input Format
Four lines containing:
1. Car model (a single word)
2. Initial speed
3. Acceleration increment
4. Braking decrement
Constraints
•
•
•
O initial speed 300
1 increment 100
1 decrement 100
The braking decrement will not exceed the speed after acceleration.
 */

package problems;

import java.util.Scanner;

class Car {
    String model;
    int speed;

    Car(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    void start() {
        System.out.println(model + " has started.");
    }

    void accelerate(int increment) {
        speed += increment;
        System.out.println("Speed after acceleration: " + speed);
    }

    void brake(int decrement) {
        speed -= decrement;
        System.out.println("Speed after braking: " + speed);
    }
}

public class second {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String model = sc.next();
        int speed = sc.nextInt();
        int increment = sc.nextInt();
        int decrement = sc.nextInt();

        Car car = new Car(model, speed);

        car.start();
        car.accelerate(increment);
        car.brake(decrement);

        sc.close();
    }
}
