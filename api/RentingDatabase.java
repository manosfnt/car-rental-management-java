package api;

import java.util.*;

public class RentingDatabase
{
    private ArrayList<Renting> rentingArray;
    private Database database;

    /**
     * Δημιουργεί τη βάση ενοικιάσεων από ένα αντικείμενο Database.
     * @param database η βάση δεδομένων από την οποία φορτώνονται οι ενοικιάσεις
     */
    public RentingDatabase(Database database)
    {
        rentingArray = new ArrayList<>();
        this.database = database;

        for (ArrayList<String> entry : database.getEntries())
        {
            Renting renting = new Renting(
                    Integer.parseInt(entry.get(0)),
                    Integer.parseInt(entry.get(1)),
                    entry.get(2),
                    entry.get(3),
                    entry.get(4),
                    entry.get(5)
            );
            rentingArray.add(renting);
        }
    }

    /**
     * @return λίστα ενοικιάσεων
     */
    public ArrayList<Renting> getRentingArray()
    {
        return rentingArray;
    }

    /**
     * Προσθέτει μία νέα ενοικίαση
     * @param renting η ενοικίαση προς εισαγωγή
     */
    public void insert(Renting renting)
    {
        rentingArray.add(renting);
    }

    /**
     * Αναζητά ενοικιάσεις με βαση τα δοσμένα κριτήρια.
     * Υποστηρίζει wildcards:
     * -1 για αριθμητικά πεδία σημαίνει οποιαδήποτε τιμή
     * ""(κενό String) για κείμενα σημαίνει οποιαδήποτε τιμή
     * @param searchRenting η ενοικίαση αναζήτησης
     * @return λίστα ενοικιάσεων που ταιριάζουν
     */
    public ArrayList<Renting> search(Renting searchRenting)
    {
        ArrayList<Renting> returnedArray = new ArrayList<>();

        for (Renting renting : rentingArray)
        {
            if ( (renting.getRentingId() == searchRenting.getRentingId() || searchRenting.getRentingId() == -1) &&
                    (renting.getCarId() == searchRenting.getCarId() || searchRenting.getCarId() == -1) &&
                    (renting.getCustomerAfm().equals(searchRenting.getCustomerAfm()) || searchRenting.getCustomerAfm().isEmpty()) &&
                    (renting.getStartingDate().equals(searchRenting.getStartingDate()) || searchRenting.getStartingDate().isEmpty()) &&
                    (renting.getExpiringdate().equals(searchRenting.getExpiringdate()) || searchRenting.getExpiringdate().isEmpty()) &&
                    (renting.getEmployeeUsername().equals(searchRenting.getEmployeeUsername()) || searchRenting.getEmployeeUsername().isEmpty()) )
            {
                returnedArray.add(renting);
            }
        }

        return returnedArray;
    }

    /**
     * Μετατρέπει τη λίστα ενοικιάσεων σε αντικείμενο Database.
     * @return Database με τα δεδομένα των ενοικιάσεων
     */
    public Database transformToDatabase()
    {
        ArrayList<String> fields = new ArrayList<>(database.getFields());
        ArrayList<ArrayList<String>> entries = new ArrayList<>();

        for (Renting renting : rentingArray)
        {
            ArrayList<String> entry = new ArrayList<>();
            entry.add(Integer.toString(renting.getRentingId()));
            entry.add(Integer.toString(renting.getCarId()));
            entry.add(renting.getCustomerAfm());
            entry.add(renting.getStartingDate());
            entry.add(renting.getExpiringdate());
            entry.add(renting.getEmployeeUsername());

            entries.add(entry);
        }

        return new Database(fields, entries);
    }
}
