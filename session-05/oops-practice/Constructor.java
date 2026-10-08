import java.util.ArrayList;

class Car {
    String brand;
    String color;
    int speed;

    Car(String brand, String color, int speed) {
        this.brand = brand;
        this.color = color;
        this.speed = speed;
    }

    void display() {
        System.out.println("Brand : " + brand + " |" + " color : " + color + " |" + " speed " + speed);
    }

    void accelearate(int inc) {
        int originalSpeed = speed;
        inc += speed;

        System.out.println("Original speed of car : " + originalSpeed);
        System.out.println("Accelerated speed : " + inc);

    }
}

// Bank Management

class Bank {
    String customerName;
    double currentBalance = 0;
    ArrayList<String> history = new ArrayList<>();

    Bank(String customerName, double currentBalance) {
        this.customerName = customerName;
        this.currentBalance = currentBalance;
    }

    void customerDetails() {
        System.out.println("Customer Name : " + customerName);
        System.out.println("Current Balance : " + currentBalance + "\n");
    }

    void addBalance(int amount) {
        currentBalance = currentBalance + amount;
        System.out.println("Deposited amount : " + amount);
        System.out.println("Balance after deposit : " + currentBalance);
        history.add("Deposited : " + amount);
        System.out.println();
    }

    void withdrawBalance(int amount) {
        System.out.println("Withdraw amount : " + amount);

        if (currentBalance < amount) {
            System.out.println("Insufficient balnace to withdraw.");
            System.out.println();
            return;
        }

        currentBalance = currentBalance - amount;

        System.out.println("Remaining Balance : " + currentBalance);

        System.out.println("Balance : " + currentBalance);
        if (currentBalance == 0) {
            System.out.println("Maintance proper balance to avoid charges");
        }
        history.add("Withdraw amount : " + amount);

        System.out.println();
    }

    void printTransactions() {
        System.out.println(" --- Transaction history ---- for " + customerName);
        if (history.isEmpty()) {
            System.out.println("No transactions done");
        } else {
            for (String t : history) {
                System.out.println(" - " + t);
            }
        }
        System.out.println("Final Balance : " + currentBalance);
        System.out.println();
    }

}

public class Constructor {
    public static void main(String[] args) {

        /*
         * Car car = new Car("Toyota", "blue", 120);
         * car.display();
         * car.accelearate(10);
         */

        Bank bank = new Bank("Pranay", 0);
        bank.customerDetails();
        bank.addBalance(100);
        bank.withdrawBalance(120);
        bank.addBalance(200);
        bank.withdrawBalance(300);
        bank.addBalance(200);
        bank.printTransactions();
    }
}