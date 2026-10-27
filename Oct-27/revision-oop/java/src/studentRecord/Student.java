package studentRecord;

public class Student {

    // 1. Private fields
    private String name;
    private int rollNumber;
    private int mark1;
    private int mark2;
    private int mark3;

    // 2. Constructor
    public Student(String name, int rollNumber,
                   int mark1, int mark2, int mark3) {

        this.name = name;
        this.rollNumber = rollNumber;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public int getTotal() {
        return mark1 + mark2 + mark3;
    }

    public double getAverage() {
        return getTotal() / 3.0;
    }

    public String getGrade() {
        double average = getAverage();

        if (average >= 90) {
            return "A+";
        } else if (average >= 75) {
            return "A";
        } else if (average >= 60) {
            return "B";
        } else if (average >= 40) {
            return "C";
        } else {
            return "Fail";
        }
    }

    public void display() {
        System.out.printf(
                java.util.Locale.US,
                "Roll %d | %s | Total: %d | Average: %.2f | Grade: %s%n",
                rollNumber,
                name,
                getTotal(),
                getAverage(),
                getGrade()
        );
    }
}