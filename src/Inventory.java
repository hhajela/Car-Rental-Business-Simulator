import java.util.*;

public class Inventory implements  Subject
{
    private ArrayList<Car> cars;

    public Inventory()
    {
        cars = new ArrayList<Car>();
    }

    public void addCar(Car car)
    {
        //observer pattern, notify observer when inventory count changes
        cars.add(car);
        notifyObservers();
    }

    public void removeCar(Car car)
    {
        //observer pattern, notify observer when inventory count changes
        cars.remove(car);
        notifyObservers();
    }

    public int getNumCars() { return cars.size(); }

    public ArrayList<Car> getCars() { return cars; }
}
