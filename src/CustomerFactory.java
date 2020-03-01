public class CustomerFactory
{
    public Customer createCustomer(String name, Inventory inventory, Simulation simulation)
    {
        if (name.startsWith("B"))
            return new BusinessCustomer(name, inventory, simulation);
        else if (name.startsWith("C"))
            return new CasualCustomer(name, inventory, simulation);
        else
            return new RegularCustomer(name, inventory, simulation);

    }
}
