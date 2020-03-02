/**
 * Luxury class - subclass of Car class
 *
 */
public class Luxury extends Car {
	
	/**
	 * Constructor to create Luxury object 
	 * @param licenseId - licenseId of the car
	 * @param name - name of the car
	 */
	public Luxury(String licenseId,String name)
	{
		super(licenseId, name);
		this.rent = 250;
	}
	
	/**
	 *@param days - Number of days want to rent the car
	 *return the cost of total Rent based on number of days
	 */
	@Override
	public int getRent(int days) {
		return rent*days;
	}
	/**
	 *returns- name of the car
	 */
	public String getDescription()
	{
		return this.name;
	}
}
