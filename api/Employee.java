package api;

public class Employee extends HumanEntity
{
    private String username;
    private String password;

    /**
     * @param name το όνομα του εργαζομένου
     * @param surname το επώνυμο του εργαζομένου
     * @param username το όνομα χρήστη
     * @param email το email του εργαζομένου
     * @param password ο κωδικός πρόσβασης
     */
    public Employee(String name, String surname, String username, String email, String password)
    {
        super(name, surname, email);
        this.username = username;
        this.password = password;
    }

    /**
     * @return το username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @return το password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param username το νέο username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @param password ο νέος κωδικός
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Επιστρέφει αναπαράσταση του εργαζομένου σε μορφή String.
     * @return στοιχεία εργαζομένου
     */
    @Override
    public String toString()
    {
        return getName() + " " + getSurname() + " " + getUsername() + " " + getEmail() + " *****";
    }
}
