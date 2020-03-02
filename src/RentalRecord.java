import java.util.ArrayList;

/**
 * Class to create Rental Record
 *
 */
public class RentalRecord {

	private ArrayList<Car> car;
	private Customer customer;
	private int expiryDay;
	private boolean isCompleted;
	private int numberOfDays;
	private int totalRent;
	
	/**
	 * Constructor to create RentalRecord Object
	 * @param car - ArrayList of all the cars object to rent
	 * @param customer - Customer who is renting the car
	 * @param startDay - The date they are renting the car
	 * @param numOfDays - Total duration of rental in days
	 * @param totalRent - Total cost of the rental transaciton
	 */
	public RentalRecord(ArrayList<Car> car,Customer customer, int startDay,int numOfDays,int totalRent)
	{
		this.car = car;
		this.customer = customer;
		this.expiryDay=startDay+numOfDays;
		this.numberOfDays = numOfDays;
		this.isCompleted = false;
		this.totalRent = totalRent;
	}
	
	// return the rental return day
	public int getExpiryDay()
	{
		return expiryDay;
	}
	
	//Mark the rental record as completed when customer has return the car
	public void setCompleted(boolean isCompleted)
	{
		this.isCompleted = isCompleted;
	}
	
	//Get the status of rental record completed or not
	public boolean getCompleted()
	{
		return isCompleted;
	}

	//Return the list of cars rented
	public ArrayList<Car> getCars() { return car; }
	
	//Override default toString function to present the record object in different format
	public String toString()
	{
		String record;
		record = "Customer Name : " + this.customer.getName()+"\tCustomer Type : "+this.customer.getType()+"\tCars Rented : ";
		if(this.isCompleted)
		{
			int i=0;
			for(;i<this.car.size()-1;i++)
			{
				record+=this.car.get(i).getDescription()+", ";
			}
			record+=this.car.get(i).getDescription()+ "\tRental Duration : ";
			record+=this.numberOfDays+" days\tTotal Rent : $"+this.totalRent;
				
		}
		else
		{
			int i=0;
			for(;i<this.car.size()-1;i++)
			{
				record+=this.car.get(i).getDescription()+" License ID "+this.car.get(i).getLicenseId()+", ";
			} 
			record+=this.car.get(i).getDescription()+' '+this.car.get(i).getLicenseId();
		}
		return record;
	}
}
