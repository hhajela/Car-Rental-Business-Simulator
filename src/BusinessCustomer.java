public class BusinessCustomer extends Customer
{
    public BusinessCustomer(String customerName, Inventory inventory, Simulation simulation)
    {
        super(customerName, inventory, simulation);

        //init with specifics for business customer
        type = "Business";

        minCarsRentedPerRental = 3;
        maxCarsRentedPerRental = 3;

        minRentalDuration = 7;
        maxRentalDuration = 7;
    }
}
