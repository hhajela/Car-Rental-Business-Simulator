
public class CarSeat extends DecoratorOptions{
	Car car; 
	Car baseCar;
	public CarSeat(Car car,Car baseCar )
	{
		this.car=car;
		this.baseCar = baseCar;
		this.rent=90;
	}
	
	public int getRent(int Days)
	{
		return rent+car.getRent(Days);
	}
	
	public Car GetBase()
	{
		return baseCar;
	}
	
	public String getDescription()
	{
		return this.car.getDescription()+" "+this.getClass().getName();
	}
}
