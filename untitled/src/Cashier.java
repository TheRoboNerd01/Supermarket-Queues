
import java.util.ArrayDeque;
import java.util.Deque;

/*********************************************************************
 *
 * Class Name: Cashier
 * Author/s name: Javier Romero Sanchez, Raul Valentin Jorge, Ning Wang [*Note: order by surname]
 * Release/Creation date: 20/10/2017
 * Class description: contains the constructor and the methods(getters and setters) for Cashier objects.
 *
 ***********************************************************************/
public class Cashier<T> implements Comparable<Cashier<T>> {
    private String id;
    private int efficiency;
    private int workTime;
    private Deque<T> currentQueue;

    //Constructor
    public Cashier(String id, int efficiency, int totalTime) {
        this.id = "C" + id;
        this.efficiency = efficiency;
        this.workTime = totalTime;
        this.currentQueue = new ArrayDeque<>();
    }

    //Getters and setters
    public String getID() {
        return id;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public int getTotalTime() {
        return workTime;
    }

    public Deque<T> getCurrentQueue() {
        return currentQueue;
    }

    public void setTotalTime(int totalTime) {
        this.workTime = totalTime;
    }

    public void setCurrentQueue(Deque<T> currentQueue) {
        this.currentQueue = currentQueue;
    }

    //Compares the sizes of cashiers this. and o to get the least full one.
    @Override
    public int compareTo(Cashier<T> o) {
        return - this.currentQueue.size() + o.currentQueue.size();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {

        return "Cashier{" +
                "id='" + id + "'" +
                ", efficiency=" + efficiency +
                ", workTime=" + workTime +
                ", currentQueue=" + currentQueue.size() +
                '}';
    }
}