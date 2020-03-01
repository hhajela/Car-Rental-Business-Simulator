
public class Standard extends Car {

	public Standard(String licenseId,String name)
	{
		super(licenseId, name);
		this.rent = 60;
	}
	
	@Override
	public int getRent(int days) {
		return rent*days;
	}
	
	public String getDescription()
	{
		return this.name;
	
	}
}
