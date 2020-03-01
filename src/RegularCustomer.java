public class RegularCustomer extends Customer
{
    public RegularCustomer(String name, Inventory inventory, Simulation simulation)
    {
        super(name, inventory, simulation);
        minCarsRentedPerRental = 1;
        maxCarsRentedPerRental = 3;
    }
}
