package api;

public class Customer extends HumanEntity
{
    private String afm;
    private String telephone;

    /**
     * @param name το όνομα του πελάτη
     * @param surname το επώνυμο του πελάτη
     * @param email το email του πελάτη
     * @param afm το ΑΦΜ του πελάτη
     * @param telephone το τηλέφωνο του πελάτη
     */
    public Customer(String name, String surname, String email, String afm, String telephone)
    {
        super(name, surname, email);
        this.afm = afm;
        this.telephone = telephone;
    }

    /**
     * @return το ΑΦΜ
     */
    public String getAfm()
    {
        return afm;
    }

    /**
     * Επιστρέφει το τηλέφωνο του πελάτη.
     *
     * @return το τηλέφωνο
     */
    public String getTelephone()
    {
        return telephone;
    }

    /**
     * @param afm το νέο ΑΦΜ
     */
    public void setAfm(String afm)
    {
        this.afm = afm;
    }

    /**
     * Ορίζει το τηλέφωνο του πελάτη.
     *
     * @param telephone το νέο τηλέφωνο
     */
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * Επιστρέφει αναπαράσταση του πελάτη σε μορφή String.
     * @return στοιχεία του πελάτη
     */
    @Override
    public String toString()
    {
        return getName() + " " + getSurname() + " " + getEmail() + " " +
                getAfm() + " " + getTelephone();
    }
}

