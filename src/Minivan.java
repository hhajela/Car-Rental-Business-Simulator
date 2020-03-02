
/**
 * Minivan class - subclass of Car class
 *
 */
public class Minivan extends Car {
	
	/**
	 * Constructor to create Minivan object 
	 * @param licenseId - licenseId of the car
	 * @param name - name of the car
	 */
	public Minivan(String licenseId,String name)
	{
		super(licenseId, name);
		this.rent = 120;
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
