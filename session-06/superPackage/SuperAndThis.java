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

class Animal {
    String name;

    void eat() {
        System.out.println("Animal eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void displayDetails() {
        System.out.println("Employee Name : " + name);
        System.out.println("Employee Salary : " + salary);
    }
}

class Manager_2 extends Employee {
    String deparatment;

    Manager_2(String name, double salaray, String department) {
        super(name, salaray);
        this.deparatment = department;
    }

    @Override
    void displayDetails() {
        super.displayDetails();
        System.out.println("Department : " + deparatment);
        System.out.println("Role : Manager ");
    }
}
