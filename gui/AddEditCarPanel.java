package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddEditCarPanel extends JPanel {
    private CarDatabase carDatabase;

    private JScrollPane jscrollPane;
    private JList<Car> jlist;
    private DefaultListModel<Car> defaultListModel;
    private Car selectedCar;
    private JTextField plateField;
    private JTextField brandField;
    private JTextField typeField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField colorField;
    private JButton saveButton;
    private JButton addButton;
    private JButton backButton;

    public JButton getSaveButton() { return saveButton; }
    public JButton getAddButton() { return addButton; }
    public JButton getBackButton() { return backButton; }
    public Car getSelectedCar() { return selectedCar; }
    public JTextField getPlateField() { return plateField; }
    public JTextField getBrandField() { return brandField; }
    public JTextField getTypeField() { return typeField; }
    public JTextField getModelField() { return modelField; }
    public JTextField getYearField() { return yearField; }
    public JTextField getColorField() { return colorField; }
    public JList<Car> getJlist() { return jlist; }

    public AddEditCarPanel(CarDatabase carDatabase) {
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


        JLabel yearLabel = createLabel("Year:");
        form.add(yearLabel);
        yearField = createTextField();
        form.add(yearField);
        form.add(Box.createVerticalStrut(8));

        JLabel colorLabel = createLabel("Color:");
        form.add(colorLabel);
        colorField = createTextField();
        form.add(colorField);
        form.add(Box.createVerticalStrut(20));

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
                selectedCar = jlist.getSelectedValue();
                if (selectedCar != null) {
                    plateField.setText(selectedCar.getPlate());
                    brandField.setText(selectedCar.getBrand());
                    typeField.setText(selectedCar.getType());
                    modelField.setText(selectedCar.getModel());
                    yearField.setText(String.valueOf(selectedCar.getYear()));
                    colorField.setText(selectedCar.getColor());
                }
            }
        });


    }


    public void Load(CarDatabase carDatabase) {
        defaultListModel = new DefaultListModel<>();
        for (Car car : carDatabase.getCarArray()) {
            defaultListModel.addElement(car);
        }

        this.carDatabase = carDatabase;
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