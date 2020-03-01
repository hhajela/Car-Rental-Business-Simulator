
public class CarFactory {

	public CarFactory()
	{
		
	}
	
	public Car createCar(String licenseID,String name)
	{
		if(licenseID.charAt(0)=='S'&&licenseID.charAt(1)=='U')
			return new Suv(licenseID,name);
		else if(licenseID.charAt(0)=='M')
			return new Minivan(licenseID,name);
		else if(licenseID.charAt(0)=='E')
			return new Economy(licenseID,name);
		else if(licenseID.charAt(0)=='L')
			return new Luxury(licenseID,name);
		else 
			return new Standard(licenseID,name);
			
	}
}
