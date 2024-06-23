import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/DormitoryManagement";
    private static final String USER = "root";
    private static final String PASSWORD = "yourpassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Phương thức xác thực người dùng
    public static boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Trả về true nếu tìm thấy user
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức quản lý sinh viên
    public static void addStudent(Student student) {
        String sql = "INSERT INTO Students (studentID, name, gender, age) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGender());
            pstmt.setInt(4, student.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(Student student) {
        String sql = "UPDATE Students SET name = ?, gender = ?, age = ? WHERE studentID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getStudentID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String studentID) {
        String sql = "DELETE FROM Students WHERE studentID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                students.add(new Student(name, gender, age, studentID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Phương thức quản lý phòng
    public static void addRoom(Room room) {
        String sql = "INSERT INTO Rooms (roomNumber, capacity, building) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, room.getRoomNumber());
            pstmt.setInt(2, room.getCapacity());
            pstmt.setString(3, room.getBuilding());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRoom(Room room) {
        String sql = "UPDATE Rooms SET capacity = ?, building = ? WHERE roomNumber = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, room.getCapacity());
            pstmt.setString(2, room.getBuilding());
            pstmt.setString(3, room.getRoomNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRoom(String roomNumber) {
        String sql = "DELETE FROM Rooms WHERE roomNumber = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String roomNumber = rs.getString("roomNumber");
                int capacity = rs.getInt("capacity");
                String building = rs.getString("building");
                rooms.add(new Room(roomNumber, capacity, building));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}