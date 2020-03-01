import java.util.*;

public class Store extends AbstractStore
{
    public Store()
    {
        super();
    }

    public void processBooking(Customer customer, int day)
    {
        //pick random value for number of cars depending on customer type
        int minCars = customer.getMinCarsRentedPerRental();
        int maxCars = customer.getMaxCarsRentedPerRental();
        Random rand = new Random();
        int carsBooked = rand.nextInt(maxCars - minCars + 1) + minCars;

        //pick random number of days depending on customer type
        int minDuration = customer.getMinRentalDuration();
        int maxDuration = customer.getMaxRentalDuration();
        int bookingDuration = rand.nextInt(maxDuration - minDuration +1) + minDuration;

        int totalCost = 0;

        ArrayList<Car> cars = new ArrayList<Car>();
        ArrayList<Car> inventoryCars = inventory.getCars();
        //for each car booked, decide upon options
        for (int i =0; i<carsBooked; i++)
        {
            //pick a random car from the inventory
            Car car = inventoryCars.get(rand.nextInt(inventory.getNumCars()));

            //remove it from inventory
            inventory.removeCar(car);

            Car basecar = car;
            //add random number of car seats
            int numCarSeats = rand.nextInt(4);
            for(int j = 0; j<numCarSeats; j++)
            {
                car = new CarSeat(car, basecar);
            }
            //add radio and gps
            if (rand.nextInt(1)== 1)
                car = new Gps(car,basecar);

            if (rand.nextInt(1)== 1)
                car = new SatelliteRadio(car,basecar);

            //add to list
            cars.add(car);

            //increment total cost
            totalCost += car.getRent(bookingDuration);
        }

        //create rental record, add cars, customer details
        RentalRecord record = new RentalRecord(cars,customer,day,bookingDuration,totalCost);

        //add record to store and customer's list
        activeRentals.add(record);
        customer.rentalRecords.add(record);
    }

    public void processReturn(Customer customer)
    {
        //go through customer's records, remove records eligible for return
        // add them to completed rentals

        for (Iterator<RentalRecord> i = customer.getRentalRecords().iterator(); i.hasNext();)
        {
            RentalRecord element = i.next();
            if (element.getCompleted())
            {
                i.remove();
                activeRentals.remove(element);
                completedRentals.add(element);
            }
        }
    }

    public Customer getCustomerEligibleToRent(int customerIndex)
    {
        Customer cust = customers.get(Integer.valueOf(customerIndex));
        if (cust.getCanRent())
            return cust;
        else
            return null;
    }
}
