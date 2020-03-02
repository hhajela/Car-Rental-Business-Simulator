
/*
 * Abstract Class Car which will be extended by different car types.
 * 
 * */

public abstract class Car {
protected int rent;
protected String licenseId;
protected String name;



	
	/**
	 * Constructor to create car class 
	 * @param licenseId - licenseId of the car
	 * @param name - name of the car
	 */
	public Car(String licenseId, String name)
	{
		this.name=name;
		this.licenseId=licenseId;
	}
	
	
	
	
	/**
	 * @param Days - Number of days to rent the car
	 * @return - total cost to rent the car
	 * 
	 */
	public abstract int getRent(int Days);
	
	
	/**
	 * @return -car name with all the option
	 */
	public abstract String getDescription();
	
	
	
	/**
	 * @return- licenseId of the car
	 */
	public String getLicenseId()
	{
		return licenseId;
	}

	
	/**
	 * @return - return this object
	 */
	public Car getBase() { return this; }
	
	
	/**
	 * Changes default toString
	 */
	public String toString()
	{
		return this.name+" "+this.licenseId;
	}
}
