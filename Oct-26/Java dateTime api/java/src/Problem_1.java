/*

Create a Java program called DateExplorer that asks the user to enter a date in the format yyyy-MM-dd.
The program should then display a menu that allows the user to explore different details about that date.

For example, the user enters 2026-10-26 and gets the following menu:

Requirements
1. Ask the user to input a date using Scanner.
2. Use LocalDate.parse() to convert the input into a LocalDate object.
3. Use a switch statement to handle the menu options.
4. Use minusMonths() to look back by a number of months specified by the user.
5. Use getMonth(), getYear(), and getDayOfWeek() to display date information.
6. Use an if-else statement to check whether the entered year is a leap year.
7. Use a loop to keep displaying the menu until the user chooses Exit.
8. Create a DateExplorer class with methods such as displayMonth(), displayYear(), and lookBackMonths().

*/

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class Problem_1 {


    private final Scanner sc = new Scanner(System.in);
    private LocalDate date;


    public void inputDate() {
        while (true) {
            try {
                System.out.print("Enter a date (yyyy-MM-dd): ");
                String input = sc.nextLine();

                date = LocalDate.parse(input);

                System.out.println("Date entered successfully: " + date);
                break;

            } catch (java.time.format.DateTimeParseException e) {
                System.out.println(
                        "Invalid date format. Please enter the date in yyyy-MM-dd format."
                );
            }
        }
    }


    public void displayMonth() {
        Month month = date.getMonth();
        System.out.println("Month : " + month);
        System.out.println("Month number : " + date.getMonthValue());
    }

    public void displayYear() {
        System.out.println("Year : " + date.getYear());

        if (date.isLeapYear()) {
            System.out.println("Leap year : yes ");
        } else {
            System.out.println("Leap year : no ");
        }
    }

    public void displayDayOfWeek() {
        DayOfWeek day = date.getDayOfWeek();
        System.out.println("Day of the week : " + day);
    }

    public void lookBackMonths() {
        System.out.println("Enter the number of months to look back : ");
        int months = Integer.parseInt(sc.nextLine());

        LocalDate pastDate = date.minusMonths(months);

        System.out.println("Original date : " + date);
        System.out.println("Date " + months + " months ago " + pastDate);
    }

    public void displayMenu() {
        int choice;

        do {
            System.out.println("\n===== Date Explorer =====");
            System.out.println("1. Display Month");
            System.out.println("2. Display Year and Leap Year");
            System.out.println("3. Display Day of Week");
            System.out.println("4. Look Back by Months");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    displayMonth();
                    break;

                case 2:
                    displayYear();
                    break;

                case 3:
                    displayDayOfWeek();
                    break;

                case 4:
                    lookBackMonths();
                    break;

                case 5:
                    System.out.println("Exiting Date Explorer. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    public static void main(String[] args) {
        Problem_1 explorer = new Problem_1();

        explorer.inputDate();
        explorer.displayMenu();

        explorer.sc.close();

    }

}