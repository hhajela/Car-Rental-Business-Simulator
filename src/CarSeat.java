
/**
 * CarSeat class which is derived class of Decorator options
 *Decorator pattern is used to get description and cost of car with different Option
 */
public class CarSeat extends DecoratorOptions{
 
	private Car car; 
	private Car baseCar;
	
	/**Constructor to create CarSeat class 
	 * @param car -The car object reference
	 * @param baseCar -base car reference which will be used to add this 
	 * 				    car back to inventory in case of return
	 */
	public CarSeat(Car car,Car baseCar )
	{
		super(baseCar.getLicenseId(),baseCar.getDescription());
		this.car=car;
		this.baseCar = baseCar;
		this.rent=90;
	}
	
	
	/**
	 *This method will cause the chain call of each option and car object 
	 *to get the total cost for the rent
	 */
	public int getRent(int Days)
	{
		return rent+car.getRent(Days);
	}
	
	//To return basecar without any option
	public Car getBase()
	{
		return baseCar;
	}
	
	/**
	 *This method will cause the chain call of each option and car object 
	 *to get the description.
	 */
	public String getDescription()
	{
		return this.car.getDescription()+" "+this.getClass().getName();
	}
}
