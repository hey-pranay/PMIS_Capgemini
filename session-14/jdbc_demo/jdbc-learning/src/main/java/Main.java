import java.sql.SQLException;

public class Main {
//    public static void main(String[] args) {
//        System.out.println("New JDBC learning...");
//
//        /*
//        try (Connection connection = DBConnection.getConnection()) {
//            System.out.println("Database connected successfully...");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//         */
//
//
//        // read data using select
//        System.out.println();
//        System.out.println("reading data using select statement");
//        String sql = "select * from students";
//
//        try (
//                Connection conn = DBConnection.getConnection();
//                Statement st = conn.createStatement();
//                ResultSet rs = st.executeQuery(sql);
//        ) {
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                String course = rs.getString("course");
//
//                System.out.println(
//                        id + " | " + name + " | " + age + " | " + course
//                );
//
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//
//        // Create a record using insert
//        System.out.println();
//        System.out.println("creating a record using insert");
//        String createRecord =
//                "insert into students (name, age, course)" +
//                        "values ('Neha' , 23, 'Java')";
//
//        // try-with-resourse
//        // When you put an object inside the try (...) parentheses,
//        // that object becomes a resource.
//        // The system guarantees that this resource will be
//        // automatically closed as soon as the try block finishes,
//        // even if an unexpected error occurs.
//        try (
//                Connection con = DBConnection.getConnection();
//                Statement st = con.createStatement();
//        ) {
//            int rows = st.executeUpdate(createRecord);
//            System.out.println(rows + " rows inserted");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // update data using Update
//        String updateRecord =
//                "UPDATE students SET age = 24 WHERE id = 1";
//
//        try (
//                Connection con = DBConnection.getConnection();
//                Statement stmt = con.createStatement()
//        ) {
//            int rows = stmt.executeUpdate(updateRecord);
//
//            System.out.println(rows + " row updated.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // delete data using delete
//
//        String deleteRecord =
//                "DELETE FROM students WHERE id = 3";
//
//        try (
//                Connection con = DBConnection.getConnection();
//                Statement stmt = con.createStatement()
//        ) {
//            int rows = stmt.executeUpdate(deleteRecord);
//
//            System.out.println(rows + " row deleted.");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        // use of preparedStatement
//        String Psql =
//                "SELECT * FROM students WHERE name = ?";
//
//        try (
//                Connection con = DBConnection.getConnection();
//                PreparedStatement ps = con.prepareStatement(Psql)
//        ) {
//            ps.setString(1, "Rahul");
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    System.out.println(
//                            rs.getInt("id") + " " +
//                                    rs.getString("name")
//                    );
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        try {
            // CREATE
//            dao.addStudent("Ajay", 23, "Java");

            // READ ALL
//            dao.displayStudents();

            // SEARCH
            dao.findStudentById(2);

            // UPDATE
//            dao.updateStudent(10, "Pranay", 52);

            // DELETE
//             dao.deleteStudent(5);

        } catch (SQLException e) {
            System.err.println(
                    "Database operation failed: " + e.getMessage()
            );
        }
    }

}