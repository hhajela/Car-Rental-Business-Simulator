
public abstract class Car {
protected int rent;
protected String licenseId;
protected String name;

	public Car(String licenseId, String name)
	{
		this.name=name;
		this.licenseId=licenseId;
	}
	public abstract int getRent(int Days);
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
