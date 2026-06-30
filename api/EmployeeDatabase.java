package api;

import java.util.*;

public class EmployeeDatabase
{
    private ArrayList<Employee> employeeArray;
    private Database database;

    /**
     * Δημιουργεί τη βάση εργαζομένων από ένα αντικείμενο Database.
     * @param database η βάση δεδομένων από την οποία φορτώνονται οι εργαζόμενοι
     */
    public EmployeeDatabase(Database database)
    {
        employeeArray = new ArrayList<>();
        this.database = database;

        for (ArrayList<String> entry : database.getEntries())
        {
            Employee employee = new Employee(
                    entry.get(0),
                    entry.get(1),
                    entry.get(2),
                    entry.get(3),
                    entry.get(4)
            );
            employeeArray.add(employee);
        }
    }

    /**
     * Προσθέτει έναν νέο εργαζόμενο.
     * @param employee ο εργαζόμενος προς εισαγωγή
     */
    public void insert(Employee employee)
    {
        employeeArray.add(employee);
    }

    /**
     * Διαγράφει έναν εργαζόμενο με βάση το index.
     * @param index η θέση του εργαζομένου
     */
    public void delete(int index)
    {
        employeeArray.remove(index);
    }

    /**
     * Επεξεργάζεται έναν εργαζομενο στη δοσμένη θέση.
     * @param employee ο εργαζόμενος με τα νέα στοιχεία
     * @param index η θέση του εργαζόμενου
     */
    public void edit(Employee employee, int index)
    {
        employeeArray.set(index, employee);
    }

    /**
     * Επιστρέφει τη λίστα των εργαζομένων.
     * @return λίστα εργαζομένων
     */
    public ArrayList<Employee> getEmployeeArray()
    {
        return employeeArray;
    }

    /**
     * Αναζητά εργαζομένους με βαση τα δοσμένα κριτήρια.
     * @param searchEmployee ο εργαζόμενος αναζήτησης
     * @return λίστα εργαζομένων που ταιριάζουν
     */
    public ArrayList<Employee> search(Employee searchEmployee)
    {
        ArrayList<Employee> returnedArray = new ArrayList<>();

        for (Employee employee : employeeArray)
        {
            if ( (employee.getName().equals(searchEmployee.getName()) || searchEmployee.getName().isEmpty()) &&
                    (employee.getSurname().equals(searchEmployee.getSurname()) || searchEmployee.getSurname().isEmpty()) &&
                    (employee.getEmail().equals(searchEmployee.getEmail()) || searchEmployee.getEmail().isEmpty()) &&
                    (employee.getUsername().equals(searchEmployee.getUsername()) || searchEmployee.getUsername().isEmpty()) &&
                    (employee.getPassword().equals(searchEmployee.getPassword()) || searchEmployee.getPassword().isEmpty()) )
            {
                returnedArray.add(employee);
            }
        }

        return returnedArray;
    }

    /**
     * Μετατρέπει τη λίστα εργαζομένων σε αντικείμενο Database
     * @return Database με τα δεδομένα των εργαζομένων
     */
    public Database transformToDatabase()
    {
        ArrayList<String> fields = new ArrayList<>(database.getFields());
        ArrayList<ArrayList<String>> entries = new ArrayList<>();

        for (Employee employee : employeeArray)
        {
            ArrayList<String> entry = new ArrayList<>();
            entry.add(employee.getName());
            entry.add(employee.getSurname());
            entry.add(employee.getUsername());
            entry.add(employee.getEmail());
            entry.add(employee.getPassword());

            entries.add(entry);
        }

        return new Database(fields, entries);
    }
}
