package api;

public class Renting
{
    private int rentingId;
    private int carId;
    private String customerAfm;
    private String startingDate;
    private String expiringDate;
    private String employeeUsername;

    /**
     * @param rentingId το ID της ενοικίασης
     * @param carId το ID του αυτοκινήτου
     * @param customerAfm το ΑΦΜ του πελάτη
     * @param startingdate ημερομηνία έναρξης
     * @param expiringdate ημερομηνία λήξης
     * @param employeeUsername το username του υπαλλήλου
     */
    public Renting(int rentingId, int carId, String customerAfm,
                   String startingdate, String expiringdate, String employeeUsername)
    {
        this.rentingId = rentingId;
        this.carId = carId;
        this.customerAfm = customerAfm;
        this.startingDate = startingdate;
        this.expiringDate = expiringdate;
        this.employeeUsername = employeeUsername;
    }

    /**
     * @return το rentingId
     */
    public int getRentingId()
    {
        return rentingId;
    }

    /**
     * @return το carId
     */
    public int getCarId()
    {
        return carId;
    }

    /**
     * @return το ΑΦΜ πελάτη
     */
    public String getCustomerAfm()
    {
        return customerAfm;
    }

    /**
     * @return ημερομηνία έναρξης
     */
    public String getStartingDate()
    {
        return startingDate;
    }

    /**
     * @return ημερομηνία λήξης
     */
    public String getExpiringdate()
    {
        return expiringDate;
    }

    /**
     * @return username υπαλλήλου
     */
    public String getEmployeeUsername()
    {
        return employeeUsername;
    }

    /**
     * Επιστρέφει αναπαράσταση της ενοικίασης σε μορφή String.
     * @return στοιχεία ενοικίασης
     */
    @Override
    public String toString()
    {
        return rentingId + " " + carId + " " + customerAfm + " " + startingDate + " " + expiringDate + " " + employeeUsername;
    }
}
