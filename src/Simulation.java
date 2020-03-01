import java.io.IOException;
import java.util.*;

public class Simulation implements Subject{

	private int day;
	private AbstractStore store;
	CustomerFactory cmFactory;
	CarFactory crFactory;
	private ArrayList<Customer> customers;
	private ArrayList<Car> car;
	private Logger log ;
	
	public Simulation(int day,CustomerFactory cmf, CarFactory crf, AbstractStore store)
	{
		this.day=day;
		this.cmFactory = cmf;
		this.crFactory = crf;
		this.store = store;
		this.customers = new ArrayList<Customer>();
		this.log = Logger.getInstance();
		
		
	}
	
	public void initialize(ArrayList<String> customers,ArrayList<String> carName, ArrayList<String> licName)
	{
		for(int i=0;i<customers.size();i++)
			{
			this.customers.add(this.cmFactory.createCustomer(customers.get(i),this.store.getInventory(),this));
			this.store.addCustomer(this.customers.get(i));
			}
		
		for(int i=0;i<carName.size();i++)
			this.store.getInventory().addCar(this.crFactory.createCar(licName.get(i),carName.get(i)));
		
	}
	public void changeDay()
	{
		this.day++;
	}
	
	public int getDay()
	{
		return this.day;
	}
	
	public void runSimulation()
	{
		while(this.day<=35)
		{
			notifyObservers();
			checkReturns();
			
			Customer incomingCustomer;
			while((incomingCustomer = getRandomCustomer())!=null)
			{
				this.store.processBooking(incomingCustomer, this.day);
			}
			
			changeDay();
		}
	}
	
	public void checkReturns()
	{
		for(int i=0;i<customers.size();i++)
		{
			if(customers.get(i).getCanReturn())
				this.store.processReturn(customers.get(i));
		}
		return;
	}
	
	public Customer getRandomCustomer()
	{
		Random random = new Random();
		
		return this.store.getCustomerEligibleToRent(random.nextInt(12));
	}
	
	public void DailyStats()
	{
		try
		{
			
			this.log.print("Day "+this.day);
			this.log.print("Total Completed Rentals :"+this.store.getCompletedRentals().size());
			this.log.print(this.store.getCompletedRentals());
			this.log.print("Total Active Rentals :"+this.store.getActiveRentals().size());
			this.log.print(this.store.getActiveRentals());
			this.log.print("Total Cars in Inventory :"+this.store.getInventory().getNumCars());
			this.log.print(this.store.getInventory().getCars());
			this.log.print("Today's Earnings:"+this.store.getDailyEarnings());
		}
		catch (IOException e)
		{
			System.out.println("Failed to write daily stats to file, error " + e.toString());
		}
	}
}
