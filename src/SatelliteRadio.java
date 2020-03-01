
public class SatelliteRadio extends DecoratorOptions{

	private Car car; 
	private Car baseCar;
	public SatelliteRadio(Car car,Car baseCar)
	{
		super(baseCar.getLicenseId(),baseCar.getDescription());
		this.car=car;
		this.baseCar=baseCar;
		rent=130;
	}
	
	public int getRent(int days)
	{
		return rent+car.getRent(days);
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
