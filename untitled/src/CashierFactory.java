
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/*********************************************************************
 *
 * Class Name: CashierFactory
 * Author/s name: Javier Romero Sanchez, Raul Valentin Jorge, Ning Wang [*Note: order by surname]
 * Release/Creation date: 20/10/2017
 * Class description: this class is used to create all the cashier objects.
 *
 ***********************************************************************/
public class CashierFactory {

    private static final int EFFICIENCY_CELL = 15; //Maximum number of seconds it can be taken to process a product.
    private static final int NUMBER_CASHIERS = 10; //Total number of cashiers.

    /*********************************************************************
     *
     * Method name: generateCashiers.
     *
     * Description of the Method: it will generate as many cashiers as NUMBER_CASHIERS indicates.
     *
     * Return value: a queue of cashiers.
     *
     *********************************************************************/
    //@SuppressWarnings("rawtypes")
    static Queue<Cashier> generateCashiers() {

        Queue<Cashier> cashiers = new PriorityQueue<>(Collections.reverseOrder());

        //In this loop we generate the cashiers, from the cashier with the ID 0 - 9.
        for (int i = 0; i < NUMBER_CASHIERS; i++) {
            //We pass the parameters of ID, efficiency rate and total time (Being for the moment 0 because we can't put null values.
            Cashier<Customer> cashier = new Cashier<Customer>((String.format("%02d", i+1)),(int)((Math.random()*EFFICIENCY_CELL) + 5),0);
            cashiers.add(cashier);
        }
        return cashiers;
    }
}
