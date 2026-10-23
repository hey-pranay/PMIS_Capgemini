import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        try {

            // get all
            dao.displayStudents();

            // SEARCH
            dao.findStudentById(2);

            //UPDATE
            dao.updateStudent(7, "Taesha", 21);

        } catch (SQLException e) {
            System.err.println(
                    "Database operation failed: " + e.getMessage()
            );
        }
    }

}