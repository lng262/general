private void login() {
    String username = usernameField.getText();
    String password = new String(passwordField.getPassword());

    if (DatabaseHelper.authenticateUser(username, password)) {
        JOptionPane.showMessageDialog(this, "Đăng nhập thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        HomeFrame homeFrame = new HomeFrame();
        homeFrame.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}