
public class Minivan extends Car {

	public Minivan(String licenseId,String name)
	{
		super(licenseId, name);
		this.rent = 120;
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
