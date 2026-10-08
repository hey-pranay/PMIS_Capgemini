import java.util.ArrayList;

class CoffeeWallet {
    private String customerName;
    private int balance;

    private ArrayList<String> history = new ArrayList<>();

    CoffeeWallet(String customerName, int balance) {
        this.customerName = customerName;
        if (balance >= 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Set to ₹0.");
        }
    }

    void displayInfo() {
        System.out.println("Account review : ");
        System.out.println("Customer Name : " + customerName);
        System.out.println("Balance : " + balance);
        System.out.println();
    }

    void deposit(int fund) {
        if (fund <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        System.out.println("deposit : " + fund);
        balance += fund;
        System.out.println("Balance : " + balance);
        history.add("deposit : " + fund);
        System.out.println();
    }

    void purchase(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid purchase amount.");
            return;
        }

        if (amount > balance) {
            System.out.println("Attempted purchase: " + amount);
            System.out.println("Error : We cannot make purchase");
            System.out.println();
            return;
        }

        System.out.println("Purchase amount : " + amount);
        balance -= amount;
        System.out.println("Balance remaining after the purchase : " + balance);
        history.add("Purchase amount : " + amount);
        System.out.println();
    }

    void printStatements() {
        System.out.println("Statements");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String i : history) {
                System.out.println(i);
            }
        }
        System.out.println("Final Balance : " + balance);
        System.out.println();
    }

    public static void main(String[] args) {

        CoffeeWallet cw = new CoffeeWallet("Pranay", 500);
        cw.displayInfo();

        cw.deposit(200);
        cw.purchase(150);
        cw.purchase(850);

        cw.displayInfo();
        cw.printStatements();
    }
}