
public class SatelliteRadio extends DecoratorOptions{

	Car car; 
	Car baseCar;
	public SatelliteRadio(Car car,Car baseCar)
	{
		this.car=car;
		this.baseCar=baseCar;
		rent=130;
	}
	
	public int getRent()
	{
		return rent+car.getRent();
	}
	}
