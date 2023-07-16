import java.util.ArrayList;
import java.util.List;

public class FoodQueue {
    static List<Customer> line_1 = new ArrayList<>();
    static List<Customer> line_2 = new ArrayList<>();
    static List<Customer> line_3 = new ArrayList<>();
    private final Customer[] queue;

    public FoodQueue(int queueLength) {
        queue = new Customer[queueLength];
    }
    public static boolean isWaitingListFull(int maxCapacity) {
        return Customer.waitingListQueue.size() == maxCapacity;
    }
    public static void setMaxCapacity(int maxCapacity) {
        Main.maxCapacity = maxCapacity;
    }
    public static List<Customer> getQueue(int queueNumber) {
        switch (queueNumber) {
            case 1 -> {
                return line_1;
            }
            case 2 -> {
                return line_2;
            }
            case 3 -> {
                return line_3;
            }
            default -> {
                return null;
            }
        }
    }
    public static void removeCustomerFromLine(Customer customer) {
        line_1.remove(customer);
        line_2.remove(customer);
        line_3.remove(customer);
    }

    public static int calculateIncome(List<Customer> queue) {
        int income = 0;
        for (Customer customer : queue) {
            if (customer != null) {
                income += customer.getBurgersCount() * Main.burgerPrice;
            }
        }
        return income;
    }
}

