import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
        // Insert new student data
        public void insertStudent(String name, int age, String course) throws SQLException {

        String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Student inserted successfully!");
            }
        }
    }


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


    // DELETE: Delete a student by ID
    public boolean deleteStudent(int id) throws SQLException{

            //Case1: Validate student ID
        if(id <= 0){
            throw new IllegalArgumentException("Student ID must be greater than zero.");
        }

        String sql = "DELETE FROM students WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        )
        {
            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();

            if(rowsDeleted > 0){
                System.out.println(
                        "Student with ID "+ id + " deleted successfully."
                );
                return true;
            }else {
                System.out.println("Student not found with ID: "+ id);
            }
            return false;
        }
    }

}