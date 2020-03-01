public class RegularCustomer extends Customer
{
    public RegularCustomer(String name, Inventory inventory, Simulation simulation)
    {
        super(name, inventory, simulation);

        type = "Regular";

        minCarsRentedPerRental = 1;
        maxCarsRentedPerRental = 3;

        minRentalDuration = 3;
        maxRentalDuration = 5;
    }
}
