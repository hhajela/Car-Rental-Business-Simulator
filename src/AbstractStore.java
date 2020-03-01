import java.util.*;

abstract public class AbstractStore
{
    protected Inventory inventory;
    protected int totalEarnings;
    protected int dailyEarnings;
    protected ArrayList<Customer> customers;
    protected HashMap<Integer, RentalRecord> completedRentals;
    protected HashMap<Integer, RentalRecord> activeRentals;

    public void addCustomer(Customer c) { customers.add(c); }
    public void removeCustomer(Customer c) { customers.remove(c);}

    public void processBooking(Customer customer)
    {
        //pick random value for number of cars depending on customer's maximum
        int numberOfCars = 1;
    }

    public void processReturn(Customer customer)
    {

    }

    public Inventory getInventory() { return inventory; }

    public ArrayList<RentalRecord> getCompletedRentals() { return new ArrayList<RentalRecord>(completedRentals.values()); }

    public ArrayList<RentalRecord> getActiveRentals() { return new ArrayList<RentalRecord>(activeRentals.values()); }

    public int getDailyEarnings() { return dailyEarnings; }

}
