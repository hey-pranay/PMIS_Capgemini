/*
 * static → Shared by the class.
 * final → You cannot change or extend certain things.
 * abstract → You must complete the missing implementation in a concrete
 * subclass.
 */
class Student {
    String name;
    static String clgName = " VIT";

    Student(String name) {
        this.name = name;
    }

    void collgeName() {
        System.out.println(name + clgName);
    }
}

class Bank {
    final void interest() {
        System.out.println("Basic interese");
    }
}

class Branch extends Bank {
    // void interest() {
    // System.out.println("branch interest");
    // }
}

abstract class Vechile {
    abstract void start();

    void stop() {
        System.out.println("Vehicle stop...");
    }
}

class Car extends Vechile {
    @Override
    void start() {
        System.out.println("car started...");
    }
}

class Bike extends Vechile {
    @Override
    void start() {
        System.out.println("Bike started...");
    }
}

public class main {

    public static void main(String[] args) {
        // Student s = new Student("Pranay");
        // s.collgeName();

        // Branch b = new Branch();
        // b.interest();

        Vechile c = new Car();
        Vechile b = new Bike();

        c.start();
        b.start();
        c.stop();
        b.stop();

    }
}
