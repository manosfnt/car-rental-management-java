package api;

public class Car
{
    private int id;
    private String plate;
    private String brand;
    private String type;
    private String model;
    private int year;
    private String color;
    private String status;

    /**
     * Κατασκευαστής της κλάσης Car.
     *
     * @param id μοναδικό αναγνωριστικό
     * @param plate πινακίδα αυτοκινήτου
     * @param brand μάρκα αυτοκινήτου
     * @param type τύπος αυτοκινήτου
     * @param model μοντέλο
     * @param year έτος κατασκευής
     * @param color χρώμα
     * @param status κατάσταση διαθεσιμότητας
     */
    public Car(int id, String plate, String brand, String type,
               String model, int year, String color, String status)
    {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.type = type;
        this.model = model;
        this.year = year;
        this.color = color;
        this.status = status;
    }

    /**
     * @return το μοναδικό ID του αυτοκινήτου
     */
    public int getId() { return id; }

    /**
     * @return την πινακίδα αυτοκινήτου
     */
    public String getPlate() { return plate; }

    /**
     * @return τη μάρκα του αυτοκινήτου
     */
    public String getBrand() { return brand; }

    /**
     * @return τον τύπο του αυτοκινήτου
     */
    public String getType() { return type; }

    /**
     * @return το μοντέλο του αυτοκινήτου
     */
    public String getModel() { return model; }

    /**
     * @return το έτος κατασκευής
     */
    public int getYear() { return year; }

    /**
     *  @return το χρώμα του αυτοκινήτου
     */
    public String getColor() { return color; }

    /**
     @return την κατάσταση διαθεσιμότητας
     */
    public String getStatus() { return status; }

    /**
     * Ορίζει το ID του αυτοκινήτου.
     * @param id νέο ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * Ορίζει την πινακίδα κυκλοφορίας.
     * @param plate νέα πινακίδα
     */
    public void setPlate(String plate) { this.plate = plate; }

    /**
     * Ορίζει τη μάρκα του αυτοκινήτου.
     * @param brand νέα μάρκα
     */
    public void setBrand(String brand) { this.brand = brand; }

    /**
     * Ορίζει τον τύπο του αυτοκινήτου.
     * @param type νέος τύπος
     */
    public void setType(String type) { this.type = type; }

    /**
     * Ορίζει το μοντέλο του αυτοκινήτου.
     * @param model νέο μοντέλο
     */
    public void setModel(String model) { this.model = model; }

    /**
     * Ορίζει το έτος κατασκευής.
     * @param year νέο έτος
     */
    public void setYear(int year) { this.year = year; }

    /**
     * Ορίζει το χρώμα του αυτοκινήτου.
     * @param color νέο χρώμα
     */
    public void setColor(String color) { this.color = color; }

    /**
     * Ορίζει την κατάσταση διαθεσιμότητας.
     * @param status νέα κατάσταση
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Επιστρέφει αναπαράσταση του αυτοκινήτου σε μορφή String.
     * @return όλα τα βασικά στοιχεία του αυτοκινήτου σε μία γραμμή
     */
    @Override
    public String toString()
    {
        return getId() + " " + getPlate() + " " + getBrand() + " " +
                getType() + " " + getModel() + " " + getYear() + " " +
                getColor() + " " + getStatus();
    }
}
