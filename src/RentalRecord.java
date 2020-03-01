import java.util.ArrayList;

public class RentalRecord {

	private ArrayList<Car> car;
	private Customer customer;
	private int expiryDays;
	private boolean isCompleted;
	
	public RentalRecord(ArrayList<Car> car,Customer customer, int expiryDays)
	{
		this.car = car;
		this.customer = customer;
		this.expiryDays=expiryDays;
	}
	
	public int getExpiryDays()
	{
		return expiryDays;
	}
	
	public void markCompleted(boolean isCompleted)
	{
		this.isCompleted = isCompleted;
	}
	public boolean getCompleted()
	{
		return isCompleted;
	}
}
