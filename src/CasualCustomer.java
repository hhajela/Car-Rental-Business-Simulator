public class CasualCustomer extends Customer
{
    public CasualCustomer(String name, Inventory inventory, Simulation simulation)
    {
        super(name, inventory, simulation);

        //init with specifics for casual customer
        type = "Casual";

        minCarsRentedPerRental = 1;
        maxCarsRentedPerRental = 1;

        minRentalDuration = 1;
        maxRentalDuration = 3;
    }
}
