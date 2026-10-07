public class EligbleVote {

    public static void rightToVote(int age) {
        if (age > 18) {
            System.out.println("Eligible to vote");
        } else {
            System.out.println("Not eligible to vote");
        }
    }

    public static void main(String[] args) {
        rightToVote(23);
    }

}
