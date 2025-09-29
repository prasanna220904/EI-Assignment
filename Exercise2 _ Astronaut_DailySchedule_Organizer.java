import java.util.*;

class Task {
    String description, start, end, priority;
    public Task(String d, String s, String e, String p) {
        description=d; start=s; end=e; priority=p;
    }
    public String toString() {
        return start+" - "+end+": "+description+" ["+priority+"]";
    }
}

class TaskFactory {
    public static Task createTask(String d,String s,String e,String p) {
        return new Task(d,s,e,p);
    }
}

interface Observer { void notifyConflict(Task t, Task conflict); }
class ConflictNotifier implements Observer {
    public void notifyConflict(Task t, Task conflict) {
        System.out.println("Error: Task \""+t.description+
                           "\" conflicts with \""+conflict.description+"\"");
    }
}

class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private ScheduleManager() {}
    public static ScheduleManager getInstance() {
        if(instance==null) instance=new ScheduleManager();
        return instance;
    }
    public void addObserver(Observer o){ observers.add(o); }
    public void addTask(Task t){
        for(Task existing: tasks){
            if(overlaps(t, existing)){
                for(Observer o: observers) o.notifyConflict(t,existing);
                return;
            }
        }
        tasks.add(t);
        System.out.println("Task added successfully. No conflicts.");
    }
    private boolean overlaps(Task a, Task b){
        return !(a.end.compareTo(b.start)<=0 || a.start.compareTo(b.end)>=0);
    }
    public void viewTasks(){
        if(tasks.isEmpty()){ System.out.println("No tasks scheduled."); return; }
        tasks.sort(Comparator.comparing(t->t.start));
        tasks.forEach(System.out::println);
    }
    public void removeTask(String desc){
        if(tasks.removeIf(t->t.description.equalsIgnoreCase(desc)))
            System.out.println("Task removed successfully.");
        else
            System.out.println("Error: Task not found.");
    }
}

public class AstronautScheduler {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ScheduleManager sm=ScheduleManager.getInstance();
        sm.addObserver(new ConflictNotifier());

        while(true){
            System.out.println("\n1.Add Task  2.Remove Task  3.View Tasks  4.Exit");
            int choice=sc.nextInt(); sc.nextLine();
            switch(choice){
                case 1 -> {
                    System.out.print("Description: "); String d=sc.nextLine();
                    System.out.print("Start Time(HH:MM): "); String s=sc.nextLine();
                    System.out.print("End Time(HH:MM): "); String e=sc.nextLine();
                    System.out.print("Priority: "); String p=sc.nextLine();
                    sm.addTask(TaskFactory.createTask(d,s,e,p));
                }
                case 2 -> {
                    System.out.print("Enter description to remove: ");
                    sm.removeTask(sc.nextLine());
                }
                case 3 -> sm.viewTasks();
                case 4 -> { System.out.println("Exiting..."); return; }
            }
        }
    }
}
