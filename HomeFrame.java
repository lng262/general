import javax.swing.*;

public class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Dormitory Management Home");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Welcome to Dormitory Management System", SwingConstants.CENTER);
        add(welcomeLabel);
    }
}
