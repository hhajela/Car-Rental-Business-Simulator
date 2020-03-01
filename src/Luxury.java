
public class Luxury extends Car {

	public Luxury(String licenseId,String name)
	{
		super(licenseId, name);
		this.rent = 250;
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
