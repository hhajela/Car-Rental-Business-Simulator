import java.lang.*;
import java.util.*;

public abstract class Customer implements MyObserver
{
    private boolean canRent;
    private boolean canReturn;
    private HashMap<Integer,RentalRecord> rentalRecords;
    private int minCarsRentedPerRental;
    private int carsRented;
    private String name;
    private Inventory inventory;
    private Simulation simulation;

    public Customer(String customerName)
    {
        name = customerName;
    }

    public void setCanRent(boolean value)
    {
        canRent = value;
    }
    public boolean getCanRent()
    {
        return canRent;
    }

    //called when notified by subjects
    public void update()
    {
        //check if rental period of any record has expired by getting clock value
        //if so invalidate it, set return to true

        //check if inventory values satisfies customer min car rental condition, accordingly set canRent
        if (inventory.getNumCars() >= minCarsRentedPerRental)
            canRent = true;
    }
}
