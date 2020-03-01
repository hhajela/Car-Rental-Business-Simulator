import java.util.*;

abstract public class AbstractStore
{
    private Inventory inventory;
    private int totalEarnings;
    private int dailyEarnings;
    private ArrayList<Customer> customers;
    private HashMap<Integer, RentalRecord> rentalRecords;

    public void addCustomer(Customer c) { customers.add(c); }
    public void removeCustomer(Customer c) { customers.remove(c);}

}
