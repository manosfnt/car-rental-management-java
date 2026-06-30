package api;

import java.util.*;

public class CustomerDatabase
{
    private ArrayList<Customer> customerArray;
    private Database database;

    /**
     * Δημιουργεί τη βάση πελατών από ένα αντικείμενο Database.
     * @param database η βάση δεδομένων από την οποία φορτώνονται οι πελάτες
     */
    public CustomerDatabase(Database database)
    {
        customerArray = new ArrayList<>();
        this.database = database;

        for (ArrayList<String> entry : database.getEntries())
        {
            Customer customer = new Customer(
                    entry.get(0),
                    entry.get(1),
                    entry.get(2),
                    entry.get(3),
                    entry.get(4)
            );
            customerArray.add(customer);
        }
    }

    /**
     * Επιστρέφει τη λίστα των πελατών by reference.
     * @return λίστα πελατών
     */
    public ArrayList<Customer> getCustomerArray()
    {
        return customerArray;
    }

    /**
     * Προσθέτει έναν νεο πελάτη.
     * @param customer ο πελάτης προς εισαγωγή
     */
    public void insert(Customer customer)
    {
        customerArray.add(customer);
    }

    /**
     * Επεξεργάζεται έναν πελατη στη δοσμένη θέση.
     * @param customer ο πελάτης με τα νέα στοιχεία
     * @param index η θέση του πελάτη στη λίστα
     */
    public void edit(Customer customer, int index)
    {
        Customer editCustomer = new Customer(
                customer.getName(),
                customer.getSurname(),
                customer.getEmail(),
                customerArray.get(index).getAfm(),
                customer.getTelephone()
        );
        customerArray.set(index, editCustomer);
    }

    /**
     * Αναζητά πελάτες με βαση τα δοσμένα κριτήρια.
     * @param searchCustomer ο πελάτης αναζήτησης
     * @return λίστα πελατων που ταιριάζουν
     */
    public ArrayList<Customer> search(Customer searchCustomer)
    {
        ArrayList<Customer> returnedArray = new ArrayList<>();

        for (Customer customer : customerArray)
        {
            if ( (customer.getName().equals(searchCustomer.getName()) || searchCustomer.getName().isEmpty()) &&
                    (customer.getSurname().equals(searchCustomer.getSurname()) || searchCustomer.getSurname().isEmpty()) &&
                    (customer.getEmail().equals(searchCustomer.getEmail()) || searchCustomer.getEmail().isEmpty()) &&
                    (customer.getAfm().equals(searchCustomer.getAfm()) || searchCustomer.getAfm().isEmpty()) &&
                    (customer.getTelephone().equals(searchCustomer.getTelephone()) || searchCustomer.getTelephone().isEmpty()) )
            {
                returnedArray.add(customer);
            }
        }

        return returnedArray;
    }

    /**
     * Μετατρέπει τη λιστα πελατών σε αντικείμενο Database.
     * @return Database με τα δεδομένα των πελατών
     */
    public Database transformToDatabase()
    {
        ArrayList<String> fields = new ArrayList<>(database.getFields());
        ArrayList<ArrayList<String>> entries = new ArrayList<>();

        for (Customer customer : customerArray)
        {
            ArrayList<String> entry = new ArrayList<>();
            entry.add(customer.getName());
            entry.add(customer.getSurname());
            entry.add(customer.getEmail());
            entry.add(customer.getAfm());
            entry.add(customer.getTelephone());

            entries.add(entry);
        }

        return new Database(fields, entries);
    }
    /**
     * Ελέγχει μοναδικότητα ΑΦΜ.
     * @param customer το νέο Customer που θα περάσει τον έλεγχο.
     */
    public boolean checkUniqueness(Customer customer)
    {
        for (Customer c : customerArray)
        {
            if (c.getAfm().equals(customer.getAfm()))
                return false;
        }
        return true;
    }
}
