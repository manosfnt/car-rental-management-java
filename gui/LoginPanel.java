package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public JTextField getUsernameField() { return usernameField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
    public JButton getCancelButton() { return cancelButton; }

    public LoginPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        setBackground(Color.decode("#FFF2D7"));
        setOpaque(true);

        add(Box.createVerticalGlue());

        Font font = new Font("SansSerif", Font.PLAIN, 25);


        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(font);
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(userLabel);

        usernameField = new JTextField();
        styleField(usernameField, font);
        add(usernameField);

        add(Box.createRigidArea(new Dimension(0, 20)));


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(passwordLabel);

        passwordField = new JPasswordField();
        styleField(passwordField, font);
        add(passwordField);

        add(Box.createRigidArea(new Dimension(0, 30)));


        loginButton = createButton("Login", font);
        cancelButton = createButton("Cancel", font);

        add(loginButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(cancelButton);

        cancelButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });

        add(Box.createVerticalGlue());
    }

    private void styleField(JTextField field, Font font) {
        field.setFont(font);
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        field.setMaximumSize(new Dimension(600, 70));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
    private JButton createButton(String text, Font font) {
        JButton b = new JButton(text);
        b.setFont(font);

        b.setMaximumSize(new Dimension(200, 40));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);

        b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        b.setFocusPainted(false);
        return b;
    }


}
