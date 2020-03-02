
/**
 * Standard class - subclass of Car class
 *
 */
public class Standard extends Car {

	/**
	 * Constructor to create Standard object 
	 * @param licenseId - licenseId of the car
	 * @param name - name of the car
	 */
	public Standard(String licenseId,String name)
	{
		super(licenseId, name);
		this.rent = 60;
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
