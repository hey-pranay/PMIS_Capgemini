import java.util.Scanner;

public class AvgOfThreeNo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter three number for to calculate the average : ");

        System.out.println("First Number : ");
        int a = sc.nextInt();

        System.out.println("Second Number : ");
        int b = sc.nextInt();

        System.out.println("Third Number : ");
        int c = sc.nextInt();

        int ans = calAvg(a, b, c);

        System.out.println("Avg : " + ans);

        sc.close();
    }

    public static int calAvg(int a, int b, int c) {
        int avg = (a + b + c) / 3;
        return avg;
    }


    


}