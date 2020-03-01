import java.util.ArrayList;

public class RentalRecord {

	private ArrayList<Car> car;
	private Customer customer;
	private int expiryDay;
	private boolean isCompleted;
	private int numberOfDays;
	private int totalRent;
	
	public RentalRecord(ArrayList<Car> car,Customer customer, int startDay,int numOfDays,int totalRent)
	{
		this.car = car;
		this.customer = customer;
		this.expiryDay=startDay+numOfDays;
		this.numberOfDays = numOfDays;
		this.isCompleted = false;
		this.totalRent = totalRent;
	}
	
	public int getExpiryDay()
	{
		return expiryDay;
	}
	
	public void setCompleted(boolean isCompleted)
	{
		this.isCompleted = isCompleted;
	}

	public boolean getCompleted()
	{
		return isCompleted;
	}

	public ArrayList<Car> getCars() { return car; }
	
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
