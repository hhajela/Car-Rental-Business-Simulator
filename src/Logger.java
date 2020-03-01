import java.util.*;

public class Logger
{
    private ArrayList<RentalRecord> activeRentals;
    private ArrayList<RentalRecord> completedRentals;
    private static Logger inst;
    private Logger()
    {}

    public void printActiveRentals(ArrayList<RentalRecord> records)
    {
        for(RentalRecord r : records)
        {

        }
    }

    public void printCompletedRentals(ArrayList<RentalRecord> records)
    {
        for(RentalRecord r : records)
        {

        }
    }

    public void printCurrentInventory(ArrayList<Car> cars)
    {
        System.out.println("Inventory Status:-");
        for(Car car : cars)
        {

        }
        System.out.println("Total cars in inventory:  " + cars.size());
    }

    public void printDailyEarnings(int earnings, int day) { System.out.println("Total daily earnings on day " + day + " are $" + earnings);}

    public static synchronized Logger getInstance()
    {
        if (inst == null)
            inst = new Logger();

        return inst;
    }
}
