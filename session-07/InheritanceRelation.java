class A {
    void text() {
        System.out.println("A running...");
    }
}

class B {
    private A a = new A(); // it creates the class reference

    void bPrint() {
        a.text();
        System.out.println("B running...");
    }
}

class Address {
    String city;
    String state;
    int pincode;

    Address(String city,
            String state,
            int pincode) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    void displayAddress() {
        System.out.println(city + " " + state + " " + pincode);
    }
}

/*
 * Aggregation: I have something, but it can live without me.
 * Composition: I own something that's part of my lifecycle.
 * Tight coupling: I'm strongly dependent on one specific implementation.
 * Loose coupling: I can work with different implementations without changing my
 * code.
 */

class Student {
    String name;
    int rollNo;
    Address address; // has-a relationship

    Student(String name, int rollNo, Address address) {
        this.name = name;
        this.rollNo = rollNo;
        this.address = address;
    }

    void studenProfile() {

        System.out.println(name + " " + rollNo);
        address.displayAddress();
    }

}

class Battery {
    int capacity;

    Battery(int capacity) {
        this.capacity = capacity;
    }

    void showChargedCapacity() {
        System.out.println("Batterey : " + capacity);
    }
}

class Camera {
    int pix;

    Camera(int pix) {
        this.pix = pix;
    }

    void showPixelsCount() {
        System.out.println("Pixels : " + pix);
    }
}

class Phone {
    String name;
    Battery battery;
    Camera camera;

    Phone(String name, Battery battery, Camera camera) {
        this.name = name;
        this.battery = battery;
        this.camera = camera;
    }

    void phoneDetails() {
        System.out.println("Phone name : " + name);
        battery.showChargedCapacity();
        camera.showPixelsCount();
    }
}

class InheritanceRelation {
    public static void main(String[] args) {
        System.out.println("HI");
        // B obj = new B();
        // obj.bPrint();

        // Student s = new Student("Pranay", 10, new Address("Mumbai", "maharashtra",
        // 6352));
        // s.studenProfile();

        Battery b = new Battery(80);
        Camera c = new Camera(32);
        Phone p = new Phone("Moto", b, c);
        p.phoneDetails();

    }
}