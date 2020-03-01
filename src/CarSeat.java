
public class CarSeat extends DecoratorOptions{
	Car car; 
	Car baseCar;
	public CarSeat(Car car,Car baseCar )
	{
		this.car=car;
		this.baseCar = baseCar;
		this.rent=90;
	}
	
	public int getRent()
	{
		return rent+car.getRent();
	}
	
}
