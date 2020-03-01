
public abstract class Car {
protected int rent;
protected String licenseId;
protected String name;

	public Car()
	{
		
	}
	public abstract int getRent();
	public abstract String getDescription();
	
	public String getLicenseId()
	{
		return licenseId;
	}
	
	public String toString()
	{
		return this.name+" "+this.licenseId;
	}
}
