import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Customer {
    static Queue<Customer> waitingListQueue = new LinkedList<>();
    private String firstName;
    private String lastName;
    private int burgersCount;

    public Customer() {
        this.firstName = "";
        this.lastName = "";
    }

    public String getFirstName() {
       return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBurgersCount() {
      return burgersCount;

    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setBurgersCount (int burgersCount){
        this.burgersCount = burgersCount;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    public static void addCustomerToWaitingList() {
        Scanner Input = new Scanner(System.in);
        System.out.print("Enter the first name of the customer: ");
        String firstName = Input.next();

        System.out.print("Enter the last name of the customer: ");
        String lastName = Input.next();

        System.out.print("How many burgers does the customer want? ");
        int burgersCount = Input.nextInt();

        // Create a new customer with the provided details
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setBurgersCount(burgersCount);

        waitingListQueue.add(newCustomer);
        Main.customerNameList.add(newCustomer);
        System.out.println("Customer added to Waiting List queue.");

    }
}









