public class CasualCustomer extends Customer
{
    public CasualCustomer(String name, Inventory inventory, Simulation simulation)
    {
        super(name, inventory, simulation);
        minCarsRentedPerRental = 1;
        maxCarsRentedPerRental = 1;
    }
}
