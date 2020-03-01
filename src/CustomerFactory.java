public class CustomerFactory
{
    public Customer createCustomer(String name)
    {
        if (name.startsWith("B"))
            return new BusinessCustomer(name);
        else if (name.startsWith("C"))
            return new CasualCustomer(name);
        else
            return new RegularCustomer(name);

    }
}
