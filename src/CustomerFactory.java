//implementation of Simple Factory pattern
//use this to generate different types of customer objects
public class CustomerFactory
{
    public Customer createCustomer(String name, Inventory inventory, Simulation simulation)
    {
        //return appropriate customer object depending on what letter the name starts with
        // B for Business
        // C for Casual
        // R for Regular
        if (name.startsWith("B"))
            return new BusinessCustomer(name, inventory, simulation);
        else if (name.startsWith("C"))
            return new CasualCustomer(name, inventory, simulation);
        else
            return new RegularCustomer(name, inventory, simulation);

    }
}
