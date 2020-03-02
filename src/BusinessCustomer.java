

public class BusinessCustomer extends Customer
{
    public BusinessCustomer(String customerName, Inventory inventory, Simulation simulation)
    {
        super(customerName, inventory, simulation);

        type = "Business";

        minCarsRentedPerRental = 3;
        maxCarsRentedPerRental = 3;

        minRentalDuration = 7;
        maxRentalDuration = 7;
    }
}
