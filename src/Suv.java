
/**
 * Suv class - subclass of Car class
 *
 */
public class Suv extends Car {

	/**
	 * Constructor to create Suv object 
	 * @param licenseId - licenseId of the car
	 * @param name - name of the car
	 */
	public Suv(String licenseId,String name)
	{
		super(licenseId, name);
		this.rent = 140;
	}
	
	/**
	 *@param days - Number of days want to rent the car
	 *return the cost of total Rent based on number of days
	 */
	@Override
	public int getRent(int days) {
		return this.rent*days;
	}
	/**
	 *returns- name of the car
	 */
	public String getDescription()
	{
		return this.name;
	
	}
}
