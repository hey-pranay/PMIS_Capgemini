public class GreaterOfTwoNumber {
    public static int greaterNumber(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static void main(String[] args) {
        System.out.println(greaterNumber(10, 20));
    }

}
