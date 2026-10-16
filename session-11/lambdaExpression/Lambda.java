/*
=========================================================
              LAMBDA EXPRESSIONS IN JAVA
                  (Java 8 Feature)
=========================================================

1. WHAT IS LAMBDA?
---------------------------------------------------------
- Lambda is a short way to implement a functional interface.
- Introduced in Java 8.
- Reduces boilerplate code.
- Used in Collections, Stream API, Sorting, Threads.

SYNTAX:
(parameters) -> expression

OR

(parameters) -> {
     statements
};

EXAMPLES:
() -> System.out.println("Hello");
x -> x * x;
(a, b) -> a + b;
(a, b) -> { return a + b; };


2. FUNCTIONAL INTERFACE
---------------------------------------------------------
- Interface containing exactly ONE abstract method.
- Can contain multiple default and static methods.
- @FunctionalInterface annotation is optional.
- Lambda requires a functional interface as target type.

EXAMPLE:

@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

Calculator c = (a, b) -> a + b;
System.out.println(c.add(10, 20)); // 30


3. TYPES OF LAMBDA
---------------------------------------------------------
! No parameter:
() -> System.out.println("Hello");

! One parameter:
x -> x * 2;

!Two parameters:
(a, b) -> a + b;

! Multiple statements:
(a, b) -> {
    int sum = a + b;
    return sum;
};


4. BUILT-IN FUNCTIONAL INTERFACES
---------------------------------------------------------
Package: java.util.function

INTERFACE          METHOD         PURPOSE
---------------------------------------------------------
Predicate<T>       test(T)        returns boolean
Function<T,R>      apply(T)       input -> output
Consumer<T>        accept(T)      input, no return
Supplier<T>        get()          returns value
BiFunction<T,U,R>  apply(T,U)     two inputs -> output

EXAMPLES:

* Predicate - check even
Predicate<Integer> p = n -> n % 2 == 0;
p.test(10); // true

* Function - square
Function<Integer, Integer> f = n -> n * n;
f.apply(5); // 25

* Consumer - print
Consumer<String> c = s -> System.out.println(s);
c.accept("Java");

* Supplier - supply value
Supplier<Integer> s = () -> 100;
s.get(); // 100

* BiFunction - add
BiFunction<Integer, Integer, Integer> add =
    (a, b) -> a + b;
add.apply(10, 20); // 30


5. LAMBDA WITH COLLECTIONS
---------------------------------------------------------
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

* Print all elements
nums.forEach(n -> System.out.println(n));

* Method reference (shorter form)
nums.forEach(System.out::println);


6. LAMBDA WITH STREAM API
---------------------------------------------------------
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6);

* Print even numbers
nums.stream()
    .filter(n -> n % 2 == 0)
    .forEach(System.out::println);

* Square every number
nums.stream()
    .map(n -> n * n)
    .forEach(System.out::println);

* Square only even numbers
nums.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .forEach(System.out::println);

Output: 4 16 36


7. LAMBDA WITH SORTING
---------------------------------------------------------
List<Integer> list = new ArrayList<>(
    Arrays.asList(5, 2, 8, 1)
);

* Ascending
list.sort((a, b) -> Integer.compare(a, b));

* Descending
list.sort((a, b) -> Integer.compare(b, a));

! Comparator is a functional interface.


8. LAMBDA WITH MULTITHREADING
---------------------------------------------------------
! Runnable is a functional interface.

Runnable task = () -> {
    System.out.println("Thread running");
};

Thread t = new Thread(task);
t.start();

* Short form:
new Thread(() ->
    System.out.println("Hello Thread")
).start();


9. LAMBDA VS ANONYMOUS CLASS
---------------------------------------------------------
ANONYMOUS CLASS:
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};

LAMBDA:
Runnable r = () -> System.out.println("Hello");

- Lambda is shorter and more readable.
- Lambda does not introduce its own 'this'.
- Anonymous class has its own 'this' reference.


10. IMPORTANT RULES
---------------------------------------------------------
1. Lambda works with functional interfaces.
2. Parameter types can usually be inferred.
3. Parentheses are optional for one inferred parameter.
4. Braces are optional for a single expression.
5. Return keyword is optional for expression bodies.
6. Captured local variables must be final or
   effectively final.
7. Lambda can throw exceptions, subject to the
   functional interface method's throws declaration.
8. Lambda does not automatically create a thread.
9. Method reference (::) can replace some lambdas.
10. Lambda expressions can be passed as arguments.


11. INTERVIEW QUESTIONS
---------------------------------------------------------
Q1. What is Lambda?
Ans: A concise implementation of a functional interface.

Q2. What is a functional interface?
Ans: An interface with exactly one abstract method.

Q3. Can functional interfaces have default methods?
Ans: Yes, multiple default and static methods.

Q4. Can lambda return a value?
Ans: Yes.

Q5. Can lambda access local variables?
Ans: Yes, if final or effectively final.

Q6. Is Runnable a functional interface?
Ans: Yes, it has one abstract method: run().

Q7. What is the difference between lambda and stream?
Ans:
Lambda -> Defines behavior/function.
Stream -> Processes sequences of elements.

Q8. What is a method reference?
Ans: A shorter way to refer to an existing method.

Example:
n -> System.out.println(n)
System.out::println

Q9. Can lambda implement an abstract class?
Ans: No, lambda targets a functional interface.

Q10. Why use lambda?
Ans:
- Less boilerplate
- Better readability
- Functional-style programming
- Easy collection and stream processing


=========================================================
QUICK REVISION
=========================================================

Lambda       -> (a, b) -> a + b
Functional   -> Exactly one abstract method
Predicate    -> test()   -> boolean
Function     -> apply()  -> result
Consumer     -> accept() -> void
Supplier     -> get()    -> value
BiFunction   -> apply()  -> result
filter()     -> Select elements
map()        -> Transform elements
forEach()    -> Perform action
sorted()     -> Sort elements
::           -> Method reference

=========================================================
*/

package lambdaExpression;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Greeter {
    void greet(String name);
}

public class Lambda {
    public static void main(String[] args) {
        Greeter g = (name) -> System.out.println("Hello , " + name);
        g.greet("Pranay");

        System.out.println("---Built in functional interface---");

        /* Predicate */
        Predicate<Integer> isAdult = age -> age >= 18;
        System.out.println(isAdult.test(25));
        System.out.println(isAdult.test(5));

        /* Function */
        Function<String, Integer> getLength = name -> name.length();

        System.out.println(getLength.apply("pranay"));

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        // square
        Function<Integer, Integer> square = n -> n * n;

        System.out.println("Square  of " + a + " is " + square.apply(a));

        // cube
        Function<Integer, Integer> cube = n -> n * n * n;

        System.out.println("Cube  of " + a + " is " + cube.apply(a));

        /* Consumer */
        Consumer<String> str = message -> System.out.println(message);

        str.accept("Hi my name is Pranay");

        /* Supplier */
        Supplier<String> message = () -> "Welcome";
        System.out.println(message.get());

    }

}
