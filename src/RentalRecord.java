import java.util.ArrayList;

public class RentalRecord {

	ArrayList<Car> car;
	Customer customer;
	int expiryDays;
	boolean isCompleted;
	
	public RentalRecord(ArrayList<Car> car,Customer customer, int expiryDays)
	{
		this.car = car;
		this.customer = customer;
		this.expiryDays=expiryDays;
	}
	
	public int GetExpiryDays()
	{
		return expiryDays;
	}
}
