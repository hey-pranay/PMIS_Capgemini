class Animal {
    void eat() {
        System.out.println("eats...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("barks...");
    }
}

public class Single {

    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat();
        myDog.bark();
    }
}
