package gui;

import api.*;

import javax.swing.*;
import java.awt.*;

public class ReturnCarPanel extends JPanel {

    private CarDatabase carDatabase;

    private DefaultListModel<Car> rentedModel;
    private JList<Car> rentedList;
    private JScrollPane scrollPane;

    private Car selectedCar;

    private JLabel carValue;
    private JButton returnButton;
    private JButton refreshButton;
    private JButton backButton;

    public JButton getBackButton() { return backButton; }
    public JButton getReturnButton() { return returnButton; }
    public JButton getRefreshButton() { return refreshButton; }
    public JLabel getCarValue(){ return carValue; }
    public JList<Car> getRentedList() { return rentedList; }
    public Car getSelectedCar() { return selectedCar; }


    public void setSelectedCar(Car car) { selectedCar = car; }

    public ReturnCarPanel(CarDatabase carDatabase) {
        this.carDatabase = carDatabase;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#FFF2D7"));


        rentedModel = new DefaultListModel<>();
        rentedList = new JList<>(rentedModel);

        scrollPane = new JScrollPane(rentedList);
        scrollPane.setPreferredSize(new Dimension(420, 0));
        add(scrollPane, BorderLayout.WEST);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#FFF2D7"));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(form, BorderLayout.CENTER);

        form.add(label("Επιλεγμένο αυτοκίνητο:"));
        carValue = value(" ");
        form.add(carValue);
        form.add(Box.createVerticalStrut(16));

        returnButton = button("Return");
        form.add(returnButton);
        form.add(Box.createVerticalStrut(8));

        refreshButton = button("Refresh");
        form.add(refreshButton);
        form.add(Box.createVerticalStrut(24));

        backButton = button("Back");
        form.add(backButton);

        rentedList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedCar = rentedList.getSelectedValue();
                if (selectedCar == null) {
                    carValue.setText(" ");
                } else {
                    carValue.setText(selectedCar.toString());
                }
            }
        });

        refreshButton.addActionListener(e -> loadRentedCars());

        loadRentedCars();
    }

    public void loadRentedCars() {
        rentedModel.clear();

        for (Car car : carDatabase.getCarArray()) {
            if (car.getStatus().equals("Μη-Διαθέσιμο")) {
                rentedModel.addElement(car);
            }
        }
    }


    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 16));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel value(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 18));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JButton button(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.PLAIN, 20));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(180, 36));
        return b;
    }
}
