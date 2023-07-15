public class FoodQueue {

    private final Customer[] queue;

    public FoodQueue(int queueLength) {
        queue = new Customer[queueLength];
    }

    public Customer getFoodQueueCustomer(int position) {
        return queue[position - 1];
    }

    public int getQueueLength() {
        return queue.length;
    }

    public void getFoodQueueCustomer(int position, Customer customer) {
        queue[position - 1] = customer;
    }

    public void setFoodQueueCustomerNull(int position) {
        queue[position - 1] = null;
    }

    public void setFoodQueueCustomer(int queueNumber, String firstName, String lastName, int burgersCount) {
    }
    public static boolean isWaitingListEmpty() {
        return Main.waitingListQueue.isEmpty();
    }
    public static boolean isWaitingListFull(int maxCapacity) {
        return Main.waitingListQueue.size() == maxCapacity;
    }

}
