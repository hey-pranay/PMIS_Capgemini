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

    // UPDATE: Change a student's details
    public void updateStudent(int id, String name, int age)
            throws SQLException {

        String sql =
                "UPDATE students SET name= ? ,age = ? " +
                        "WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            System.out.println(rows + " student updated.");
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