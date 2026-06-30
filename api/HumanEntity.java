package api;

public class HumanEntity
{
    private String name;
    private String surname;
    private String email;

    /**
     * Δημιουργεί ένα νέο HumanEntity
     * @param name το όνομα
     * @param surname το επώνυμο
     * @param email το email
     */
    public HumanEntity(String name, String surname, String email)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * @return το όνομα
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return το επώνυμο
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * @return το email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param name το νέο όνομα
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param surname το νέο επώνυμο
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * @param email το νέο email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
}
