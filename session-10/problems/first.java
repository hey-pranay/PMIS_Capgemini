
/*

Level 1: The Locker
Problem Statement
A school has lockers. One locker can hold one item. Students keep different things in them: a
book (String), a locker code (Integer), or a lunch price (Double).
Without generics, you’d write StringLocker, IntegerLocker, DoubleLocker, and so on.
Your task: Write one Locker<T> class that can hold any type of item.
Requirements:
1. put(item) stores an item.
2. get() returns the item.
3. Test it with a String and an Integer.
Expected output:
Locker has: Java Book
Locker has: 1234

*/

package problems;

import java.util.Arrays;
import java.util.List;

class Locker<T> {
    private T items;

    void put(T items) {
        this.items = items;
    }

    T get() {
        return items;
    }
}

// wild card generic

class WildCard {
    public static void printList(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}

public class first {
    public static void main(String[] args) {
        Locker<String> l1 = new Locker<>();
        l1.put("Java Book");
        System.out.println(l1.get());

        Locker<Integer> l2 = new Locker<>();
        l2.put(500);
        l2.put(1000);

        System.out.println(l2.get());

        System.out.println();
   

        System.out.println("-------Wild Card---------");
        System.out.println();
        List<String> names = Arrays.asList("Pranay", "Rahul", "Kundan");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        System.out.println(names);
        System.out.println(numbers);

    }
}
