import java.util.Scanner;

class Main {
    /*
     * EXCEPTION HANDLING IN JAVA
     *
     * Exception handling is a mechanism used to handle runtime errors
     * so that the normal flow of the program can be maintained.
     *
     * TYPES OF EXCEPTIONS:
     *
     * 1. Checked Exceptions:
     * - Checked by the compiler at compile time.
     * - Must be handled using try-catch or declared using throws.
     * - Examples: IOException, SQLException, FileNotFoundException.
     *
     * 2. Unchecked Exceptions:
     * - Not checked by the compiler at compile time.
     * - Usually occur during runtime due to programming mistakes.
     * - Examples: ArithmeticException, NullPointerException,
     * ArrayIndexOutOfBoundsException, NumberFormatException.
     *
     * KEYWORDS USED IN EXCEPTION HANDLING:
     *
     * 1. try - Contains code that may cause an exception.
     * 2. catch - Handles the exception.
     * 3. finally - Executes cleanup code regardless of whether
     * an exception occurs (except in certain cases).
     * 4. throw - Used to explicitly throw an exception.
     * 5. throws - Declares exceptions that a method may propagate.
     *
     * ERRORS:
     * - Serious problems that applications generally cannot recover from.
     * - Examples: OutOfMemoryError, StackOverflowError.
     *
     * DIFFERENCE BETWEEN throw AND throws:
     *
     * throw - Used inside a method to explicitly throw an exception.
     * throws - Used in a method declaration to declare exceptions.
     *
     * EXCEPTION HIERARCHY:
     *
     * Throwable
     * |
     * |--- Exception
     * | |--- Checked Exceptions
     * | |--- RuntimeException (Unchecked Exceptions)
     * |
     * |--- Error
     *
     * BENEFITS:
     * - Prevents abrupt termination when exceptions are handled.
     * - Maintains normal program flow where recovery is possible.
     * - Separates error-handling code from normal code.
     * - Helps identify and handle unexpected situations.
     */

    static void checkAge(int age) throws Exception {
        if (age < 18) {
            throw new Exception("You are minor.");
        }

        System.out.println("You are eligible.");
    }

    public static void main(String[] args) {
        System.out.println("---- Exception handling ----");

        int a = 5;
        int b = 0;

        try {
            int result = a / b;
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error occured : " + e);
        } finally {
            System.out.print("Finally block : ");
            System.out.print("any way runs this block");
        }

        System.out.println();

        System.out.println("---- Throw ----");

        /*
         * THROWS KEYWORD IN JAVA
         *
         * The throws keyword is used in a method declaration
         * to specify exceptions that a method may throw.
         *
         * Syntax:
         * returnType methodName() throws ExceptionType {
         * // code
         * }
         *
         * 1. It is mainly used for checked exceptions.
         *
         * 2. It informs the caller that the method may
         * throw the specified exception.
         *
         * 3. The calling method must handle checked exceptions
         * using try-catch or declare them using throws.
         *
         * 4. Multiple exceptions can be declared using commas.
         *
         * Example:
         * void read() throws IOException, SQLException {
         * // code
         * }
         *
         * DIFFERENCE:
         *
         * throw - Used to explicitly throw an exception.
         * throws - Used to declare possible exceptions.
         */

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Age : ");
        int age = sc.nextInt();

        try {
            checkAge(age);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();

    }

}