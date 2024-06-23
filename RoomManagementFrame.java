import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RoomManagementFrame extends JFrame {
    private JTextField roomNumberField, capacityField, buildingField;
    private JTable roomTable;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private DefaultTableModel tableModel;

    public RoomManagementFrame() {
        setTitle("Quản Lý Phòng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo các components
        roomNumberField = new JTextField(10);
        capacityField = new JTextField(5);
        buildingField = new JTextField(20);

        addButton = new JButton("Thêm");
        updateButton = new JButton("Cập Nhật");
        deleteButton = new JButton("Xóa");
        clearButton = new JButton("Xóa Trường");

        // Tạo table model và table
        String[] columnNames = {"Số Phòng", "Sức Chứa", "Tòa Nhà"};
        tableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(roomTable);

        // Tạo layout
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Số Phòng:"));
        inputPanel.add(roomNumberField);
        inputPanel.add(new JLabel("Sức Chứa:"));
        inputPanel.add(capacityField);
        inputPanel.add(new JLabel("Tòa Nhà:"));
        inputPanel.add(buildingField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Thêm action listeners
        addButton.addActionListener(e -> addRoom());
        updateButton.addActionListener(e -> updateRoom());
        deleteButton.addActionListener(e -> deleteRoom());
        clearButton.addActionListener(e -> clearFields());

        // Thêm listener cho việc chọn hàng trong bảng
        roomTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = roomTable.getSelectedRow();
                if (selectedRow != -1) {
                    populateFields(selectedRow);
                }
            }
        });

        // Load room data
        loadRooms();
    }

    private void addRoom() {
        String roomNumber = roomNumberField.getText();
        String capacityText = capacityField.getText();
        String building = buildingField.getText();

        if (roomNumber.isEmpty() || capacityText.isEmpty() || building.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int capacity = Integer.parseInt(capacityText);
            Room room = new Room(roomNumber, capacity, building);
            DatabaseHelper.addRoom(room);
            JOptionPane.showMessageDialog(this, "Phòng đã được thêm", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadRooms();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sức chứa phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateRoom() {
        String roomNumber = roomNumberField.getText();
        String capacityText = capacityField.getText();
        String building = buildingField.getText();

        if (roomNumber.isEmpty() || capacityText.isEmpty() || building.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int capacity = Integer.parseInt(capacityText);
            Room room = new Room(roomNumber, capacity, building);
            DatabaseHelper.updateRoom(room);
            JOptionPane.showMessageDialog(this, "Phòng đã được cập nhật", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadRooms();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Sức chứa phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRoom() {
        String roomNumber = roomNumberField.getText();
        if (roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng để xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phòng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DatabaseHelper.deleteRoom(roomNumber);
            JOptionPane.showMessageDialog(this, "Phòng đã được xóa", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            loadRooms();
        }
    }

    private void loadRooms() {
        List<Room> rooms = DatabaseHelper.getAllRooms();
        tableModel.setRowCount(0); // Clear existing data
        for (Room room : rooms) {
            Object[] row = {room.getRoomNumber(), room.getCapacity(), room.getBuilding()};
            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        roomNumberField.setText("");
        capacityField.setText("");
        buildingField.setText("");
        roomTable.clearSelection();
    }

    private void populateFields(int row) {
        roomNumberField.setText((String) tableModel.getValueAt(row, 0));
        capacityField.setText(tableModel.getValueAt(row, 1).toString());
        buildingField.setText((String) tableModel.getValueAt(row, 2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoomManagementFrame().setVisible(true);
        });
    }
}