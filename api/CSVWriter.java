package api;

import java.io.*;

public class CSVWriter extends CSVIO
{
    BufferedWriter bf;
    FileWriter fl;

    /**
     * Δημιουργεί έναν CSVWriter για το δοσμένο αρχείο.
     * @param filename το όνομα του αρχείου CSV
     * @throws IOException σε περίπτωση σφάλματος I/O
     */
    public CSVWriter(String filename) throws IOException
    {
        super(filename);
        bf = null;
        fl = null;
    }

    /**
     * Αποθηκεύει τα δεδομένα ενός Database σε αρχείο CSV.
     * @param db το αντικείμενο Database που θα αποθηκευτεί
     * @throws IOException σε περίπτωση σφάλματος εγγραφής
     */
    public void save(Database db) throws IOException
    {
        fl = new FileWriter(filename);
        bf = new BufferedWriter(fl);

        bf.write("");
        for (int i = 0; i < db.getFields().size(); i++)
        {
            bf.append(db.getFields().get(i));
            if (i < db.getFields().size() - 1)
            {
                bf.append(",");
            }
        }

        for (int i = 0; i < db.getEntries().size(); i++)
        {
            bf.append("\n");
            for (int j = 0; j < db.getEntry(i).size(); j++)
            {
                bf.append(db.getEntry(i).get(j));
                if (j < db.getEntry(i).size() - 1)
                {
                    bf.append(",");
                }
            }
        }

        bf.close();
        fl.close();
    }
}
