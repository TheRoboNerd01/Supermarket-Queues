import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Deque;

/*********************************************************************
 *
 * Class Name: ProjectQueue.
 * Author/s name: Javier Romero Sanchez, Raul Valentin Jorge, Ning Wang [*Note: order by surname]
 * Release/Creation date: 30/10/2017
 * Class description: this class implements the project about Queues.
 *
 ***********************************************************************/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProjectQueue {

    /*********************************************************************
     *
     * Method name: sortPriorityQueue.
     *
     * Description of the Method: this method order all the cashiers by the shortest queue.
     *
     * Calling arguments:
     * 	Queue cashiers; the queue of cashiers
     *
     * Return value: the queue of cashiers ordered.
     *
     *********************************************************************/
    public static Queue<Cashier> sortPriorityQueue(Queue<Cashier> cashiers){
        Queue<Cashier> sortedQueue = new PriorityQueue<>(Collections.reverseOrder());

        //Transforms the original cashier queue of customers in a Priority one.
        while (!cashiers.isEmpty()){
            sortedQueue.add(cashiers.remove());
        }
        return sortedQueue;
    }

    /*********************************************************************
     *
     * Method name: publishCashiersStatus.
     *
     * Description of the Method: it will print the status of the cashiers.
     *
     * Calling arguments:
     * 	Queue cashiers; the queue of cashiers.
     *
     *********************************************************************/
    public static void publishCashiersStatus(Queue<Cashier> cashiers){
        System.out.println("\nDisplaying the status of the cashiers each:");
        for(Cashier<Customer> cashier:cashiers){
            System.out.println(cashier.toString());
        }
    }

    /*********************************************************************
     *
     * Method name: isAllFinished.
     *
     * Description of the Method: checks if there are no customers waiting on the cashiers.
     *
     * Calling arguments:
     * 	Queue cashiers; the queue of cashiers
     *
     * Return value: a boolean indicating if all the queues of the cashiers are empty or not
     *
     *********************************************************************/
    public static boolean isAllFinished(Queue<Cashier> cashiers) {
        for (Cashier cashier : cashiers) {
            if(cashier.getCurrentQueue().size() != 0) {
                return false;
            }
        }
        return true;
    }

    /*********************************************************************
     *
     * Method name: intoQue.
     *
     * Description of the Method: inserts the new customers in the cashiers queue of customers.
     *
     * Calling arguments:
     * 	Customer customers; a customer object.
     * 	Queue cashiers; the queue of cashiers.
     *
     * Return value: the queue of the cashier with less people and with the new customer added.
     *
     *********************************************************************/
    public static Queue<Cashier> intoQue(Customer c, Queue<Cashier> cashiers){
        Deque<Customer> currentQueue;

        cashiers = sortPriorityQueue(cashiers);
        c.setAllTime(cashiers.peek().getEfficiency());

        //once we have ordered the queue, we pick the one at the front, which is the shortest one
        currentQueue = cashiers.peek().getCurrentQueue();
        if(c.isElder()) {
            int jumps = (int) (Math.random()*5);
            c.setJumps(jumps);
            int position = currentQueue.size() - jumps;
            if(position < 0) {
                currentQueue.addFirst(c);
            }else {
                changePosition(c,currentQueue, position);
            }
        }else {
            currentQueue.add(c);
        }
        return cashiers;
    }
    /*********************************************************************
     *
     * Method name: changePosition.
     *
     * Description of the Method: this method will move an elder customer as many position as jumps it has.
     *
     * Calling arguments:
     *  Customer c; an object of class customer.
     * 	Deque currentQueue; the currentQueue of a cashier.
     * 	int position; the position where the elder customer will be introduced
     *
     *********************************************************************/
    public static void changePosition(Customer c, Deque<Customer> currentQueue, int position) {
        Customer element;

        if ( !(currentQueue.size() == position)) {
            element = currentQueue.removeLast();
            changePosition(c,currentQueue, position);
            currentQueue.add(element);
        }

        else{
            currentQueue.addLast(c);
        }
    }

    /*********************************************************************
     *
     * Method name: flushCashier.
     *
     * Description of the Method: ???
     *
     * Calling arguments:
     * 	Queue cashiers;
     *
     *********************************************************************/
    public static void flushCashier(Queue<Cashier> cashiers){
        for(Cashier<Customer> cashier: cashiers){
            if(cashier.getCurrentQueue().peek() != null){
                cashier.getCurrentQueue().peek().flushAllTime();
                cashier.setTotalTime(cashier.getTotalTime() + 1);
            }
        }
    }

    /*********************************************************************
     *
     * Method name: checkPeek.
     *
     * Description of the Method: this method will check if a customer is served, print it, and removes it from the queue of the cashier.
     *
     * Calling arguments:
     * 	Queue cashiers; the queue of cashiers.
     *
     *********************************************************************/
    public static void checkPeek(Queue<Cashier> cashiers){
        for(Cashier<Customer> cashier: cashiers){
            if(cashier.getCurrentQueue().peek() != null && cashier.getCurrentQueue().peek().getAllTime() <= 0){
                System.out.println("\nThe following customer has left the queue '" + cashier.getID() +"'");
                System.out.println(cashier.getCurrentQueue().peek().toString());
                cashier.getCurrentQueue().remove();
            }
        }
    }

    /*********************************************************************
     *
     * Method name: largestCashier.
     *
     * Description of the Method: this method will print the cashier with the largest work-time.
     *
     * Calling arguments:
     * 	Queue cashiers; the queue of cashiers
     *
     *********************************************************************/
    public static void largestCashier(Queue<Cashier> cashiers) {
        int max = 0;
        int tmp;
        System.out.println("\nThe cashier with the largest work-time is:");

        for(Cashier<Customer> cashier : cashiers){
            tmp = cashier.getTotalTime();
            if(tmp > max) {
                max=tmp;
            }
        }

        for(Cashier<Customer> cashier : cashiers){
            if(max == cashier.getTotalTime()) {
                System.out.println(cashier.toString());
            }
        }
    }

    public static void main(String[] args) {

        Queue<Cashier> cashiers = CashierFactory.generateCashiers();

        int time = 0;
        Customer c = CustomerFactory.generateCustomer(time);
        cashiers = intoQue(c,cashiers);
        publishCashiersStatus(cashiers);
        time++;

        while(!isAllFinished(cashiers) || (time < 3000)){

             /* Each 30 seconds, a new customer will arrive at the cashiers. To represent when, if the rest of variable time
             *is equal to 0 (that means, the time is multiple of 30), it will generate one more customer and put it into a queue. */

            if((time%30 == 0)){
                Customer customers = CustomerFactory.generateCustomer(time);
                if(customers != null) {
                    cashiers = intoQue(customers,cashiers);
                }
                publishCashiersStatus(cashiers);
            }
            time++;

            // flush cashier work time and top customer's wait time.
            flushCashier(cashiers);
            checkPeek(cashiers);
        }

        publishCashiersStatus(cashiers);
        checkPeek(cashiers);
        largestCashier(cashiers);
    }
}