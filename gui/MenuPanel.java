package gui;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private JButton logoutButton;
    private JButton AddEditCarButton;
    private JButton AddEditCustomerButton;
    private JButton SearchCarButton;
    private JButton SearchCustomerButton;
    private JButton RentingCarButton;
    private JButton ReturnCarButton;
    private JButton CustomerHistoryButton;
    private JButton CarHistoryButton;
    private JButton AddDeleteUserButton;

    public JButton getLogoutButton()
    {
        return logoutButton;
    }
    public JButton getAddEditCarButton() { return  AddEditCarButton; }
    public JButton getAddEditCustomerButton() { return AddEditCustomerButton; }
    public JButton getSearchCarButton(){ return SearchCarButton; }
    public JButton getSearchCustomerButton(){ return SearchCustomerButton; }
    public JButton getRentingCarButton(){ return  RentingCarButton; }
    public JButton getReturnCarButton(){ return ReturnCarButton; }
    public JButton getCustomerHistoryButton() { return CustomerHistoryButton; }
    public JButton getCarHistoryButton(){ return  CarHistoryButton; }
    public JButton getAddDeleteUserButton(){ return AddDeleteUserButton; }


    public MenuPanel() {


        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        setBackground(Color.decode("#FFF2D7"));
        setOpaque(true);

        logoutButton = new JButton("Log Out");
        logoutButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setBackground(Color.RED);
        logoutButton.setOpaque(true);

        JPanel logoutWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutWrapper.setOpaque(false);
        logoutWrapper.add(logoutButton);

        add(logoutWrapper, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(5, 2, 40, 25));
        grid.setOpaque(false);

        Font font = new Font("SansSerif", Font.PLAIN, 16);

        AddEditCarButton = createButton("Προσθήκη / Επεξεργασία Αυτοκινήτου", font);
        AddEditCustomerButton = createButton("Προσθήκη / Επεξεργασία Πελάτη", font);
        SearchCarButton = createButton("Αναζήτηση Αυτοκινήτου", font);
        SearchCustomerButton = createButton("Αναζήτηση Πελάτη", font);
        RentingCarButton = createButton("Ενοικίαση Αυτοκινήτου", font);
        ReturnCarButton = createButton("Επιστροφή Αυτοκινήτου", font);
        CustomerHistoryButton = createButton("Ιστορικό Ενοικιάσεων Πελάτη", font);
        CarHistoryButton = createButton("Ιστορικό Ενοικιάσεων Αυτοκινήτου", font);
        AddDeleteUserButton = createButton("Προσθήκη / Αφαίρεση Χρηστών", font);

        grid.add(AddEditCarButton);
        grid.add(AddEditCustomerButton);
        grid.add(SearchCarButton);
        grid.add(SearchCustomerButton);
        grid.add(RentingCarButton);
        grid.add(ReturnCarButton);
        grid.add(CustomerHistoryButton);
        grid.add(CarHistoryButton);
        grid.add(AddDeleteUserButton);
        grid.add(logoutButton);

        add(grid, BorderLayout.CENTER);
    }

    private JButton createButton(String text, Font font) {
        JButton b = new JButton(text);
        b.setFont(font);
        b.setPreferredSize(new Dimension(700, 70));
        b.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        b.setFocusPainted(false);
        return b;
    }


}