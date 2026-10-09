/*
        Problem Statement
        A number is called a Special Number if the sum of the factorials of its
        digits is equal to the original number.
        For example, the number 145 is a Special Number because:
        • 24
        • 5! = 120
        • Sum + 120 -145
        Your task is to determine whether a given positive integer is a Special
        Number.
        Input Format
        A single positive integer N.
        Constraints
        Output Format
        Print Special Number if the given number satisfies the condition.
        Otherwise, print Not a Special Number.
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
        1! +2! +3! = 1+2+6=9
        Since 9 is not equal to 123, the number is not a Special Number.
*/
package hackerrank_ques;

import java.io.IOException;
import java.util.Scanner;

class Employee {
    String name;
    int basicSalary;

    Employee(String name, int basicSalary) {
        this.name = name;
        this.basicSalary = basicSalary;
    }

    double calculateSalary() {
        return basicSalary + basicSalary * 0.10;
    }

}

class Manager extends Employee {
    Manager(String name, int basicSalary) {
        super(name, basicSalary);
    }

    @Override
    double calculateSalary() {
        return basicSalary + basicSalary * 0.20;
    }
}

class Result {

    /*
     * Complete the 'calculateSalary' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER basicSalary
     * 2. INTEGER allowance
     * 3. INTEGER deduction
     */

    // public static int calculateSalary(int basicSalary, int allowance, int
    // deduction) {
    // // Write your code here

    // }

}

public class first {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String empName = sc.nextLine();
        int empSalary = Integer.parseInt(sc.nextLine());

        String mgrName = sc.nextLine();
        int mgrSalary = Integer.parseInt(sc.nextLine());

        Employee emp = new Employee(empName, empSalary);
        Employee mgr = new Manager(mgrName, mgrSalary);

        System.out.printf("Employee: %s, Total Salary: %.2f%n",
                emp.name, emp.calculateSalary());

        System.out.printf("Manager: %s, Total Salary: %.2f%n",
                mgr.name, mgr.calculateSalary());

        sc.close();

    }
}
