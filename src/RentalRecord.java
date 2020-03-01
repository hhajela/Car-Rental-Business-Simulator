import java.util.ArrayList;

public class RentalRecord {

	private ArrayList<Car> car;
	private Customer customer;
	private int expiryDay;
	private boolean isCompleted;
	
	public RentalRecord(ArrayList<Car> car,Customer customer, int expiryDay)
	{
		this.car = car;
		this.customer = customer;
		this.expiryDay=expiryDay;
		this.isCompleted = false;
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
	
	public String toString()
	{
		String record;
	}
}
