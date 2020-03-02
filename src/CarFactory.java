
/**
 * CarFactory class which implements Simple Factory Design Pattern.
 *
 */
public class CarFactory {

	
	/**
	 * Constructor
	 */
	public CarFactory()
	{
		
	}
	
	/**
	 * Method which created car object of different type based on licenseID first character
	 * @param licenseID - licenseId of
	 * @param car - name of the car
	 * @return - return car object
	 */
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
