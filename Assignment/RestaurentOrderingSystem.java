package Assignment;

// 1. Create Waiter, Chef, and Customer classes implementing the Runnable interface.
// 2. The Customer places an order, the Waiter picks it up and notifies the Chef.
// 3. The Chef prepares the order and notifies the Waiter to serve it.
// 4. Ensure thread synchronization using synchronized blocks or methods

class Order {
    String itemName;
    public Order(String itemName) {
        this.itemName = itemName;
    }
}

class Waiter implements Runnable {
    Order currentOrder;
    boolean orderTaken = false;
    boolean foodPrepared = false;

    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    // Wait until customer places the order
                    while (!orderTaken) {
                        wait();
                    }

                    // Serve the order to the chef
                    System.out.println("Waiter received Order: " + currentOrder.itemName);
                    foodPrepared = false; // Chef has not prepared the food yet
                    notify(); // Notify the chef to start preparing the order
                    wait(); // Wait for the chef to finish preparing

                    // After chef finishes, serve the customer
                    System.out.println("Waiter is serving the customer.");
                    orderTaken = false;
                    notify(); // Notify the customer the food is ready
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Chef implements Runnable {
    Waiter waiter;

    public Chef(Waiter waiter) {
        this.waiter = waiter;
    }

    public void run() {
        while (true) {
            synchronized (waiter) {
                try {
                    // Wait until waiter informs about the order
                    while (waiter.currentOrder == null || waiter.foodPrepared) {
                        waiter.wait();
                    }

                    // Chef starts preparing the food
                    System.out.println("Chef started preparing: " + waiter.currentOrder.itemName);
                    Thread.sleep(100); // Simulate preparation time
                    System.out.println("Chef finished preparing: " + waiter.currentOrder.itemName);
                    waiter.foodPrepared = true; // Mark the food as prepared
                    waiter.notify(); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Customer implements Runnable {
    Waiter waiter;

    public Customer(Waiter waiter) {
        this.waiter = waiter;
    }

    public void run() {
        int i = 1; // Placing a limited number of orders
        while (i <= 10) {
            synchronized (waiter) {
                try {
                    // Place an order
                    waiter.currentOrder = new Order("Pizza " + i);
                    System.out.println("Customer placed order: " + waiter.currentOrder.itemName);
                    waiter.orderTaken = true; // Mark order as placed
                    waiter.notify(); // Notify the waiter about the new order

                    // Wait until the waiter serves the food
                    waiter.wait();
                    System.out.println("Customer received food: " + waiter.currentOrder.itemName);
                    Thread.sleep(100); // Simulate eating time
                    i++; // Next order
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class RestaurentOrderingSystem {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Chef chef = new Chef(waiter);
        Customer customer = new Customer(waiter);

        Thread waiterThread = new Thread(waiter);
        Thread chefThread = new Thread(chef);
        Thread customerThread = new Thread(customer);

        waiterThread.start();
        chefThread.start();
        customerThread.start();
    }
}

