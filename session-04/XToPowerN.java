import java.util.Scanner;

public class XToPowerN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter x : ");
        int x = sc.nextInt();

        System.out.print("Enter n : ");
        int n = sc.nextInt();

        int result = 1;
        int i = 1;

        while (i <= n) {
            result = result * x;
            i++;
        }

        System.out.println(x + "^" + n + " = " + result);
        sc.close();

    }
}
