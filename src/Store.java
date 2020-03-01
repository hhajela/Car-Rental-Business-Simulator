import java.util.Random;

public class Store extends AbstractStore
{
    public void processBooking(Customer customer, int day)
    {
        //pick random value for number of cars depending on customer's maximum
        int minCars = customer.getMinCarsRentedPerRental();
        int maxCars = customer.getMaxCarsRentedPerRental();
        Random rand = new Random();
        int carsBooked = rand.nextInt(maxCars - minCars + 1) + minCars;


    }

    public void processReturn(Customer customer)
    {

    }

    public Customer getCustomerEligibleToReturn(int customerIndex)
    {
        Customer cust = customers.get(Integer.valueOf(customerIndex));
        if (cust.getCanRent())
            return cust;
        else
            return null;
    }
}
