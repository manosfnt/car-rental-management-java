package gui;

import api.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchCarPanel extends JPanel {

    private CarDatabase carDatabase;

    private JScrollPane jscrollPane;
    private JList<Car> jlist;
    private DefaultListModel<Car> defaultListModel;

    private Car selectedCar;

    private JTextField plateField;
    private JTextField brandField;
    private JTextField typeField;
    private JTextField modelField;
    private JTextField colorField;
    private JTextField statusField;

    private JButton searchButton;
    private JButton clearButton;
    private JButton backButton;


    public JButton getSearchButton() { return searchButton; }
    public JButton getClearButton() { return clearButton; }
    public JButton getBackButton() { return backButton; }

    public SearchCarPanel(CarDatabase carDatabase) {
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

        JLabel plateLabel = createLabel("Plate:");
        form.add(plateLabel);
        plateField = createTextField();
        form.add(plateField);
        form.add(Box.createVerticalStrut(8));

        JLabel brandLabel = createLabel("Brand:");
        form.add(brandLabel);
        brandField = createTextField();
        form.add(brandField);
        form.add(Box.createVerticalStrut(8));

        JLabel typeLabel = createLabel("Type:");
        form.add(typeLabel);
        typeField = createTextField();
        form.add(typeField);
        form.add(Box.createVerticalStrut(8));

        JLabel modelLabel = createLabel("Model:");
        form.add(modelLabel);
        modelField = createTextField();
        form.add(modelField);
        form.add(Box.createVerticalStrut(8));

        JLabel colorLabel = createLabel("Color:");
        form.add(colorLabel);
        colorField = createTextField();
        form.add(colorField);
        form.add(Box.createVerticalStrut(8));

        JLabel statusLabel = createLabel("Status:");
        form.add(statusLabel);
        statusField = createTextField();
        form.add(statusField);
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
            Car searchCar = buildSearchCarFromFields();
            ArrayList<Car> results = carDatabase.search(searchCar);

            defaultListModel.clear();
            for (Car c : results)
                defaultListModel.addElement(c);

            jlist.clearSelection();
            selectedCar = null;
            jlist.repaint();
        });

        clearButton.addActionListener(e -> {
            clearFields();
            reloadAll();
        });
    }

    public void Load(CarDatabase carDatabase) {
        defaultListModel = new DefaultListModel<>();
        for (Car car : carDatabase.getCarArray()) {
            defaultListModel.addElement(car);
        }
        this.carDatabase = carDatabase;
    }

    private void reloadAll() {
        defaultListModel.clear();
        for (Car car : carDatabase.getCarArray()) {
            defaultListModel.addElement(car);
        }
        jlist.repaint();
    }

    private Car buildSearchCarFromFields() {
        String plate = plateField.getText();
        String brand = brandField.getText();
        String type = typeField.getText();
        String model = modelField.getText();
        String color = colorField.getText();
        String status = statusField.getText();


        return new Car(-1, plate, brand, type, model,-1,color, status);
    }

    private void clearFields() {
        plateField.setText("");
        brandField.setText("");
        typeField.setText("");
        modelField.setText("");
        colorField.setText("");
        statusField.setText("");
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
