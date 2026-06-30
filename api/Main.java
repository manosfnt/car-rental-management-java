package api;

import gui.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;


public class Main
{

    public static void main(String[] args) throws IOException
    {
        //Δημιουργούμε τα databases
        CSVReader usersReader = new CSVReader("users.csv");
        usersReader.read();
        Database usersDatabase = new Database(usersReader.getFields(), usersReader.getEntries());
        EmployeeDatabase employeeDatabase = new EmployeeDatabase(usersDatabase);

        CSVReader carsReader = new CSVReader("vehicles_with_plates.csv");
        carsReader.read();
        Database carSimpleDatabase = new Database(carsReader.getFields(), carsReader.getEntries());
        CarDatabase carDatabase = new CarDatabase(carSimpleDatabase);

        CSVReader customerReader = new CSVReader("customers.csv");
        customerReader.read();
        Database customerSimpleDatabase = new Database(customerReader.getFields(), customerReader.getEntries());
        CustomerDatabase customerDatabase = new CustomerDatabase(customerSimpleDatabase);

        CSVReader rentingReader = new CSVReader("rentings.csv");
        rentingReader.read();
        Database rentingSimpleDatabase = new Database(rentingReader.getFields(), rentingReader.getEntries());
        RentingDatabase rentingDatabase = new RentingDatabase(rentingSimpleDatabase);

        //ο χρήστης που έκανε log in
        Employee[] loggedinEmployee = new Employee[1];

        //Φτιάχνουμε το window frame
        WindowFrame windowFrame = new WindowFrame("Εργασία");

        //Δημιουργία των Panels
        LoginPanel loginPanel = new LoginPanel();
        MenuPanel menuPanel = new MenuPanel();
        AddEditCarPanel addEditCarPanel = new AddEditCarPanel(carDatabase);
        AddEditCustomerPanel addEditCustomerPanel = new AddEditCustomerPanel(customerDatabase);
        SearchCarPanel searchCarPanel = new SearchCarPanel(carDatabase);
        SearchCustomerPanel searchCustomerPanel = new SearchCustomerPanel(customerDatabase);
        RentingPanel rentingPanel = new RentingPanel();
        ReturnCarPanel returnCarPanel = new ReturnCarPanel(carDatabase);
        CustomerHistoryRentingPanel customerHistoryRentingPanel = new CustomerHistoryRentingPanel(rentingDatabase);
        CarHistoryRentingPanel carHistoryRentingPanel = new CarHistoryRentingPanel(rentingDatabase);
        AddRemoveUserPanel addRemoveUserPanel = new AddRemoveUserPanel(employeeDatabase);

        //Αction listener για login interface
        loginPanel.getLoginButton().addActionListener(e -> {
            String  username = loginPanel.getUsernameField().getText();
            String  password = new String(loginPanel.getPasswordField().getPassword());


            if (!username.isEmpty() && !password.isEmpty())
            {
                Employee searchEmployee = new Employee("", "", username,"", password);

                ArrayList<Employee> search_query = employeeDatabase.search(searchEmployee);

                if (!search_query.isEmpty())
                {
                    loggedinEmployee[0] = search_query.get(0);
                    windowFrame.getContentPane().removeAll();
                    windowFrame.add(menuPanel);
                    windowFrame.reset();

                }
                else
                {
                    JOptionPane.showMessageDialog((loginPanel),"Έδωσες Λάθος Στοιχεία", "Unsuccessful Login", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog((loginPanel),"Έδωσες Κενό","Error",JOptionPane.ERROR_MESSAGE);
            }
        });


        //Action listeners για το menu panel
        menuPanel.getLogoutButton().addActionListener(
                e -> {
                    windowFrame.goTo(loginPanel);
                    loginPanel.getCancelButton().doClick();
                }
        );

        menuPanel.getAddEditCarButton().addActionListener(
                e ->{
                    addEditCarPanel.Load(carDatabase);
                    windowFrame.goTo(addEditCarPanel);
                }
        );
        menuPanel.getAddEditCustomerButton().addActionListener(
                e ->{
                    addEditCustomerPanel.Load(customerDatabase);
                    windowFrame.goTo(addEditCustomerPanel);
                }
        );
        menuPanel.getSearchCarButton().addActionListener(
                e ->{
                    searchCarPanel.getClearButton().doClick();
                    windowFrame.goTo(searchCarPanel);
                }
        );
        menuPanel.getSearchCustomerButton().addActionListener(
                e ->{
                    searchCustomerPanel.getClearButton().doClick();
                    windowFrame.goTo(searchCustomerPanel);
                }

        );
        menuPanel.getRentingCarButton().addActionListener(
                e ->{
                    rentingPanel.Load(rentingDatabase, carDatabase, customerDatabase, loggedinEmployee[0]);
                    windowFrame.goTo(rentingPanel);
                }

        );
        menuPanel.getReturnCarButton().addActionListener(
                e ->{
                    searchCustomerPanel.getClearButton().doClick();
                    windowFrame.goTo(returnCarPanel);
                    returnCarPanel.getRefreshButton().doClick();
                }
        );
        menuPanel.getCustomerHistoryButton().addActionListener(
                    e ->{
                        windowFrame.goTo(customerHistoryRentingPanel);
                        customerHistoryRentingPanel.getClearButton().doClick();
                    }
        );
        menuPanel.getCarHistoryButton().addActionListener(
                e ->{
                    windowFrame.goTo(carHistoryRentingPanel);
                    carHistoryRentingPanel.getClearButton().doClick();
                }
        );
        menuPanel.getAddDeleteUserButton().addActionListener(
                e ->{
                    windowFrame.goTo(addRemoveUserPanel);
                }
        );


        //Ειδικοί action listener που αλλάζουν τα αρχεία csv
        addEditCarPanel.getAddButton().addActionListener(e -> {

            DefaultListModel<Car> model = (DefaultListModel<Car>) addEditCarPanel.getJlist().getModel();

            String plate = addEditCarPanel.getPlateField().getText();
            String brand = addEditCarPanel.getBrandField().getText();
            String type  = addEditCarPanel.getTypeField().getText();
            String carModel = addEditCarPanel.getModelField().getText();
            String yearText = addEditCarPanel.getYearField().getText();
            String color = addEditCarPanel.getColorField().getText();

            if (plate.isEmpty() || brand.isEmpty() || type.isEmpty()
                    || carModel.isEmpty() || yearText.isEmpty() || color.isEmpty())
            {
                JOptionPane.showMessageDialog(addEditCarPanel,
                        "Συμπλήρωσε όλα τα πεδία πριν κάνεις Add.",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int year;
            try {
                year = Integer.parseInt(yearText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addEditCarPanel,
                        "Το έτος πρέπει να είναι αριθμός!",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (year < 0) { 
                JOptionPane.showMessageDialog(addEditCarPanel,
                        "Το έτος δεν μπορεί να είναι αρνητικό!",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }



            int id = model.getSize() + 1;
            Car newCar = new Car(id, plate, brand, type, carModel, year, color, "Διαθέσιμο");

            if (!carDatabase.checkUniqueness(newCar))
            {
                JOptionPane.showMessageDialog(addEditCarPanel,
                        "Το αυτοκίνητο δεν είναι μοναδικό (id, plate)!",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.addElement(newCar);
            carDatabase.insert(newCar);

            int newIndex = model.getSize() - 1;
            addEditCarPanel.getJlist().setSelectedIndex(newIndex);
            addEditCarPanel.getJlist().ensureIndexIsVisible(newIndex);
            addEditCarPanel.getJlist().repaint();

            Database savedb = carDatabase.transformtodatabase();
            try {
                CSVWriter writer = new CSVWriter("vehicles_with_plates.csv");
                writer.save(savedb);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        addEditCarPanel.getSaveButton().addActionListener(
                e -> {
                    if (addEditCarPanel.getSelectedCar() == null) return;
                    String plate  = addEditCarPanel.getPlateField().getText();
                    String brand  = addEditCarPanel.getBrandField().getText();
                    String type  =  addEditCarPanel.getTypeField().getText();
                    String model =  addEditCarPanel.getModelField().getText();
                    String year = addEditCarPanel.getYearField().getText();
                    String color = addEditCarPanel.getColorField().getText();
                    if (plate.isEmpty() || brand.isEmpty() || type.isEmpty()
                            || model.isEmpty() || year.isEmpty() || color.isEmpty())
                    {
                        JOptionPane.showMessageDialog(addEditCarPanel, "Μη έγκυρες τιμές", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int yearcheck;
                    try {
                        yearcheck = Integer.parseInt(year);
                        if ( yearcheck < 0 )
                        {
                            JOptionPane.showMessageDialog(addEditCarPanel, "Μη έγκυρες τιμές", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(addEditCarPanel, "Το έτος πρέπει να είναι αριθμός!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    addEditCarPanel.getSelectedCar().setPlate(plate);
                    addEditCarPanel.getSelectedCar().setBrand(brand);
                    addEditCarPanel.getSelectedCar().setType(type);
                    addEditCarPanel.getSelectedCar().setModel(model);
                    addEditCarPanel.getSelectedCar().setYear(Integer.parseInt(year));
                    addEditCarPanel.getSelectedCar().setColor(color);

                    addEditCarPanel.getJlist().repaint();

                    Database savedb = carDatabase.transformtodatabase();
                    try {
                        CSVWriter writer = new CSVWriter("vehicles_with_plates.csv");
                        writer.save(savedb);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
                );

        addEditCustomerPanel.getAddButton().addActionListener(e -> {

            DefaultListModel<Customer> model = (DefaultListModel<Customer>) addEditCustomerPanel.getJlist().getModel();

            String name = addEditCustomerPanel.getNameField().getText();
            String surname = addEditCustomerPanel.getSurnameField().getText();
            String email = addEditCustomerPanel.getEmailField().getText();
            String afm = addEditCustomerPanel.getAfmField().getText();
            String telephone = addEditCustomerPanel.getTelephoneField().getText();

            if (name.isEmpty() || surname.isEmpty() || email.isEmpty()
                    || afm.isEmpty() || telephone.isEmpty())
            {
                JOptionPane.showMessageDialog(addEditCustomerPanel,
                        "Συμπλήρωσε όλα τα πεδία πριν κάνεις Add.",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int telephonecheck;
            int afmcheck;
            try {
                telephonecheck = Integer.parseInt(telephone);
                afmcheck = Integer.parseInt(afm);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addEditCustomerPanel,
                        "Το τηλέφωνο και το ΑΦΜ πρέπει να είναι αριθμοί!",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (telephonecheck < 0 || afmcheck < 0) {
                JOptionPane.showMessageDialog(addEditCustomerPanel,
                        "Το τηλεφωνο και το ΑΦΜ δεν μπορεί να είναι αρνητικά!",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Customer newCustomer = new Customer(name, surname, email, afm, telephone);
            if (!customerDatabase.checkUniqueness(newCustomer))
            {
                JOptionPane.showMessageDialog(addEditCustomerPanel,
                        "Ο πελάτης δεν είναι μοναδικός (ΑΦΜ)!",
                        "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.addElement(newCustomer);
            customerDatabase.insert(newCustomer);

            int newIndex = model.getSize() - 1;
            addEditCustomerPanel.getJlist().setSelectedIndex(newIndex);
            addEditCustomerPanel.getJlist().ensureIndexIsVisible(newIndex);
            addEditCustomerPanel.getJlist().repaint();

            Database savedb = customerDatabase.transformToDatabase();
            try {
                CSVWriter writer = new CSVWriter("customers.csv");
                writer.save(savedb);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        addEditCustomerPanel.getSaveButton().addActionListener(
                e -> {
                    if (addEditCustomerPanel.getSelectedCustomer() == null) return;
                    String name  = addEditCustomerPanel.getNameField().getText();
                    String surname  = addEditCustomerPanel.getSurnameField().getText();
                    String email  =  addEditCustomerPanel.getEmailField().getText();
                    String afm =  addEditCustomerPanel.getAfmField().getText();
                    String telephone = addEditCustomerPanel.getTelephoneField().getText();
                    if (name.isEmpty() || surname.isEmpty() || email.isEmpty()
                            || afm.isEmpty() || telephone.isEmpty())
                    {
                        JOptionPane.showMessageDialog(addEditCustomerPanel, "Μη έγκυρες τιμές", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int telephonecheck;
                    int afmcheck;
                    try {
                        telephonecheck = Integer.parseInt(telephone);
                        afmcheck = Integer.parseInt(afm);
                        if ( telephonecheck < 0 || afmcheck < 0)
                        {
                            JOptionPane.showMessageDialog(addEditCustomerPanel, "Μη έγκυρες τιμές", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(addEditCustomerPanel, "Το έτος πρέπει να είναι αριθμός!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    addEditCustomerPanel.getSelectedCustomer().setName(name);
                    addEditCustomerPanel.getSelectedCustomer().setSurname(surname);
                    addEditCustomerPanel.getSelectedCustomer().setEmail(email);
                    addEditCustomerPanel.getSelectedCustomer().setAfm(afm);
                    addEditCustomerPanel.getSelectedCustomer().setTelephone(telephone);

                    addEditCustomerPanel.getJlist().repaint();

                    Database savedb = customerDatabase.transformToDatabase();
                    try {
                        CSVWriter writer = new CSVWriter("customers.csv");
                        writer.save(savedb);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
        );


        rentingPanel.getRentButton().addActionListener(
            e -> {
                if(rentingPanel.rent())
                {
                    Database savedcars = carDatabase.transformtodatabase();
                    Database savedrentings = rentingDatabase.transformToDatabase();

                    try {
                        CSVWriter writer = new CSVWriter("vehicles_with_plates.csv");
                        writer.save(savedcars);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        CSVWriter writer = new CSVWriter("rentings.csv");
                        writer.save(savedrentings);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        );

        returnCarPanel.getReturnButton().addActionListener(e -> {
            if (returnCarPanel.getSelectedCar() == null) {
                return;
            }
            returnCarPanel.getSelectedCar().setStatus("Διαθέσιμο");
            carDatabase.update(returnCarPanel.getSelectedCar());

            Database savedDatabase = carDatabase.transformtodatabase();
            try {
                CSVWriter writer = new CSVWriter("vehicles_with_plates.csv");
                writer.save(savedDatabase);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            returnCarPanel.loadRentedCars();

            returnCarPanel.setSelectedCar(null);
            returnCarPanel.getCarValue().setText(" ");
        });

        addRemoveUserPanel.getAddButton().addActionListener(e -> {

            String name = addRemoveUserPanel.getNameField().getText();
            String surname = addRemoveUserPanel.getSurnameField().getText();
            String username = addRemoveUserPanel.getUsernameField().getText();
            String email = addRemoveUserPanel.getEmailField().getText();
            String password = addRemoveUserPanel.getPasswordField().getText();
            Employee newEmp = new Employee(name, surname, username , email , password);

            employeeDatabase.insert(newEmp);
            addRemoveUserPanel.getDefaultListModel().addElement(newEmp);

            addRemoveUserPanel.clearFields();
            addRemoveUserPanel.getJlist().clearSelection();
            addRemoveUserPanel.getJlist().repaint();

            Database savedDatabase = employeeDatabase.transformToDatabase();
            try {
                CSVWriter writer = new CSVWriter("users.csv");
                writer.save(savedDatabase);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
        addRemoveUserPanel.getDeleteButton().addActionListener(e->{
            int index = addRemoveUserPanel.getJlist().getSelectedIndex();
            employeeDatabase.delete(index);
            addRemoveUserPanel.getDefaultListModel().remove(index);

            addRemoveUserPanel.clearFields();
            addRemoveUserPanel.getJlist().clearSelection();
            addRemoveUserPanel.getJlist().repaint();

            Database savedDatabase = employeeDatabase.transformToDatabase();
            try {
                CSVWriter writer = new CSVWriter("users.csv");
                writer.save(savedDatabase);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //Action Listeners για τα Back button
        addEditCarPanel.getBackButton().addActionListener(e ->{windowFrame.goTo(menuPanel);});
        addEditCustomerPanel.getBackButton().addActionListener(e ->{windowFrame.goTo(menuPanel);});
        searchCarPanel.getBackButton().addActionListener(e ->{windowFrame.goTo(menuPanel);});
        searchCustomerPanel.getBackButton().addActionListener(e ->{windowFrame.goTo(menuPanel);});
        rentingPanel.getBackButton().addActionListener(e ->{windowFrame.goTo(menuPanel);});
        returnCarPanel.getBackButton().addActionListener(e ->{windowFrame.goTo(menuPanel);});
        customerHistoryRentingPanel.getBackButton().addActionListener(e ->{windowFrame.goTo(menuPanel);});
        carHistoryRentingPanel.getBackButton().addActionListener( e->{windowFrame.goTo(menuPanel);});
        addRemoveUserPanel.getBackButton().addActionListener(e ->{ windowFrame.goTo(menuPanel);});

        //αρχικοποίηση παραθύρου
        windowFrame.add(loginPanel);
        windowFrame.setLocationRelativeTo(null);
        windowFrame.setVisible(true);

    }
}