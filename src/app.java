import java.util.*;

public class app
{
    public static void main(String[] args)
    {
        CustomerFactory cmf = new CustomerFactory();
        CarFactory crf = new CarFactory();
        AbstractStore store = new Store();
        Simulation s = new Simulation(1,cmf,crf,store);

        ArrayList<String> customers = new ArrayList<String>(Arrays.asList(
                "Brian",
                "Brock",
                "Casey",
                "Cate",
                "Robert",
                "Ryan",
                "Carlos",
                "Chris",
                "Riley",
                "Richard",
                "Caleb",
                "Bruce"
        ));

        ArrayList<String> cars = new ArrayList<String>(Arrays.asList(
                "Toyota Camry",
                "Hyundai Sonata",
                "Honda Accord",
                "Volkswagen Passat",
                "Jeep Wrangler",
                "Ford Explorer",
                "Subaru Forester",
                "Honda CR-V",
                "Toyota RAV4",
                "Nissa NV200",
                "Chrysler Pacifica",
                "Kia Sedona",
                "Toyota Sienna",
                "Honda Odyssey",
                "Honda Fit",
                "Toyota Yaris",
                "Kia Rio",
                "Nissan Versa",
                "Chevrolet Spark",
                "Lexus GS",
                "Lexus ES",
                "Volvo S90",
                "Audi A6",
                "BMW 5 Series"
        ));

        ArrayList<String> licenses = new ArrayList<String>(Arrays.asList(
                "STD132",
                "STD154",
                "STD332",
                "STD662",
                "SUV194",
                "SUV169",
                "SUV134",
                "SUV109",
                "SUV123",
                "MIN334",
                "MIN324",
                "MIN335",
                "MIN234",
                "MIN336",
                "ECO365",
                "ECO375",
                "ECO265",
                "ECO369",
                "ECO315",
                "LUX331",
                "LUX109",
                "LUX188",
                "LUX151",
                "LUX239"
        ));

        s.initialize(customers,cars,licenses);
        s.runSimulation();
    }
}
