
public class Economy extends Car {

	
	public Economy(String licenseId,String name)
	{
		this.licenseId=licenseId;
		this.name = name;
		this.rent = 50;
	}

	@Override
	public int getRent() {
		
		return this.rent;
	}
}
