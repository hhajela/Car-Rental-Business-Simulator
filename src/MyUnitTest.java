import org.junit.Test;

import java.io.IOException;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class MyUnitTest
{
    @Test
    public void customerCannotRentIfAlreadyMoreThanThreeCarsRentedTest() throws IOException {
        try
        {
            CustomerFactory cmf = new CustomerFactory();
            CarFactory crf = new CarFactory();
            AbstractStore store = new Store();
            Simulation s = new Simulation(1,cmf,crf,store);

            ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
                    "Toyota Camry",
                    "Hyundai Sonata",
                    "Honda Accord",
                    "Volkswagen Passat",
                    "Jeep Wrangler",
                    "Ford Explorer",
                    "Subaru Forester",
                    "Honda CR-V",
                    "Toyota RAV4",
                    "Nissa NV200",
                    "Chrysler Pacifica",
                    "Kia Sedona",
                    "Toyota Sienna",
                    "Honda Odyssey",
                    "Honda Fit",
                    "Toyota Yaris",
                    "Kia Rio",
                    "Nissan Versa",
                    "Chevrolet Spark",
                    "Lexus GS",
                    "Lexus ES",
                    "Volvo S90",
                    "Audi A6",
                    "BMW 5 Series"
            ));

            ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
                    "STD132",
                    "STD154",
                    "STD332",
                    "STD662",
                    "SUV194",
                    "SUV169",
                    "SUV134",
                    "SUV109",
                    "SUV123",
                    "MIN334",
                    "MIN324",
                    "MIN335",
                    "MIN234",
                    "MIN336",
                    "ECO365",
                    "ECO375",
                    "ECO265",
                    "ECO369",
                    "ECO315",
                    "LUX331",
                    "LUX109",
                    "LUX188",
                    "LUX151",
                    "LUX239"
            ));

            ArrayList<String> customers = new ArrayList<String>();

            s.initialize(customers,cars,licenses);

            //create a regular customer, add to store
            Customer c = cmf.createCustomer("Brian",store.getInventory(),s);
            store.addCustomer(c);

            //Customer can book more cars as long as he has upto three cars currently,
            //but after that he cannot since he has exceeded the per customer limit
            while (c.getCarsRented() <= 3) {
                assertEquals("Customer should be able to book if number of current cars booked <=3",true,c.getCanRent());
                store.processBooking(c, 1);
            }

            assertEquals("Customer shouldn't be able to book if number of current cars booked >3",false,c.getCanRent());
        }
        catch (AssertionError e)
        {
            Logger.getInstance().print("Assertion failed " + e.getMessage());
        }
    }

    @Test
    public void customerCannotRentIfInventoryHasInsufficientCarsTest() throws IOException {

        try
        {
            //init inventory with cars and remove enough to trigger the condition
            CustomerFactory cmf = new CustomerFactory();
            CarFactory crf = new CarFactory();
            AbstractStore store = new Store();
            Simulation s = new Simulation(1, cmf, crf, store);

            ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
                    "Toyota Camry",
                    "Hyundai Sonata",
                    "Honda Accord",
                    "Volkswagen Passat"

            ));

            ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
                    "STD132",
                    "STD154",
                    "STD332",
                    "STD662"
            ));

            ArrayList<String> customers = new ArrayList<String>();

            s.initialize(customers, cars, licenses);

            Customer businessCust = cmf.createCustomer("Brian", store.getInventory(), s);
            store.addCustomer(businessCust);

            Customer regularCust = cmf.createCustomer("Ryan", store.getInventory(), s);
            store.addCustomer(regularCust);

            Customer casualCust = cmf.createCustomer("Casey", store.getInventory(), s);
            store.addCustomer(casualCust);

            assertEquals("Should be able to book if inventory has sufficient cars per type",true,businessCust.getCanRent());
            store.processBooking(businessCust, 1);

            //only 1 car left now
            assertEquals("Should not be able to book if inventory has insufficient cars per type",false,businessCust.getCanRent());

            //other customer types should still be able to book
            assertEquals("Should be able to book if inventory has sufficient cars per type",true,casualCust.getCanRent());
            assertEquals("Should be able to book if inventory has sufficient cars per type", true, regularCust.getCanRent());
        }
        catch (AssertionError e)
        {
            Logger.getInstance().print("Assertion failed " + e.getMessage());
        }

    }

    @Test
    public void bookedCarDoesNotExceedOptionLimitsTest() throws IOException {
        try
        {
            //try 10 times to do a fresh booking
            //make sure that the booking follows the conditions
            for (int i =0; i<10; i++)
            {
                CustomerFactory cmf = new CustomerFactory();
                CarFactory crf = new CarFactory();
                AbstractStore store = new Store();
                Simulation s = new Simulation(1, cmf, crf, store);

                ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
                        "Toyota Camry",
                        "Hyundai Sonata",
                        "Honda Accord",
                        "Volkswagen Passat"

                ));

                ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
                        "STD132",
                        "STD154",
                        "STD332",
                        "STD662"
                ));

                ArrayList<String> customers = new ArrayList<String>();

                s.initialize(customers, cars, licenses);

                Customer casualCust = cmf.createCustomer("Casey", store.getInventory(), s);
                store.addCustomer(casualCust);

                //book car and check option limits are obeyed
                store.processBooking(casualCust,1);
                Car bookedCar = casualCust.getRentalRecords().iterator().next().getCars().get(0);
                String desc  = bookedCar.getDescription();

                String strFind = "CarSeat";
                int count = 0, fromIndex = 0;

                while ((fromIndex = desc.indexOf(strFind, fromIndex)) != -1 ){
                    count++;
                    fromIndex++;

                }
                assertEquals("car seats between 0 and 4", count<=4, true);

                strFind = "SatelliteRadio";
                count = 0;
                fromIndex = 0;

                while ((fromIndex = desc.indexOf(strFind, fromIndex)) != -1 ){
                    count++;
                    fromIndex++;

                }
                assertEquals("satellite radio between 0 and 1", count <= 1, true);

                strFind = "Gps";
                count = 0;
                fromIndex = 0;

                while ((fromIndex = desc.indexOf(strFind, fromIndex)) != -1 ){
                    count++;
                    fromIndex++;

                }
                assertEquals("gps between 0 and 1", count<=1, true);

                /*
                int numCarSeats = 0;
                int numRadio = 0;
                int numGps = 0;
                String className = bookedCar.getClass().getName();
                while(className.equals("Gps") || className.equals("SatelliteRadio") || className.equals("CarSeat"))
                {
                    if (className.equals("Gps")) {
                        numGps++;
                    }
                    else if (className.equals()) {
                        numRadio++;
                    }
                    else {
                        numCarSeats++;
                    }

                    bookedCar = bookedCar.get
                }*/
            }
        }
        catch (AssertionError e)
        {
            Logger.getInstance().print("Assertion failed " + e.getMessage());
        }
    }

    @Test
    public void carsBookedPerBookingWithinBoundsTest() throws IOException
    {
        try
        {
            //do 10 bookings for each type of customer in random runs
            //check per customer per rental car limit obeyed
            for (int i = 0; i < 10; i++)
            {
                CustomerFactory cmf = new CustomerFactory();
                CarFactory crf = new CarFactory();
                AbstractStore store = new Store();
                Simulation s = new Simulation(1, cmf, crf, store);

                ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
                        "Toyota Camry",
                        "Hyundai Sonata",
                        "Honda Accord",
                        "Volkswagen Passat",
                        "Jeep Wrangler",
                        "Ford Explorer",
                        "Subaru Forester",
                        "Honda CR-V",
                        "Toyota RAV4"

                ));

                ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
                        "STD132",
                        "STD154",
                        "STD332",
                        "STD662",
                        "SUV194",
                        "SUV169",
                        "SUV134",
                        "SUV109",
                        "SUV123"
                ));

                ArrayList<String> customers = new ArrayList<String>();

                s.initialize(customers, cars, licenses);

                Customer businessCust = cmf.createCustomer("Brian", store.getInventory(), s);
                store.addCustomer(businessCust);

                Customer regularCust = cmf.createCustomer("Ryan", store.getInventory(), s);
                store.addCustomer(regularCust);

                Customer casualCust = cmf.createCustomer("Casey", store.getInventory(), s);
                store.addCustomer(casualCust);

                store.processBooking(businessCust,1);
                int numCarsBooked = businessCust.getRentalRecords().iterator().next().getCars().size();
                assertEquals("Business customer always books 3 cars", numCarsBooked, 3);

                store.processBooking(casualCust,1);
                numCarsBooked = casualCust.getRentalRecords().iterator().next().getCars().size();
                assertEquals("Casual customer always books 1 car", numCarsBooked, 1);

                store.processBooking(regularCust,1);
                numCarsBooked = regularCust.getRentalRecords().iterator().next().getCars().size();
                assertEquals("Regular customer always books b/w 1 and 3 cars", numCarsBooked <= regularCust.getMaxCarsRentedPerRental() && numCarsBooked >= regularCust.minCarsRentedPerRental, true);
            }
        }
        catch (AssertionError e)
        {
            Logger.getInstance().print("Assertion failed " + e.getMessage());
        }
    }

    @Test
    public void bookingDurationWithinBoundsTest() throws IOException
    {
        try
        {
            //do 10 bookings for each type of customer in random runs
            //check per customer rental durations obeyed
            for (int i = 0; i < 10; i++)
            {
                CustomerFactory cmf = new CustomerFactory();
                CarFactory crf = new CarFactory();
                AbstractStore store = new Store();
                Simulation s = new Simulation(1, cmf, crf, store);

                ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
                        "Toyota Camry",
                        "Hyundai Sonata",
                        "Honda Accord",
                        "Volkswagen Passat",
                        "Jeep Wrangler",
                        "Ford Explorer",
                        "Subaru Forester",
                        "Honda CR-V",
                        "Toyota RAV4"

                ));

                ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
                        "STD132",
                        "STD154",
                        "STD332",
                        "STD662",
                        "SUV194",
                        "SUV169",
                        "SUV134",
                        "SUV109",
                        "SUV123"
                ));

                ArrayList<String> customers = new ArrayList<String>();

                s.initialize(customers, cars, licenses);

                Customer businessCust = cmf.createCustomer("Brian", store.getInventory(), s);
                store.addCustomer(businessCust);

                Customer regularCust = cmf.createCustomer("Ryan", store.getInventory(), s);
                store.addCustomer(regularCust);

                Customer casualCust = cmf.createCustomer("Casey", store.getInventory(), s);
                store.addCustomer(casualCust);

                store.processBooking(businessCust,1);
                int rentalDuration = businessCust.getRentalRecords().iterator().next().getExpiryDay()-1;
                assertEquals("Business customer always rents for 7 days", rentalDuration, 7);

                store.processBooking(casualCust,1);
                rentalDuration = casualCust.getRentalRecords().iterator().next().getExpiryDay()-1;
                assertEquals("Casual customer always rents for b/w 1 and 3 days", rentalDuration<=3 && rentalDuration >=1, true);

                store.processBooking(regularCust,1);
                rentalDuration = regularCust.getRentalRecords().iterator().next().getExpiryDay()-1;
                assertEquals("Regular customer always books for b/w 3 and 5 days", rentalDuration<=5 && rentalDuration>=3, true);
            }
        }
        catch (AssertionError e)
        {
            Logger.getInstance().print("Assertion failed " + e.getMessage());
        }
    }

}
