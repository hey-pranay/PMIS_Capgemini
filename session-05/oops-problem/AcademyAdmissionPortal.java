
class StudentProfile {
    private String fullName;
    private String studentId;
    private double finalExamScore;

    // for case 1 : Exam takers
    public StudentProfile(String fullName, String studentId, double finalExamScore) {
        this.fullName = fullName;
        this.studentId = studentId;

        if (finalExamScore >= 0.0 && finalExamScore <= 100.0) {
            this.finalExamScore = finalExamScore;
        } else {
            this.finalExamScore = 0.0;
            System.out.println("Invalid score provided. Initialized to 0.0.");
        }
    }

    // for case 2 : Direct Walk-in
    public StudentProfile(String fullName, String studentId) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.finalExamScore = 0.0;
    }

    public char calculateLetterGrade() {
        if (finalExamScore >= 90.0) {
            return 'A';
        } else if (finalExamScore >= 75.0) {
            return 'B';
        } else if (finalExamScore >= 50.0) {
            return 'C';
        } else {
            return 'F';
        }
    }

    public void printReportCard() {
        System.out.println("ACADEMY REPORT CARD");
        System.out.println("Student ID    : " + studentId);
        System.out.println("Full Name     : " + fullName);
        System.out.println("Exam Score    : " + finalExamScore);
        System.out.println("Letter Grade  : " + calculateLetterGrade());
        System.out.println();
    }

    public void setFinalExamScore(double finalExamScore) {
        if (finalExamScore >= 0.0 && finalExamScore <= 100.0) {
            this.finalExamScore = finalExamScore;
        }
    }
}

public class AcademyAdmissionPortal {
    public static void main(String[] args) {
        System.out.println("Running Academy Admissions Simulation \n");

        StudentProfile student1 = new StudentProfile("Rahul Sharma", "A-2026-01", 82.5);
        StudentProfile student2 = new StudentProfile("Pranay Patel", "A-2026-02");

        student1.printReportCard();
        student2.printReportCard();

        System.out.println("... Walk-in student completes evaluation test ...");
        student2.setFinalExamScore(92.0);
        student2.printReportCard();
    }
}
