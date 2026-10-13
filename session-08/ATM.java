public class ATM {

    public static void main(String[] args) {
        int balance = 500;
        int withdraw = 600;

        try {
            if (withdraw > balance) {
                throw new IllegalArgumentException("Insufficient balance.");
            }
            balance -= withdraw;
            System.out.println("Withdraw successful.");
            System.out.println("Remaining balance : " + balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("program completed");
    }
}
