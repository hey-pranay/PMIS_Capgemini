package superPackage;

class SuperAndThis {
    double salary = 30000;

    void eat() {
        System.out.println("animal eat...");
    }
}

class Manager extends SuperAndThis {
    double salary = 60000;

    void display() {
        System.out.println("Manager salary : " + salary);
        System.out.println("Manager salary : " + super.salary);
    }
}

class cow extends SuperAndThis {
    void eat() {
        super.eat();
        System.out.println("cow eat...");
    }

}

class BankAccount {
    String accoundHolder;

    BankAccount(String accountHolder) {
        this.accoundHolder = accountHolder;
    }

    void displayDetails() {
        System.out.println("Account Holder name : " + accoundHolder);
    }
}

class SavingAc extends BankAccount {
    double interstRate = 5;

    SavingAc(String accoundHolder) {
        super(accoundHolder);
    }

    @Override
    void displayDetails() {
        System.out.println("Interest rate : " + interstRate);
    }
}
