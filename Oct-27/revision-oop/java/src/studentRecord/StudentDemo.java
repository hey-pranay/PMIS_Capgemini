package studentRecord;

public class StudentDemo {
    public static void main(String[] args) {
        Student s1 = new Student("Asha", 1, 80, 90, 70);
        Student s2 = new Student("Ravi", 2, 55, 40, 62);

        s1.display();
        s2.display();
    }
}