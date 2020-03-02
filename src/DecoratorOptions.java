
/**
 * This is DecoratorOption class which is extending Car class.
 *This is Abstract Decorator class which will be extended by Decorator Concrete classes.
 */
public abstract class DecoratorOptions extends Car{

	/**
	 * Constructor to create DecoratorOption class 
	 * @param licenseId - licenseId of the car
	 * @param name - name of the car
	 */
    public DecoratorOptions(String licenseId, String name)
    {
    	super(licenseId,name);	
    }

    public abstract String getDescription();
}
