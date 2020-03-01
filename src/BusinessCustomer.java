import java.util.*;

public class BusinessCustomer extends Customer
{
    public BusinessCustomer(String customerName, Inventory inventory, Simulation simulation)
    {
        super(customerName, inventory, simulation);
        minCarsRentedPerRental = 3;
        maxCarsRentedPerRental = 3;
    }
}
