package generics_classes;

import java.util.*;

public class WildcardExample {

    /*
     * WILDCARDS IN JAVA GENERICS
     *
     * ? means an unknown type.
     *
     * Three types:
     *
     * 1. <?> Any unknown type
     * 2. <? extends T> T or its subclasses
     * 3. <? super T> T or its superclasses
     *
     * PECS:
     * Producer Extends, Consumer Super
     */

    // 1. UNBOUNDED WILDCARD
    // Accepts lists of any element type.
    public static void display(List<?> list) {

        for (Object item : list) {
            System.out.println(item);
        }
    }

    // 2. UPPER-BOUNDED WILDCARD
    // Accepts Number or its subclasses.
    // Mainly used for reading.
    public static double sum(List<? extends Number> list) {

        double total = 0;

        for (Number num : list) {
            total += num.doubleValue();
        }

        return total;
    }

    // 3. LOWER-BOUNDED WILDCARD
    // Accepts Integer or its superclasses.
    // Mainly used for adding.
    public static void addValues(List<? super Integer> list) {

        list.add(10);
        list.add(20);
        list.add(30);
    }

    public static void main(String[] args) {

        // Unbounded wildcard
        List<String> names = Arrays.asList("Java", "Python");

        display(names);

        // Upper-bounded wildcard
        List<Integer> numbers = Arrays.asList(10, 20, 30);

        System.out.println("Sum: " + sum(numbers));

        // Lower-bounded wildcard
        List<Number> values = new ArrayList<>();

        addValues(values);

        System.out.println("Values: " + values);
    }
}