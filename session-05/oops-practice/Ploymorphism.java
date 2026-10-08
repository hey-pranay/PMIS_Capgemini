// compile time
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(double a, double b) {
        return a + b;
    }
}

class Animal {
    void makeSound() {
        System.out.println("animal make sound...");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog make sound...");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat make sound...");
    }
}

public class Ploymorphism {

    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal animal2 = new Dog();
        Animal animal3 = new Cat();

        animal.makeSound();
        animal2.makeSound();
        animal3.makeSound();

        Calculator cal = new Calculator();
        System.out.println(cal.add(5, 2));
        System.out.println(cal.add(1, 1, 1));
        System.out.println(cal.add(1.0, 5.0));

    }

}
