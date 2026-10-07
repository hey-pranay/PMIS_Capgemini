import java.util.Scanner;

public class UserChoiceNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int positive = 0;
        int negative = 0;
        int zeros = 0;

        char choice = 'y';

        while (choice == 'y' || choice == 'Y') {

            System.out.println("Enter a number : ");
            int num = sc.nextInt();

            if (num > 0) {
                positive++;
            } else if (num < 0) {
                negative++;
            } else {
                zeros++;
            }

            System.out.println("Do you want to continue? (y/n) : ");
            choice = sc.next().charAt(0);
        }

        System.out.println("positives : " + positive);
        System.out.println("negative : " + negative);
        System.out.println("zeros : " + zeros);

        sc.close();
    }

}
