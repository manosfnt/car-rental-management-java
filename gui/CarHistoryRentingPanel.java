package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CarHistoryRentingPanel extends JPanel {

    private RentingDatabase rentingDatabase;

    private JScrollPane jscrollPane;
    private JList<Renting> jlist;
    private DefaultListModel<Renting> defaultListModel;

    private JTextField IdField;
    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;

    public JButton getClearButton() { return clearButton; }
    public JButton getBackButton() { return backButton; }

    public CarHistoryRentingPanel(RentingDatabase rentingDatabase) {
        Load(rentingDatabase);

        jlist = new JList<>(defaultListModel);


        setLayout(new BorderLayout());

        jscrollPane = new JScrollPane(jlist);
        jscrollPane.setPreferredSize(new Dimension(400, 0));
        add(jscrollPane, BorderLayout.WEST);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#FFF2D7"));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(createLabel("CAR ID:"));
        IdField = createTextField();
        form.add(IdField);
        form.add(Box.createVerticalStrut(20));

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
            int id = Integer.parseInt(IdField.getText());

            Renting searchRenting = new Renting(-1, id ,"", "" , "", "");
            ArrayList<Renting> results = rentingDatabase.search(searchRenting);

            defaultListModel.clear();

            for (Renting r : results) {
                defaultListModel.addElement(r);
            }

            jlist.repaint();
        });


        clearButton.addActionListener(e -> {
            IdField.setText("");
            defaultListModel.clear();
            for (Renting r : rentingDatabase.getRentingArray()) {
                defaultListModel.addElement(r);
            }
            jlist.repaint();
        });
    }

    public void Load(RentingDatabase rentingDatabase) {
        defaultListModel = new DefaultListModel<>();
        for (Renting renting : rentingDatabase.getRentingArray()) {
            defaultListModel.addElement(renting);
        }
        this.rentingDatabase = rentingDatabase;
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
