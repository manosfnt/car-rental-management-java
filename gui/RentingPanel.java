package gui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RentingPanel extends JPanel {

    private RentingDatabase rentingDatabase;
    private CarDatabase carDatabase;
    private CustomerDatabase customerDatabase;
    private Employee loggedInEmployee;

    private DefaultListModel<Car> carModel;
    private JList<Car> carList;
    private Car selectedCar;

    private JTextField afmField;
    private JLabel customerValue;
    private Customer selectedCustomer;

    private JTextField startField;
    private JTextField endField;

    private JLabel empValue;
    private JLabel messageLabel;

    private JButton searchButton;
    private JButton rentButton;
    private JButton refreshButton;
    private JButton backButton;

    public JButton getBackButton() { return backButton; }
    public JButton getRentButton() { return rentButton; }

    public RentingPanel() {
        setLayout(new BorderLayout());

        carModel = new DefaultListModel<>();
        carList = new JList<>(carModel);

        JScrollPane carScroll = new JScrollPane(carList);
        carScroll.setPreferredSize(new Dimension(400, 0));
        add(carScroll, BorderLayout.WEST);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(Color.decode("#FFF2D7"));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        form.add(label("Επιλεγμένο αμάξι: "));
        JLabel carValue = value("Δεν έχετε επιλέξει αμάξι.");
        form.add(carValue);
        form.add(Box.createVerticalStrut(12));

        form.add(label("AFM:"));
        afmField = field();
        form.add(afmField);
        form.add(Box.createVerticalStrut(8));

        searchButton = button("Search Customer");
        searchButton.setMaximumSize(new Dimension(220, 32));
        form.add(searchButton);
        form.add(Box.createVerticalStrut(8));

        customerValue = value("Δεν έχετε επιλέξει πελάτη.");
        form.add(customerValue);
        form.add(Box.createVerticalStrut(8));

        form.add(label("Έναρξη Ενοικίασης (yyyy-mm-dd):"));
        startField = field();
        form.add(startField);
        form.add(Box.createVerticalStrut(8));

        form.add(label("Λήξη Ενοικίασης (yyyy-mm-dd):"));
        endField = field();
        form.add(endField);
        form.add(Box.createVerticalStrut(8));

        messageLabel = value(" ");
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        form.add(messageLabel);
        form.add(Box.createVerticalStrut(8));

        form.add(label("Υπάλληλος:"));
        empValue = value("");
        form.add(empValue);
        form.add(Box.createVerticalStrut(8));


        rentButton = button("Rent");
        refreshButton = button("Refresh Cars");
        backButton = button("Back");

        form.add(rentButton);
        form.add(refreshButton);
        form.add(backButton);

        add(form, BorderLayout.CENTER);



        carList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedCar = carList.getSelectedValue();
                if (selectedCar == null)
                    carValue.setText("Δεν έχεις επιλέξει κάποιο αμάξι.");
                else
                    carValue.setText(selectedCar.toString());
            }
        });

        searchButton.addActionListener(e ->  {
            if (customerDatabase == null) return;

            String afm = afmField.getText();

            Customer searchCustomer = new Customer("", "", "", "", "");
            searchCustomer.setAfm(afm);

            ArrayList<Customer> customer = customerDatabase.search(searchCustomer);
            if (customer.isEmpty()) {
                selectedCustomer = null;
                customerValue.setText("Δεν βρέθηκε πελάτης.");
            } else {
                selectedCustomer = customer.get(0);
                customerValue.setText("Selected: " + selectedCustomer);
            }
        });
        refreshButton.addActionListener(e -> loadCars());
    }

    public void Load(RentingDatabase rentingDatabase, CarDatabase carDatabase,
                     CustomerDatabase customerDatabase, Employee loggedInEmployee) {

        this.rentingDatabase = rentingDatabase;
        this.carDatabase = carDatabase;
        this.customerDatabase = customerDatabase;
        this.loggedInEmployee = loggedInEmployee;

        empValue.setText(loggedInEmployee.getUsername());

        loadCars();

        revalidate();
        repaint();
    }

    private void loadCars() {
        carModel.clear();
        if (carDatabase == null)
            return;
        for (Car c : carDatabase.getCarArray())
            if (c.getStatus().equals("Διαθέσιμο"))
                carModel.addElement(c);

        selectedCar = null;
    }

    private int nextRentingId() {
        int maxId = 0;

        ArrayList<Renting> allRentings = rentingDatabase.getRentingArray();
        for (Renting r : allRentings)
            if (r.getRentingId() > maxId) {
                maxId = r.getRentingId();
            }
        return maxId + 1;
    }

 public boolean rent() {
        if (rentingDatabase == null) return false;

        String startingDate = startField.getText();
        String endingDate = endField.getText();

        if (startingDate.isEmpty() || endingDate.isEmpty())
        {
            msg("Διάλεξε ημερομηνίες για ενοικίαση.");
            return false;
        }

        LocalDate Date1 = LocalDate.parse(startingDate);
        LocalDate Date2 = LocalDate.parse(endingDate);

        if (Date2.isBefore(Date1)) {
            msg("Λανθασμένη Ημερομηνία.");
            return false;
        }


        Renting renting = new Renting(nextRentingId(), selectedCar.getId(), selectedCustomer.getAfm(),
                                      startingDate, endingDate, loggedInEmployee.getUsername());

        rentingDatabase.insert(renting);
        selectedCar.setStatus("Μη-Διαθέσιμο");


        startField.setText("");
        endField.setText("");
        selectedCar = null;

        loadCars();
        return true;
    }

    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("SansSerif", Font.PLAIN, 14));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private JLabel value(String t) {
        JLabel V = new JLabel(t);
        V.setFont(new Font("SansSerif", Font.BOLD, 14));
        V.setAlignmentX(Component.LEFT_ALIGNMENT);
        return V;
    }

    private JTextField field() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tf.setMaximumSize(new Dimension(320, 26));
        tf.setAlignmentX(Component.LEFT_ALIGNMENT);
        return tf;
    }

    private JButton button(String t) {
        JButton b = new JButton(t);
        b.setFont(new Font("SansSerif", Font.PLAIN, 20));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(220, 32));
        return b;
    }

    public void msg(String t) {
        messageLabel.setText(t);
    }
}
