package gui;

import api.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchCustomerPanel extends JPanel {

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

    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;


    public JButton getSearchButton() { return searchButton; }
    public JButton getClearButton() { return clearButton; }
    public JButton getBackButton() { return backButton; }

    public SearchCustomerPanel(CustomerDatabase carDatabase) {
        Load(carDatabase);

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


        searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchButton.setMaximumSize(new Dimension(140, 32));
        form.add(searchButton);

        clearButton = new JButton("Clear");
        clearButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        clearButton.setMaximumSize(new Dimension(140, 32));
        form.add(clearButton);

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        backButton.setMaximumSize(new Dimension(140, 32));
        form.add(backButton);

        searchButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        clearButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));

        add(form, BorderLayout.CENTER);


        searchButton.addActionListener(e -> {
            Customer searchCustomer = buildSearchCustomerFromFields();
            ArrayList<Customer> results = customerDatabase.search(searchCustomer);

            defaultListModel.clear();
            for (Customer c : results)
                defaultListModel.addElement(c);

            jlist.clearSelection();
            selectedCustomer = null;
            jlist.repaint();
        });

        clearButton.addActionListener(e -> {
            clearFields();
            reloadAll();
        });
    }

    public void Load(CustomerDatabase customerDatabase) {
        defaultListModel = new DefaultListModel<>();
        for (Customer customer : customerDatabase.getCustomerArray()) {
            defaultListModel.addElement(customer);
        }
        this.customerDatabase = customerDatabase;
    }

    private void reloadAll() {
        defaultListModel.clear();
        for (Customer customer : customerDatabase.getCustomerArray()) {
            defaultListModel.addElement(customer);
        }
        jlist.repaint();
    }

    private Customer buildSearchCustomerFromFields() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        String afm = afmField.getText();
        String telephone = telephoneField.getText();


        return new Customer(name , surname , email , afm , telephone);
    }

    private void clearFields() {
        nameField.setText("");
        surnameField.setText("");
        emailField.setText("");
        afmField.setText("");
        telephoneField.setText("");
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
