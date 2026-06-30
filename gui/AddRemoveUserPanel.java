package gui;

import api.*;

import javax.swing.*;
import java.awt.*;

public class AddRemoveUserPanel extends JPanel {

    private EmployeeDatabase employeeDatabase;

    private JScrollPane jscrollPane;
    private JList<Employee> jlist;
    private DefaultListModel<Employee> defaultListModel;

    private Employee selectedEmployee;

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JTextField passwordField;

    private JButton addButton;
    private JButton deleteButton;
    private JButton backButton;

    public JButton getAddButton(){ return addButton; }
    public JButton getDeleteButton(){ return deleteButton; }
    public JButton getBackButton() { return backButton; }
    public DefaultListModel<Employee> getDefaultListModel() {return defaultListModel;}
    public JList<Employee> getJlist() {return jlist;}
    public JTextField getNameField() {return nameField;}
    public JTextField getSurnameField() {return surnameField;}
    public JTextField getEmailField() {return emailField;}
    public JTextField getUsernameField() {return  usernameField;}
    public JTextField getPasswordField() {return passwordField;}


    public AddRemoveUserPanel(EmployeeDatabase employeeDatabase) {
        this.employeeDatabase = employeeDatabase;

        defaultListModel = new DefaultListModel<>();
        Load();

        jlist = new JList<>(defaultListModel);

        setLayout(new BorderLayout());
        jscrollPane = new JScrollPane(jlist);
        jscrollPane.setPreferredSize(new Dimension(400, 0));
        add(jscrollPane, BorderLayout.WEST);

        setVisible(true);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        Color bg = Color.decode("#FFF2D7");
        form.setBackground(bg);
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(createLabel("Name:"));
        nameField = createTextField();
        form.add(nameField);
        form.add(Box.createVerticalStrut(8));

        form.add(createLabel("Surname:"));
        surnameField = createTextField();
        form.add(surnameField);
        form.add(Box.createVerticalStrut(8));

        form.add(createLabel("Username:"));
        usernameField = createTextField();
        form.add(usernameField);
        form.add(Box.createVerticalStrut(8));

        form.add(createLabel("Email:"));
        emailField = createTextField();
        form.add(emailField);
        form.add(Box.createVerticalStrut(8));

        form.add(createLabel("Password:"));
        passwordField = createTextField();
        form.add(passwordField);
        form.add(Box.createVerticalStrut(20));

        addButton = new JButton("Add");
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(140, 32));
        form.add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        deleteButton.setMaximumSize(new Dimension(140, 32));
        form.add(deleteButton);

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(140, 32));
        form.add(backButton);

        addButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        deleteButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));

        add(form, BorderLayout.CENTER);


        jlist.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedEmployee = jlist.getSelectedValue();
            }
        });

    }

    private void Load() {
        defaultListModel.clear();
        for (Employee e : employeeDatabase.getEmployeeArray()) {
            defaultListModel.addElement(e);
        }
    }

    public void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tf.setMaximumSize(new Dimension(220, 26));
        tf.setAlignmentX(Component.LEFT_ALIGNMENT);
        return tf;
    }
}
