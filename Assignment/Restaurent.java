package Assignment;
/* Create a multi-threaded application that simulates a Restaurant Ordering System:
 1. Create Waiter, Chef, and Customer classes implementing the Runnable interface.
 2. The Customer places an order, the Waiter picks it up and notifies the Chef.
 3. The Chef prepares the order and notifies the Waiter to serve it.
 4. Ensure thread synchronization using synchronized blocks or methods */

class Customer implements Runnable{
    public void run(){
        System.out.println("Customer placed an order: Wadapav");
    }
}
class Waiter implements Runnable{
    @Override
    public void run(){
        System.out.println("Waiter picked up an order : Wadapav");

    }
}
class Chef implements Runnable{
    @Override
    public void run(){
        System.out.println("Chef prepared order: Wadapav");
    }
}
public class Restaurent {
    public static void main(String[] args) {
        Waiter waiter= new Waiter();
        Chef chef= new Chef();
        Customer customer= new Customer();

        Thread customThread= new Thread(customer);
        Thread waiterThread= new Thread(waiter);
        Thread chefThread= new Thread(chef);
       

        waiterThread.start();
        chefThread.start();
        customThread.start();
    }
    
}
