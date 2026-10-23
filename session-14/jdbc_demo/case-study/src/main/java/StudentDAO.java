import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // READ: Display all students
    public void displayStudents() throws SQLException {

        String sql = "SELECT * FROM students";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Name: " + rs.getString("name") +
                                ", Age: " + rs.getInt("age") +
                                ", Course: " + rs.getString("course")
                );
            }
        }
    }


    // READ: Find one student by ID
    public void findStudentById(int id) throws SQLException {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getInt("age") + " | " +
                                    rs.getString("course")
                    );
                } else {
                    System.out.println("Student not found.");
                }
            }
        }
    }
}