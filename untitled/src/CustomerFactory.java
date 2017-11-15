/*********************************************************************
 *
 * Class Name: CustomerFactory
 * Author/s name: Javier Romero Sanchez, Raul Valentin Jorge, Ning Wang [*Note: order by surname]
 * Release/Creation date: 20/10/2017
 * Class description: this class is use to create all the customer objects.
 *
 ***********************************************************************/
class CustomerFactory {

    private final static Integer ELDER_RATE = 10;
    private final static Integer PRODUCTS_CELL = 50;
    public static Integer customerNumbers = 0;

    /*********************************************************************
     *
     * Method name: generateCustomers.
     *
     * Description of the Method: it will generate a customer each 30 seconds until 100 customers are created.
     *
     * Calling arguments:
     * 	int arrive; the arriveTime of the customers (it has to be multiple of 30)
     *
     * Return value: the customer generated or null(if we have created 100 customers).
     *
     *********************************************************************/
    static Customer generateCustomer(int arrive){

        if(customerNumbers < 100){
            customerNumbers += 1;
            Customer customer = new Customer(arrive,(int)((Math.random()*PRODUCTS_CELL) + 1), (int) (Math.random()*100) <= ELDER_RATE, String.format("%03d", customerNumbers));
            return customer;
        }
        return null;
    }
}
