package superPackage;

public class Main {
    public static void main(String[] args) {
        Manager mg = new Manager();
        mg.display();

        cow c = new cow();
        c.eat();

        SavingAc sa = new SavingAc("Pranay...");
        sa.displayDetails();

    }
}
