
public class CarSeat extends DecoratorOptions{
	private Car car; 
	private Car baseCar;
	public CarSeat(Car car,Car baseCar )
	{
		super(baseCar.getLicenseId(),baseCar.getDescription());
		this.car=car;
		this.baseCar = baseCar;
		this.rent=90;
	}
	
	public int getRent(int Days)
	{
		return rent+car.getRent(Days);
	}
	
	public Car getBase()
	{
		return baseCar;
	}
	
	public String getDescription()
	{
		return this.car.getDescription()+" "+this.getClass().getName();
	}
}
