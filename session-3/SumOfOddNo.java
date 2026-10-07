public class SumOfOddNo {
    public static void main(String[] args) {

        SumOddNo(10);
    }

    public static void SumOddNo(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 != 0) {
                sum = sum + i;
            }
        }
        System.out.println(sum);
    }

}
