import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentManagementFrame extends JFrame {
    private JTextField nameField, genderField, ageField, studentIDField;
    private JTable studentTable;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private DefaultTableModel tableModel;

    public StudentManagementFrame() {
        setTitle("Quản Lý Sinh Viên");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo các components
        nameField = new JTextField(20);
        genderField = new JTextField(10);
        ageField = new JTextField(5);
        studentIDField = new JTextField(10);

        addButton = new JButton("Thêm");
        updateButton = new JButton("Cập Nhật");
        deleteButton = new JButton("Xóa");
        clearButton = new JButton("Xóa Trường");

        // Tạo table model và table
        String[] columnNames = {"Mã Sinh Viên", "Họ Tên", "Giới Tính", "Tuổi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Tạo layout
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Mã Sinh Viên:"));
        inputPanel.add(studentIDField);
        inputPanel.add(new JLabel("Họ Tên:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Giới Tính:"));
        inputPanel.add(genderField);
        inputPanel.add(new JLabel("Tuổi:"));
        inputPanel.add(ageField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Thêm action listeners
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearFields());

        // Thêm listener cho việc chọn hàng trong bảng
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    populateFields(selectedRow);
                }
            }
        });

        // Load student data
        loadStudents();
    }

    private void addStudent() {
        String studentID = studentIDField.getText();
        String name = nameField.getText();
        String gender = genderField.getText();
        String ageText = ageField.getText();

        if (studentID.isEmpty() || name.isEmpty() || gender.isEmpty() || ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            Student student = new Student(name, gender, age, studentID);
            DatabaseHelper.addStudent(student);
            JOptionPane.showMessageDialog(this, "Sinh viên đã được thêm", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadStudents();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        String studentID = studentIDField.getText();
        String name = nameField.getText();
        String gender = genderField.getText();
        String ageText = ageField.getText();

        if (studentID.isEmpty() || name.isEmpty() || gender.isEmpty() || ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            Student student = new Student(name, gender, age, studentID);
            DatabaseHelper.updateStudent(student);
            JOptionPane.showMessageDialog(this, "Sinh viên đã được cập nhật", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadStudents();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        String studentID = studentIDField.getText();
        if (studentID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên để xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sinh viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DatabaseHelper.deleteStudent(studentID);
            JOptionPane.showMessageDialog(this, "Sinh viên đã được xóa", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadStudents();
        }
    }

    private void loadStudents() {
        List<Student> students = DatabaseHelper.getAllStudents();
        tableModel.setRowCount(0); // Clear existing data
        for (Student student : students) {
            Object[] row = {student.getStudentID(), student.getName(), student.getGender(), student.getAge()};
            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        studentIDField.setText("");
        nameField.setText("");
        genderField.setText("");
        ageField.setText("");
        studentTable.clearSelection();
    }

    private void populateFields(int row) {
        studentIDField.setText((String) tableModel.getValueAt(row, 0));
        nameField.setText((String) tableModel.getValueAt(row, 1));
        genderField.setText((String) tableModel.getValueAt(row, 2));
        ageField.setText(tableModel.getValueAt(row, 3).toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementFrame().setVisible(true);
        });
    }
}