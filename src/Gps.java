
public class Gps extends DecoratorOptions{

	Car car; 
	Car baseCar;
	public Gps(Car car,Car baseCar)
	{
		this.car=car;
		this.baseCar =baseCar;
		rent=70;
	}
	
	public int getRent()
	{
		return rent+car.getRent();
	}
}
