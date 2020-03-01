
public class Suv extends Car {

	public Suv(String licenseId,String name)
	{
		this.licenseId=licenseId;
		this.name = name;
		this.rent = 140;
	}
	
	
	@Override
	public int getRent(int days) {
		return this.rent*days;
	}
	
	public String getDescription()
	{
		return this.name;
	
	}
}
