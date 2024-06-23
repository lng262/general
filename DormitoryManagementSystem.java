import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DormitoryManagementSystem {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Hệ Thống Quản Lý Ký Túc Xá");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create the main panel with CardLayout
        JPanel mainPanel = new JPanel(new CardLayout());

        // Create navigation panel
        JPanel navigationPanel = new JPanel();
        JButton studentManagementButton = new JButton("Quản Lý Sinh Viên");
        JButton roomManagementButton = new JButton("Quản Lý Phòng");
        navigationPanel.add(studentManagementButton);
        navigationPanel.add(roomManagementButton);

        // Add panels to main panel
        mainPanel.add(createStudentManagementPanel(), "StudentManagement");
        mainPanel.add(createRoomManagementPanel(), "RoomManagement");

        // Add action listeners for buttons
        studentManagementButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "StudentManagement");
        });

        roomManagementButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (mainPanel.getLayout());
            cl.show(mainPanel, "RoomManagement");
        });

        // Add panels to frame
        frame.add(navigationPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Set the frame visibility to true
        frame.setVisible(true);
    }

    private static JPanel createStudentManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create a form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Họ tên:");
        JTextField nameField = new JTextField();
        JLabel genderLabel = new JLabel("Giới tính:");
        JTextField genderField = new JTextField();
        JLabel ageLabel = new JLabel("Tuổi:");
        JTextField ageField = new JTextField();
        JLabel studentIDLabel = new JLabel("Mã Sinh Viên:");
        JTextField studentIDField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(genderLabel);
        formPanel.add(genderField);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(studentIDLabel);
        formPanel.add(studentIDField);

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Thêm");
        JButton updateButton = new JButton("Cập Nhật");
        JButton viewButton = new JButton("Xem");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(viewButton);

        // Add form and button panels to the main student management panel
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add student logic here
                JOptionPane.showMessageDialog(panel, "Thêm Sinh Viên Thành Công!");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update student logic here
                JOptionPane.showMessageDialog(panel, "Cập Nhật Sinh Viên Thành Công!");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View student logic here
                JOptionPane.showMessageDialog(panel, "Xem Thông Tin Sinh Viên!");
            }
        });

        return panel;
    }

    private static JPanel createRoomManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create a form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel roomNumberLabel = new JLabel("Số Phòng:");
        JTextField roomNumberField = new JTextField();
        JLabel capacityLabel = new JLabel("Sức Chứa:");
        JTextField capacityField = new JTextField();
        JLabel buildingLabel = new JLabel("Tòa Nhà:");
        JTextField buildingField = new JTextField();

        formPanel.add(roomNumberLabel);
        formPanel.add(roomNumberField);
        formPanel.add(capacityLabel);
        formPanel.add(capacityField);
        formPanel.add(buildingLabel);
        formPanel.add(buildingField);

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        JButton addRoomButton = new JButton("Thêm Phòng");
        JButton viewRoomsButton = new JButton("Xem Phòng");
        buttonPanel.add(addRoomButton);
        buttonPanel.add(viewRoomsButton);

        // Add form and button panels to the main room management panel
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add room logic here
                String roomNumber = roomNumberField.getText();
                String capacity = capacityField.getText();
                String building = buildingField.getText();
                // Logic to add the room to the system (e.g., adding to a database or list)

                JOptionPane.showMessageDialog(panel, "Thêm Phòng Thành Công!\nSố Phòng: " + roomNumber + "\nSức Chứa: " + capacity + "\nTòa Nhà: " + building);
            }
        });

        viewRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // View rooms logic here
                JOptionPane.showMessageDialog(panel, "Xem Thông Tin Phòng!");
            }
        });

        return panel;
    }
}
