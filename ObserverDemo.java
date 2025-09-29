
import java.util.*;

interface Observer {
    void update(String message);
}

class ConcreteObserver implements Observer {
    private String name;
    public ConcreteObserver(String name) {
        this.name = name;
    }
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) {
        observers.add(o);
    }
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer o1 = new ConcreteObserver("Observer1");
        Observer o2 = new ConcreteObserver("Observer2");
        subject.addObserver(o1);
        subject.addObserver(o2);
        subject.notifyObservers("New event happened!");
    }
}
