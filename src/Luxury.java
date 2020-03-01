
public class Luxury extends Car {

	public Luxury(String licenseId,String name)
	{
		this.licenseId=licenseId;
		this.name = name;
		this.rent = 250;
	}

	@Override
	public int getRent() {
		return rent;
	}
}
