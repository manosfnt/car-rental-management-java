package api;

public class CSVIO
{
    protected String filename;

    /**
     * Κατασκευαστης της CSVIO.
     * @param filename το όνομα του αρχείου CSV
     */
    public CSVIO(String filename)
    {
        this.filename = filename;
    }

    /**
     * Επιστρέφει το όνομα του αρχείου CSV
     * @return το όνομα του αρχείου
     */
    public String getFilename()
    {
        return filename;
    }
}
