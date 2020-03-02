import java.util.*;

public abstract class Customer implements MyObserver
{
    protected boolean canRent;
    protected boolean canReturn;
    protected HashSet<RentalRecord> rentalRecords;
    protected int minCarsRentedPerRental;
    protected int maxCarsRentedPerRental;
    protected int minRentalDuration;
    protected int maxRentalDuration;
    protected int carsRented;
    protected String name;
    protected Inventory inventory;
    protected Simulation simulation;
    protected String type;

    public Customer(String customerName, Inventory inventory, Simulation simulation)
    {
        this.simulation = simulation;
        this.inventory = inventory;
        rentalRecords = new HashSet<RentalRecord>();
        simulation.registerObserver(this);
        inventory.registerObserver(this);
        name = customerName;
        canReturn = false;
        canRent = true;
    }

    //getters and setters
    public void setCanRent(boolean value) { canRent = value; }

    public boolean getCanRent() { return canRent; }

    public void setCanReturn(boolean canReturn) { this.canReturn = canReturn; }

    public boolean getCanReturn() { return canReturn; }

    public String getName() { return name; }

    public int getMinCarsRentedPerRental() { return minCarsRentedPerRental; }

    public int getMaxCarsRentedPerRental() { return maxCarsRentedPerRental; }

    public int getMinRentalDuration() { return minRentalDuration; }

    public int getMaxRentalDuration() { return maxRentalDuration; }

    public int getCarsRented() { return carsRented; }

    public void setCarsRented(int carsRented) { this.carsRented = carsRented; }

    public HashSet<RentalRecord> getRentalRecords() { return rentalRecords; }

    public String getType() { return this.type; }

    //called when notified by subjects
    public void update()
    {
        //check if rental period of any record has expired by getting clock value
        //if so invalidate it, set return to true
        int currentDay = simulation.getDay();

        for (RentalRecord record : rentalRecords)
        {
            if (record.getExpiryDay() == currentDay) {
                record.setCompleted(true);
                canReturn = true;
            }
        }

        //check if inventory values satisfies customer min car rental condition
        //if we haven't already the max amount of cars allowed and inventory has enough cars, set can rent to true
        if (inventory.getNumCars() >= minCarsRentedPerRental && carsRented < 4)
            canRent = true;
        else
            canRent = false;
    }
}
