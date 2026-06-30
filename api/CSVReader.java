package api;

import java.util.*;
import java.io.*;

public class CSVReader extends CSVIO
{
    private File file;
    private Scanner scanner;

    private ArrayList<String> fields;
    private ArrayList<ArrayList<String>> entries;

    /**
     * Δημιουργεί έναν CSVReader για το δοσμένο αρχείο.
     * @param filename το όνομα του αρχείου CSV
     * @throws FileNotFoundException αν το αρχείο δεν βρεθεί
     */
    CSVReader(String filename) throws FileNotFoundException
    {
        super(filename);
        file = new File(filename);
        scanner = null;
        fields = new ArrayList<>();
        entries = new ArrayList<>();
    }

    /**
     * Διαβάζει το αρχείο CSV και αποθηκεύει τα δεδομένα.
     * @throws FileNotFoundException αν το αρχείο δεν βρεθεί
     */
    public void read() throws FileNotFoundException
    {
        scanner = new Scanner(file);
        String[] strarray0 = scanner.nextLine().split(",");

        for (String str : strarray0)
        {
            fields.add(str);
        }

        while (scanner.hasNext())
        {
            ArrayList<String> line = new ArrayList<>();
            String[] strarray1 = scanner.nextLine().split(",");

            for (String str : strarray1)
            {
                line.add(str);
            }

            entries.add(line);
        }

        scanner.close();
    }

    /**
     * Επιστρέφει τα πεδία του CSV.
     * @return λίστα με τα ονόματα των πεδίων
     */
    public ArrayList<String> getFields()
    {
        return fields;
    }

    /**
     * Επιστρέφει τις εγγραφές του CSV.
     * @return λίστα εγγραφών (γραμμών)
     */
    public ArrayList<ArrayList<String>> getEntries()
    {
        return entries;
    }
}














