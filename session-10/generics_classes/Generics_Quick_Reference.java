package generics_classes;

import java.util.ArrayList;
import java.util.List;

/**
 * JAVA GENERICS — QUICK REFERENCE
 *
 * Generics let a class, interface, or method work with different reference
 * types.
 * The compiler checks those types, so mistakes are caught early.
 * Common type names: T = Type, E = Element, K = Key, V = Value.
 *
 * Benefits:
 * 1. Type safety at compile time.
 * 2. Reusable code for different types.
 * 3. No need for explicit casts when reading typed values.
 * 4. Clearer, more readable code.
 *
 */
public class Generics_Quick_Reference {

    /*
     * 1) GENERIC CLASS
     * T is a placeholder for a reference type chosen when creating the object.
     * Inside this class, T can be a field type, constructor parameter, or return
     * type.
     */
    static class Box<T> {
        private final T value;

        Box(T value) {
            this.value = value;
        }

        T getValue() {
            return value;
        }
    }

    /*
     * 2) MULTIPLE TYPE PARAMETERS
     * T and U can represent different types in the same object.
     */
    static class Pair<T, U> {
        private final T first;
        private final U second;

        Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        void print() {
            System.out.println(first + " | " + second);
        }
    }

    /*
     * 3) GENERIC METHOD
     * <T> before 'void' declares the method's own type parameter.
     * The compiler usually infers T from the argument.
     */
    static <T> void display(T element) {
        System.out.println(element.getClass().getName() + " = " + element);
    }

    /*
     * 4) STATIC MEMBERS IN GENERIC CLASSES
     * There is one shared static field for the class, not one per type argument.
     * A static field cannot use the class's T (for example, 'static T item' is
     * invalid).
     */
    static class Counter<T> {
        static int count = 0;

        Counter() {
            count++;
        }
    }



    public static void main(String[] args) {
        System.out.println("1. Generic class:");
        Box<Integer> integerBox = new Box<>(15);
        Box<String> stringBox = new Box<>("GeeksForGeeks");
        System.out.println(integerBox.getValue()); // 15
        System.out.println(stringBox.getValue()); // GeeksForGeeks

        /*
         * Generic type arguments must be reference types, not primitives.
         * Box<int> invalid = new Box<>(20); // COMPILE ERROR
         * Box<Integer> valid = new Box<>(20); // Wrapper class works
         * int[] IS a reference type, so Box<int[]> is valid.
         */
        Box<int[]> arrayBox = new Box<>(new int[] { 1, 2, 3 });
        System.out.println("Primitive array length: " + arrayBox.getValue().length);

        System.out.println("\n2. Multiple type parameters:");
        Pair<String, Integer> pair = new Pair<>("GfG", 15);
        pair.print(); // GfG | 15

        System.out.println("\n3. Generic method:");
        display(11); // Integer (autoboxed from int)
        display("GeeksForGeeks"); // String
        display(1.0); // Double (autoboxed from double)

        /*
         * 5) DIAMOND OPERATOR <>
         * Java 7+ lets the compiler infer the right-hand type argument.
         * Both declarations below mean the same thing.
         */
        List<String> names = new ArrayList<>();
        // List<String> names2 = new ArrayList<String>();

        /*
         * 6) TYPE SAFETY + NO EXPLICIT CASTING
         * Before generics, collections commonly stored Object values.
         * Reading required a cast, which could fail at runtime.
         * With List<String>, get() is typed as String.
         */
        names.add("Sweta");
        names.add("Gudly");
        // names.add(10); // COMPILE ERROR: Integer is not String
        String firstName = names.get(0); // No (String) cast needed
        System.out.println("\n4. Typed list: " + firstName);

        /*
         * 7) TYPE ARGUMENTS ARE CHECKED AT COMPILE TIME
         * Box<Integer> and Box<String> are incompatible types.
         * integerBox = stringBox; // COMPILE ERROR
         *
         * TYPE ERASURE: Java erases most generic type information when compiling.
         * Unbounded T is erased to Object; bounded T is erased to its bound.
         * Thus both Box<Integer> and Box<String> use the same runtime Box class.
         * This does NOT make them assignment-compatible in Java source code.
         */
        System.out.println("\n5. Same runtime class: "
                + (integerBox.getClass() == stringBox.getClass())); // true

        System.out.println("\n6. Shared static variable:");
        new Counter<Integer>();
        new Counter<String>();
        new Counter<Double>();
        System.out.println(Counter.count); // 3

        /*
         * QUICK RECAP
         * Box<T> -> generic class
         * Pair<T, U> -> multiple type parameters
         * static <T> void f(T x) -> generic method
         * new Box<>(...) -> diamond operator (type inference)
         * List<String> -> only String elements accepted
         * Box<int> -> invalid (primitive)
         * Box<Integer> -> valid (wrapper)
         * Box<int[]> -> valid (array is a reference type)
         * Type erasure -> generic arguments mostly absent at runtime
         * static field -> shared across Box<Integer>, Box<String>, etc.
         */
    }
}
