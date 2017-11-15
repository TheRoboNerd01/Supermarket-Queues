/**********************************************************************
 *
 * Class Name: Customer
 * Author/s name: Javier Romero Sanchez, Raul Valentin Jorge, Ning Wang [*Note: order by surname]
 * Release/Creation date: 20/10/2017
 * Class description: contains the constructor and the methods(getters and setters) for Customer objects.
 *
 * ***********************************************************************/
public class Customer  {

    private int arriveTime;
    private int products;
    private boolean isElder;
    private String id;
    private int allTime; //All time required to process all the items of the customer.
    private int jumps; //If the customer is elder, it can jump some positions in the cashier queue.

    //Constructor
    public Customer(int arriveTime, int products, boolean isElder, String id) {
        this.arriveTime = arriveTime;
        this.products = products;
        this.isElder = isElder;
        this.id = (isElder ? "E" : "C") + id;
        this.jumps = 0;
    }

    //Getters and setters
    public String getId() {
        return id;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getProducts() {
        return products;
    }

    public int getAllTime() {
        return allTime;
    }

    public int getJumps() {
        return jumps;
    }

    public boolean isElder() {
        return isElder;
    }

    public void setAllTime(int efficiency) {
        this.allTime = this.products * efficiency;
    }

    public void setJumps(int value) {
        jumps = value;
    }

    public void flushAllTime(){
        this.allTime -= 1;
    }

    //@Override
    public String toString() {
        return "Customer{" +
                "id='" + id + "'" +
                ", arriveTime=" + arriveTime +
                ", products=" + products +
                ", isElder=" + isElder +
                '}';
    }
}
