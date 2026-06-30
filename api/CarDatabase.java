package api;

import java.util.*;

public class CarDatabase {

    private ArrayList<Car> carArray;
    private Database database;

    /**
     * Κατασκευαστής που αρχικοποιεί την CarDatabase από ένα Database.
     * @param database το Database από το οποίο θα φορτωθούν τα αυτοκίνητα
     */
    public CarDatabase(Database database) {
        carArray = new ArrayList<>();
        this.database = database;

        for (ArrayList<String> entry : database.getEntries()) {
            Car car = new Car(
                    Integer.parseInt(entry.get(0)),
                    entry.get(1),
                    entry.get(2),
                    entry.get(3),
                    entry.get(4),
                    Integer.parseInt(entry.get(5)),
                    entry.get(6),
                    entry.get(7)
            );
            carArray.add(car);
        }
    }

    /**
     * Εισάγει ένα νέο αυτοκίνητο στη λίστα.
     * @param car το Car που θα προστεθεί
     */
    public void insert(Car car) {
        carArray.add(car);
    }

    /**
     * Επεξεργάζεται ένα αυτοκίνητο στη θέση index.
     * @param car το αυτοκίνητο που περιέχει τις νέες τιμές (εκτός id/plate)
     * @param index η θέση στη λίστα carArray που θα ενημερωθεί
     */
    public void edit(Car car, int index) {
        Car editCar = new Car(
                carArray.get(index).getId(),
                carArray.get(index).getPlate(),
                car.getBrand(),
                car.getType(),
                car.getModel(),
                car.getYear(),
                car.getColor(),
                car.getStatus()
        );
        carArray.set(index, editCar);
    }

    /**
     * Αναζητά αυτοκίνητα που ταιριάζουν με τα κριτήρια του searchCar.
     * Υποστηρίζει wildcards:
     * -1 για αριθμητικά πεδία (id, year) σημαίνει οποιαδήποτε τιμή
     * "" (κενό String) για κείμενα σημαίνει οποιαδήποτε τιμή
     * @param searchCar αντικείμενο Car που περιγράφει τα κριτήρια αναζήτησης
     * @return λίστα με τα Car που ταιριάζουν στα κριτήρια
     */
    public ArrayList<Car> search(Car searchCar) {
        ArrayList<Car> returnedArray = new ArrayList<>();

        for (Car car : carArray) {
            if ((car.getId() == searchCar.getId() || searchCar.getId() == -1) &&
                    (car.getPlate().equals(searchCar.getPlate()) || searchCar.getPlate().isEmpty()) &&
                    (car.getBrand().equals(searchCar.getBrand()) || searchCar.getBrand().isEmpty()) &&
                    (car.getType().equals(searchCar.getType()) || searchCar.getType().isEmpty()) &&
                    (car.getModel().equals(searchCar.getModel()) || searchCar.getModel().isEmpty()) &&
                    (car.getYear() == searchCar.getYear() || searchCar.getYear() == -1) &&
                    (car.getColor().equals(searchCar.getColor()) || searchCar.getColor().isEmpty()) &&
                    (car.getStatus().equals(searchCar.getStatus()) || searchCar.getStatus().isEmpty())) {
                returnedArray.add(car);
            }
        }

        return returnedArray;
    }

    /**
     * Επιστρέφει την εσωτερική λίστα αυτοκινήτων by reference.
     * @return ArrayList με τα  Car
     */
    public ArrayList<Car> getCarArray() {
        return carArray;
    }

    /**
     * Μετατρέπει την τρέχουσα λίστα carArray σε αντικείμενο Database,
     * ώστε να μπορεί να αποθηκευτεί/χρησιμοποιηθεί με τη μορφή fields + entries.
     * @return νέο Database που περιέχει τα δεδομένα των αυτοκινήτων
     */
    public Database transformtodatabase() {
        ArrayList<String> fields = new ArrayList<>(database.getFields());

        ArrayList<ArrayList<String>> entries = new ArrayList<>();
        for (Car car : carArray) {
            ArrayList<String> entry = new ArrayList<>();
            entry.add(Integer.toString(car.getId()));
            entry.add(car.getPlate());
            entry.add(car.getBrand());
            entry.add(car.getType());
            entry.add(car.getModel());
            entry.add(Integer.toString(car.getYear()));
            entry.add(car.getColor());
            entry.add(car.getStatus());

            entries.add(entry);
        }

        return new Database(fields, entries);
    }

    /**
     * Ενημερώνει ένα αυτοκίνητο στη λίστα, εντοπίζοντάς το με βάση το id.
     * Αν βρεθεί ίδιο id, αντικαθίσταται ολόκληρο το αντικείμενο.
     * @param car το νέο Car που θα αντικαταστήσει το παλιό με ίδιο id
     */
    public void update(Car car) {
        for (int i = 0; i < carArray.size(); i++) {
            if (carArray.get(i).getId() == car.getId()) {
                carArray.set(i, car);
                return;
            }
        }
    }

    /**
     * Ελέγχει μοναδικότητα id και plate.
     * @param car το νέο Car που θα περάσει τον έλεγχο.
     */
    public boolean checkUniqueness(Car car){
        for ( Car c :  carArray) {
            if ( c.getPlate().equals(car.getPlate()) || c.getId() == car.getId())
                return false;
        }
        return true;
    }
}
