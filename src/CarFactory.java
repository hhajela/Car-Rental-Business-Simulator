
public class CarFactory {

	public CarFactory()
	{
		
	}
	
	public Car createCar(String licenseID,String car)
	{
		if(licenseID.charAt(0)=='S'&&licenseID.charAt(1)=='U')
			return new Suv(licenseID,car);
		else if(licenseID.charAt(0)=='M')
			return new Minivan(licenseID,car);
		else if(licenseID.charAt(0)=='E')
			return new Economy(licenseID,car);
		else if(licenseID.charAt(0)=='L')
			return new Luxury(licenseID,car);
		else 
			return new Standard(licenseID,car);
			
	}
}
