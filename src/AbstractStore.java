import java.util.*;

abstract public class AbstractStore
{
    protected Inventory inventory;
    protected int totalEarnings;
    protected int dailyEarnings;
    protected ArrayList<Customer> customers;
    protected HashSet<RentalRecord> completedRentals;
    protected HashSet<RentalRecord> activeRentals;

    public AbstractStore()
    {
        completedRentals = new HashSet<RentalRecord>();
        activeRentals = new HashSet<RentalRecord>();
        customers = new ArrayList<Customer>();
        inventory = new Inventory();
    }

    public void addCustomer(Customer c) { customers.add(c); }
    public void removeCustomer(Customer c) { customers.remove(c);}

    public abstract void processBooking(Customer customer, int day);

    public abstract void processReturn(Customer customer);

    public Inventory getInventory() { return inventory; }

    public ArrayList<RentalRecord> getCompletedRentals() { return new ArrayList<RentalRecord>(completedRentals); }

    public ArrayList<RentalRecord> getActiveRentals() { return new ArrayList<RentalRecord>(activeRentals); }

    public int getDailyEarnings() { return dailyEarnings; }

    public abstract Customer getCustomerEligibleToRent(int customerIndex);

}
