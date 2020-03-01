import java.util.*;

public class Simulation implements Subject{

	private int day;
	private AbstractStore store;
	CustomerFactory cmFactory;
	CarFactory crFactory;
	private ArrayList<Customer> customer;
	private ArrayList<Car> car;
	private Logger log ;
	
	public Simulation(int day,CustomerFactory cmf, CarFactory crf, AbstractStore store)
	{
		this.day=day;
		this.cmFactory = cmf;
		this.crFactory = crf;
		this.store = store;
		this.customer = new ArrayList<Customer>();
		this.log = Logger.getInstance();
		
		
	}
	
	public void initialize(ArrayList<String> customer,ArrayList<String> carName, ArrayList<String> licName)
	{
		for(int i=0;i<customer.size();i++)
			{
			customer.add(this.cmFactory.createCustomer(customer.get(i),this.store.getInventory(),this));
			this.store.addCustomer(customer.get(i));
			}
		
		for(int i=0;i<carName.size();i++)
			this.store.getInventory().addCar(this.crFactory.createCar(licName.get(i),licName.get(i)));
		
		
		
		
		
	}
	public void changeDay()
	{
		this.day=this.day+1;
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
				this.store.processBooking(incomingCustomer);
				
			}
			
			changeDay();
		}
	}
	
	public void checkReturns()
	{
		for(int i=0;i<customer.size();i++)
		{
			if(customer.get(i).getCanReturn())
				this.store.processReturn(customer.get(i));
		}
		return;
	}
	
	public Customer getRandomCustomer()
	{
		Random random = new Random();
		
		return this.store.getCustomer(random.nextInt(12));
	}
	
	public void DailyStats()
	{
		this.log.print()
		this.log.print("Day "+this.day);
		this.log.print("Total Completed Rentals :"+this.store.getCompletedRental().size());
		this.log.print(this.store.getCompletedRental());
		this.log.print("Total Active Rentals :"+this.store.getActiveRental().size());
		this.log.print(this.store.getActiveRental());
		this.log.print(this.log.print("Total Active Rentals :"+this.store.getInventory().size()));
		this.log.print(this.log.print(this.store.getInventory());
		this.log.print("Today's Earnings:"+this.store.getDailyEarnings());
		
		
	}
}
