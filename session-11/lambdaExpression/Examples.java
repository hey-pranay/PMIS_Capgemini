package lambdaExpression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Examples {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 6, 4, 5, 9);

        // stream => source -> chain og intermediate operation -> terminal operation
        List<Integer> result = nums
                .stream()
                .filter(n -> n % 2 == 0) // intermidate operation
                .map(n -> n * n) // intermidate operation
                .sorted() // intermidate operation
                .collect(Collectors.toList()); // terminal operation

        System.out.println("OG List : " + nums);
        System.out.println("result : " + result);

        // Example 2

        List<String> list = List.of("a", "b", "c");
        System.out.println("Stream from list");

        list
                .stream()
                .forEach(System.out::println);

        int[] arr = { 1, 2, 3 };

        Arrays
                .stream(arr)
                .forEach(System.out::println);

        // Example 3

        List<Integer> l = List.of(0, 1, 8, 2, 36, 9, 7, 4, 10);

        List<Integer> res = l.stream()
                .filter(n -> n > 7)
                .collect(Collectors.toList());
        System.out.println(res);

        List<Integer> res2 = l.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.println(res2);
    }

}
