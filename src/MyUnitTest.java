import org.junit.Test;

import java.io.IOException;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

            Logger.getInstance().print("Test case customerCannotRentIfAlreadyMoreThanThreeCarsRentedTest passed");
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

            Logger.getInstance().print("Test case customerCannotRentIfInventoryHasInsufficientCarsTest passed");
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

            }
            Logger.getInstance().print("Test case bookedCarDoesNotExceedOptionLimitsTest passed");
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
            Logger.getInstance().print("Test case carsBookedPerBookingWithinBoundsTest passed");
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
            Logger.getInstance().print("Test Case bookingDurationWithinBoundsTest passed");
        }
        catch (AssertionError e)
        {
            Logger.getInstance().print("Assertion failed " + e.getMessage());
        }
    }
    @Test
    public void checkTotalCostCalculated() throws IOException
    {
		Logger log = Logger.getInstance();
		
		try
		{
			
			CarFactory crf = new CarFactory();
			 ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
		             "Toyota Camry",
		             "Jeep Wrangler",
		             "Nissa NV200",
		             "Honda Fit",
		             "Lexus GS"
		       
		     ));
		
		     ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
		             "STD132",
		             "SUV194",
		             "MIN334",
		             "ECO365",
		             "LUX331"
		             
		     ));
		     
		     ArrayList<Car> baseCarObjects= new ArrayList();
		     ArrayList<Car> carObjects = new ArrayList();
		     for(int i=0;i<5;i++)
		     {
		    	 baseCarObjects.add(crf.createCar(licenses.get(i), cars.get(i)));
		    	 carObjects.add(baseCarObjects.get(i));
		     }
		     
		     Car Localcar = carObjects.get(0);
		     Localcar = new SatelliteRadio(Localcar,baseCarObjects.get(0));
		     carObjects.set(0, Localcar);
		     
		     Localcar = carObjects.get(1);
		     Localcar = new CarSeat(Localcar,baseCarObjects.get(1));
		     Localcar = new CarSeat(Localcar,baseCarObjects.get(1));
		     Localcar = new CarSeat(Localcar,baseCarObjects.get(1));
		     Localcar = new Gps(Localcar,baseCarObjects.get(1));
		     carObjects.set(1, Localcar);
		     
		     Localcar = carObjects.get(2);
		     Localcar = new Gps(Localcar,baseCarObjects.get(2));
		     Localcar = new SatelliteRadio(Localcar,baseCarObjects.get(2));
		     carObjects.set(2, Localcar);
		     
		     Localcar = carObjects.get(3);
		     Localcar = new SatelliteRadio(Localcar,baseCarObjects.get(3));
		     Localcar = new CarSeat(Localcar,baseCarObjects.get(3));
		     carObjects.set(3, Localcar);
		     
		     Localcar = carObjects.get(4);
		     Localcar = new Gps(Localcar,baseCarObjects.get(4));
		     carObjects.set(4, Localcar);
		     
		     ArrayList<Integer> assertValues = new ArrayList(Arrays.asList(190,620,560,420,1320));
		     String fail_message;
		     for(int i=0;i<5;i++)
		     {
		    	 fail_message = "Assertion Failed for checkTotalCostCalculated:Car "+cars.get(i); 
		    	 assertEquals(fail_message,assertValues.get(i),Integer.valueOf(carObjects.get(i).getRent(i+1)));
		    	 
		    	 
		    	 
		     }
		     log.print("Test case checkTotalCostCalculated passed");
		     return;
		     
		}
		catch(AssertionError e)
		{
			log.print(e.getMessage());
		} 
			
	}
	
	@Test
    public void checkInventoryChangeForNewBooking() throws IOException
    {	
		Logger log=Logger.getInstance();
		try 
		{
			CarFactory crf = new CarFactory();
			ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
		             "Toyota Camry",
		             "Jeep Wrangler",
		             "Nissa NV200",
		             "Honda Fit",
		             "Lexus GS"
		       
		     ));
		
		     ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
		             "STD132",
		             "SUV194",
		             "MIN334",
		             "ECO365",
		             "LUX331"
		             
		     ));
		    String customerName = "Brian";
		    CustomerFactory cmf = new CustomerFactory();
	        AbstractStore store = new Store();
	        Simulation s = new Simulation(1,cmf,crf,store);
	      
	        	Customer customer = cmf.createCustomer(customerName,store.getInventory(),s);  
	        	store.addCustomer(customer);
			
		
	        	for(int i=0;i<cars.size();i++)
	        		store.getInventory().addCar(crf.createCar(licenses.get(i),cars.get(i)));
	        		
	        	store.processBooking(customer,1);
	        	int expectedInventory =5- customer.getCarsRented();
	        	
	        	String fail_message = "Test Case Failed:checkInventoryChangeForNewBooking() ";
	        	assertEquals(fail_message,expectedInventory,store.getInventory().getNumCars());
	        	
	        	log.print("Test case checkInventoryChangeForNewBooking passed");
		}
		catch(AssertionError e)
		{
			log.print(e.getMessage());
		}
	        			
    }
	
	@Test
    public void checkDescriptionDecorator() throws IOException
    {
		Logger log = Logger.getInstance();
		
		try
		{
			
			CarFactory crf = new CarFactory();
			 
		     String car="Toyota Camry";
		
		     String license="STD132";
		     
		     Car baseCarObjects= crf.createCar(license, car);
		     Car carObjects = baseCarObjects;
		     
		     carObjects = new CarSeat(carObjects, baseCarObjects);
		     carObjects = new CarSeat(carObjects, baseCarObjects);
		     carObjects = new CarSeat(carObjects, baseCarObjects);
		     carObjects = new Gps(carObjects, baseCarObjects);
		     String expectedDescription  = "Toyota Camry CarSeat CarSeat CarSeat Gps";
		     String fail_message = "TestCase Failed:checkDescriptionDecorator() ";
		     assertEquals(fail_message, expectedDescription,carObjects.getDescription());
		     log.print("Test case checkDescriptionDecorator passed");
		     
		     
		}
		catch(AssertionError e)
		{
			log.print(e.getMessage());
		}
	
	
   }
	@Test
	public void checkReturnsWhenexpected() throws IOException
	{
		Logger log=Logger.getInstance();
		try 
		{
			CarFactory crf = new CarFactory();
			ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
		             "Toyota Camry",
		             "Jeep Wrangler",
		             "Nissa NV200",
		             "Honda Fit",
		             "Lexus GS"
		       
		     ));
		
		     ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
		             "STD132",
		             "SUV194",
		             "MIN334",
		             "ECO365",
		             "LUX331"
		             
		     ));
		    String customerName = "Brian";
		    CustomerFactory cmf = new CustomerFactory();
	        AbstractStore store = new Store();
	        Simulation s = new Simulation(1,cmf,crf,store);
	      
	        	Customer customer = cmf.createCustomer(customerName,store.getInventory(),s);  
	        	store.addCustomer(customer);
			
		
	        	for(int i=0;i<cars.size();i++)
	        		store.getInventory().addCar(crf.createCar(licenses.get(i),cars.get(i)));
	        		
	        	store.processBooking(customer,1);
	        	int returnDay = customer.getRentalRecords().iterator().next().getExpiryDay();
	        	
	        	for(int i=1;i<returnDay;i++)
	        		s.changeDay();
	        	
	        	String fail_message = "TestCase FAILED:checkReturnsWhenexpected()- Customer should return";
	        	assertTrue(fail_message,customer.getCanReturn());
	        	
	        	store.processReturn(customer);
	        	fail_message = "TestCase FAILED:checkReturnsWhenexpected()- Return did not process, Inventory";
	        	assertEquals(fail_message, 5,store.getInventory().getNumCars());
	        	
	        	log.print("Test case checkDescriptionDecorator passed");
	        	
	        	
		}
		catch(AssertionError e)
		{
			log.print(e.getMessage());
		}
	}
	
	@Test
	public void checkActiveCompletedRentals() throws IOException
	{
		Logger log=Logger.getInstance();
		try 
		{
			CarFactory crf = new CarFactory();
			ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
		             "Toyota Camry",
		             "Jeep Wrangler",
		             "Nissa NV200",
		             "Honda Fit",
		             "Lexus GS"
		       
		     ));
		
		     ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
		             "STD132",
		             "SUV194",
		             "MIN334",
		             "ECO365",
		             "LUX331"
		             
		     ));
		    String customerName = "Brian";
		    CustomerFactory cmf = new CustomerFactory();
	        AbstractStore store = new Store();
	        Simulation s = new Simulation(1,cmf,crf,store);
	      
	        	Customer customer = cmf.createCustomer(customerName,store.getInventory(),s);  
	        	store.addCustomer(customer);
			
		
	        	for(int i=0;i<cars.size();i++)
	        		store.getInventory().addCar(crf.createCar(licenses.get(i),cars.get(i)));
	        		
	        	store.processBooking(customer,1);
	        	
	        	
	        	String fail_message = "TestCase checkActiveCompletedRentals()- ActiveRentals not updated after booking";
	        	assertEquals(fail_message,1,store.getActiveRentals().size());
	        	
	        	
	        	int returnDay = customer.getRentalRecords().iterator().next().getExpiryDay();
	        	
	        	for(int i=1;i<returnDay;i++)
	        		s.changeDay();
	        	
	        	
	        	store.processReturn(customer);
	        	
	        	fail_message = "TestCase checkActiveCompletedRentals()- ActiveRentals not updated after return";
	        	assertEquals(fail_message,0,store.getActiveRentals().size());
	        	
	        	fail_message = "TestCase checkActiveCompletedRentals()- CompletedRentals not updated";
	        	assertEquals(fail_message,1,store.getCompletedRentals().size());
	        	
	        	
	        	log.print("Test case checkActiveCompletedRentals passed");
	        	
	        	
		}
		catch(AssertionError e)
		{
			log.print(e.getMessage());
		}
	}


}
