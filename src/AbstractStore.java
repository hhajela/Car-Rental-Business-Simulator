import java.util.*;

abstract public class AbstractStore
{
    protected Inventory inventory;
    protected int totalEarnings;
    protected int dailyEarnings;
    protected ArrayList<Customer> customers;
    protected HashMap<Integer, RentalRecord> rentalRecords;

    public void addCustomer(Customer c) { customers.add(c); }
    public void removeCustomer(Customer c) { customers.remove(c);}

}
