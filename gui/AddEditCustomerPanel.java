package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddEditCustomerPanel extends JPanel {
    private CustomerDatabase customerDatabase;

    private JScrollPane jscrollPane;
    private JList<Customer> jlist;
    private DefaultListModel<Customer> defaultListModel;
    private Customer selectedCustomer;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField emailField;
    private JTextField afmField;
    private JTextField telephoneField;

    private JButton saveButton;
    private JButton addButton;
    private JButton backButton;

    public JButton getSaveButton() { return saveButton; }
    public JButton getAddButton() { return addButton; }
    public JButton getBackButton() { return backButton; }

    public Customer getSelectedCustomer() { return selectedCustomer; }
    public JTextField getNameField() { return nameField; }
    public JTextField getSurnameField() { return surnameField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getAfmField() { return afmField; }
    public JTextField getTelephoneField() { return telephoneField; }
    public JList<Customer> getJlist() { return jlist; }

    public AddEditCustomerPanel(CustomerDatabase customerDatabase) {
        Load(customerDatabase);

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


        JLabel nameLabel = createLabel("Name:");
        form.add(nameLabel);
        nameField = createTextField();
        form.add(nameField);
        form.add(Box.createVerticalStrut(8));

        JLabel surnameLabel = createLabel("Surname:");
        form.add(surnameLabel);
        surnameField = createTextField();
        form.add(surnameField);
        form.add(Box.createVerticalStrut(8));

        JLabel emailLabel = createLabel("Email:");
        form.add(emailLabel);
        emailField = createTextField();
        form.add(emailField);
        form.add(Box.createVerticalStrut(8));

        JLabel afmLabel = createLabel("AFM:");
        form.add(afmLabel);
        afmField = createTextField();
        form.add(afmField);
        form.add(Box.createVerticalStrut(8));

        JLabel telephoneLabel = createLabel("Telephone:");
        form.add(telephoneLabel);
        telephoneField = createTextField();
        form.add(telephoneField);
        form.add(Box.createVerticalStrut(8));



        addButton = new JButton("Add");
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.setMaximumSize(new Dimension(120, 32));
        form.add(addButton);

        saveButton = new JButton("Edit");
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        saveButton.setMaximumSize(new Dimension(120, 32));
        form.add(saveButton);

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(120, 32));
        form.add(backButton);

        saveButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        addButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));

        add(form, BorderLayout.CENTER);


        jlist.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedCustomer = jlist.getSelectedValue();
                if (selectedCustomer != null) {
                    nameField.setText(selectedCustomer.getName());
                    surnameField.setText(selectedCustomer.getSurname());
                    emailField.setText(selectedCustomer.getEmail());
                    afmField.setText(selectedCustomer.getAfm());
                    telephoneField.setText(String.valueOf(selectedCustomer.getTelephone()));
                }
            }
        });

    }


    public void Load(CustomerDatabase customerDatabase) {
        defaultListModel = new DefaultListModel<>();
        for (Customer customer : customerDatabase.getCustomerArray()) {
            defaultListModel.addElement(customer);
        }

        this.customerDatabase = customerDatabase;
    }


    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField TextField = new JTextField();
        TextField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        TextField.setMaximumSize(new Dimension(320, 26));
        TextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        return TextField;
    }
}