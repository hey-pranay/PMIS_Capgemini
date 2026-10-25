
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        try {

            // Get all students
            dao.displayStudents();

            // Search student by ID
            dao.findStudentById(2);

            // Update student
            dao.updateStudent(7, "Taesha", 21);

            // Insert student
            dao.insertStudent("AjayP", 23, "JDBC");

            // Delete student by ID
            dao.deleteStudent(7);

        }catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());

        }catch (SQLException e) {
            System.err.println(
                    "Database operation failed: " + e.getMessage()
            );
        }
    }
}
