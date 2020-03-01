import java.util.*;

public class Inventory implements  Subject
{
    private ArrayList<Car> cars;

    public void addCar(Car car)
    {
        cars.add(car);
        notifyObservers();
    }

    public void removeCar(Car car)
    {
        cars.remove(car);
        notifyObservers();
    }

    public int getNumCars() { return cars.size(); }
}
