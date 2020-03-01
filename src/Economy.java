
public class Economy extends Car {

	
	public Economy(String licenseId,String name)
	{
		super(licenseId,name);
		this.rent = 50;
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
