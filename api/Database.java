package api;

import java.util.*;

/**
 * Κλάση που αναπαριστά μια γενική βάση δεδομένων
 * με πεδία (fields) και εγγραφές (entries).
 */
public class Database
{
    private ArrayList<String> fields;
    private ArrayList<ArrayList<String>> entries;

    /**
     * Δημιουργει ένα αντικείμενο Database.
     * @param fields λίστα με τα ονόματα των πεδίων
     * @param entries λίστα με τις εγγραφές
     */
    public Database(ArrayList<String> fields, ArrayList<ArrayList<String>> entries)
    {
        this.fields = new ArrayList<String>(fields);
        this.entries = new ArrayList<ArrayList<String>>(entries);
    }

    /**
     * Επιστρέφει τα πεδια της βάσης δεδομένων.
     * @return λίστα πεδίων
     */
    public ArrayList<String> getFields()
    {
        return fields;
    }

    /**
     * Επιστρέφει όλες τις εγγραφές.
     * @return λίστα εγγραφών
     */
    public ArrayList<ArrayList<String>> getEntries()
    {
        return entries;
    }

    /**
     * Επιστρέφει μία εγγραφή με βάση το index
     * @param index η θέση της εγγραφής
     * @return η εγγραφή στη συγκεκριμένη θέση
     */
    public ArrayList<String> getEntry(int index)
    {
        return entries.get(index);
    }

    /**
     * Εισάγει μια νέα εγγραφή στη βάση.
     * @param entry εγγραφη προς εισαγωγή
     */
    public void insertEntry(ArrayList<String> entry)
    {
        ArrayList<String> line = new ArrayList<String>(entry);
        entries.add(line);
    }

    /**
     * Διαγράφει μια εγγραφή από τη βάση.
     * @param index η θεση της εγγραφής που θα διαγραφεί
     */
    public void deleteEntry(int index)
    {
        entries.remove(index);
    }
}