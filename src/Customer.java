import java.lang.*;
import java.util.*;

public abstract class Customer implements MyObserver
{
    protected boolean canRent;
    protected boolean canReturn;
    protected HashMap<Integer,RentalRecord> rentalRecords;
    protected int minCarsRentedPerRental;
    protected int maxCarsRentedPerRental;
    protected int carsRented;
    protected String name;
    protected Inventory inventory;
    protected Simulation simulation;

    public Customer(String customerName, Inventory inventory, Simulation simulation)
    {
        this.simulation = simulation;
        this.inventory = inventory;
        simulation.registerObserver(this);
        inventory.registerObserver(this);
        name = customerName;
    }

    public void setCanRent(boolean value) { canRent = value; }

    public boolean getCanRent() { return canRent; }

    public void setCanReturn(boolean canReturn) { this.canReturn = canReturn; }

    public boolean getCanReturn() { return canReturn; }

    //called when notified by subjects
    public void update()
    {
        //check if rental period of any record has expired by getting clock value
        //if so invalidate it, set return to true
        int currentDay = simulation.getDay();

        for (RentalRecord record : rentalRecords.values())
        {
            if (record.getExpiryDay() == currentDay) {
                record.setCompleted(true);
                canReturn = true;
            }
        }

        //check if inventory values satisfies customer min car rental condition, accordingly set canRent
        if (inventory.getNumCars() >= minCarsRentedPerRental)
            canRent = true;
    }
}
