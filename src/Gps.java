
public class Gps extends DecoratorOptions{

	private Car car; 
	private Car baseCar;
	public Gps(Car car,Car baseCar)
	{
		this.car=car;
		this.baseCar =baseCar;
		rent=70;
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
