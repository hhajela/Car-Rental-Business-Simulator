
public abstract class DecoratorOptions extends Car{

	
    public DecoratorOptions(String licenseId, String name)
{
super(licenseId,name);	
}

    public abstract String getDescription();
}
