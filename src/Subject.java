import java.util.*;

public interface Subject
{
    ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    default void notifyObservers()
    {
        for (MyObserver o: observers)
        {
            o.update();
        }
    }

    default void registerObserver(MyObserver observer)
    {
        observers.add(observer);
    }

    default void removeObserver(MyObserver observer)
    {
        observers.remove(observer);
    }
}
