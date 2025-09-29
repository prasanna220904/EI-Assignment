import java.util.*;

interface Observer {
    void update(String message);
}
class User implements Observer {
    private String name;
    public User(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
class NotificationService {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void notifyAllObservers(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}
