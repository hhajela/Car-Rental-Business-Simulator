import java.io.IOException;
import java.util.*;


/**
 * This class runs this simulation for the store.
 * This is implementing the Subject interface
 * Observer pattern is used to update Customers.
 */
public class Simulation implements Subject{

	private int day;
	private AbstractStore store;
	private CustomerFactory cmFactory;
	private CarFactory crFactory;
	private ArrayList<Customer> customers;
	private Logger log ;
	
	/**Constructor to create Simulation object
	 * @param day - day from which simulation need to run
	 * @param cmf - CustomerFactory object to create customers.
	 * @param crf - CarFactory object to create cars.
	 * @param store - Store object
	 */
	public Simulation(int day,CustomerFactory cmf, CarFactory crf, AbstractStore store)
	{
		this.day=day;
		this.cmFactory = cmf;
		this.crFactory = crf;
		this.store = store;
		this.customers = new ArrayList<Customer>();
		this.log = Logger.getInstance();
		
		
	}
	
	/**
	 * @param customers - ArrayList of Customer Customer names
	 * @param carName - ArrayList of Customer Car names
	 * @param licName - ArrayList of Customer license names
	 */
	public void initialize(ArrayList<String> customers,ArrayList<String> carName, ArrayList<String> licName)
	{
		//Creating customer object to run the simulation
		for(int i=0;i<customers.size();i++)
			{
			this.customers.add(this.cmFactory.createCustomer(customers.get(i),this.store.getInventory(),this));
			this.store.addCustomer(this.customers.get(i));
			}
		
		//Adding 24 cars to inventory
		for(int i=0;i<carName.size();i++)
			this.store.getInventory().addCar(this.crFactory.createCar(licName.get(i),carName.get(i)));
		
	}
	//To change day
	public void changeDay()
	{
		this.day++;
		// This notify customer that day is changed so that they can check if they want to return rented car
		notifyObservers();
	}
	
	//Return the present day valye
	public int getDay()
	{
		return this.day;
		
	}
	
	//This method is to run simulation of store for 35 days
	public void runSimulation()
	{
		while(this.day<=35)
		{
			//To reset the earning as new day started.
			store.setDailyEarnings(0);
			//Function to take the returns from the customer.
			checkReturns();
			
			Customer incomingCustomer;
			//Loop which will keep running till getRandomCustomer return Customer object
			while((incomingCustomer = getRandomCustomer())!=null)
			{
				//To book rental for the customer
				this.store.processBooking(incomingCustomer, this.day);
			}
			//Output Stats as the day ends
			logDailyStats();
			//Change the day
			changeDay();
		}
		logFinalStats();
	}
	
	//This function check value of canReturn to find if there is any customer ready to return their rented car
	public void checkReturns()
	{
		for(int i=0;i<customers.size();i++)
		{
			 
			if(customers.get(i).getCanReturn())
				this.store.processReturn(customers.get(i));
		}
		return;
	}
	
	//This function generator random number from 0 to 11 to get the customer from the list
	public Customer getRandomCustomer()
	{
		Random random = new Random();
		
		return this.store.getCustomerEligibleToRent(random.nextInt(12));
	}
	
	//Printing Stats for end of the day
	public void logDailyStats()
	{
		try
		{
			
			this.log.print("\nDay \n"+this.day);
			this.log.print("Total Completed Rentals : "+this.store.getCompletedRentals().size() + "\n");
			this.log.print(this.store.getCompletedRentals());
			this.log.print("\nTotal Active Rentals : "+this.store.getActiveRentals().size() + "\n");
			this.log.print(this.store.getActiveRentals());
			this.log.print("\nTotal Cars in Inventory : "+this.store.getInventory().getNumCars() + "\n");
			this.log.print(this.store.getInventory().getCars());
			this.log.print("\nToday's Earnings : "+this.store.getDailyEarnings());
			this.log.print("--------------------------------------------------------------------------------------------");
		}
		catch (IOException e)
		{
			System.out.println("Failed to write daily stats to file, error " + e.toString());
		}
	}
	
	//Method which prints the final stats at the end of Simulations
	public void logFinalStats()
	{
		try
		{
			this.log.print("Final Stats\n");
			this.log.print("Total Completed Rentals : "+this.store.getCompletedRentals().size() + "\n");
			this.log.print("Total Completed Rentals by Business Customers : "+this.store.getBusinessRentals() + "\n");
			this.log.print("Total Completed Rentals by Casual Customers : "+this.store.getCasualRentals() + "\n");
			this.log.print("Total Completed Rentals by Regular Customers : "+this.store.getRegularRentals() + "\n");
			this.log.print("Total Earnings : "+this.store.getTotalEarnings()+'\n');
		}
		catch (IOException e)
		{
			System.out.println("Failed to write daily stats to file, error " + e.toString());
		}
	}
}
