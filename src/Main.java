import java.io.*;
import java.util.*;
public class Main {
    private static final int Queue1_max_size = 2;
    private static final int Queue2_max_size = 3;
    private static final int Queue3_max_size = 5;

    private static final List<Customer> FoodQueue1 = new ArrayList<>(Queue1_max_size);
    private static final List<Customer> FoodQueue2 = new ArrayList<>(Queue2_max_size);
    private static final List<Customer> FoodQueue3 = new ArrayList<>(Queue3_max_size);

    static ArrayList<Customer> customerNameList = new ArrayList<>();

    private static int BurgerStock = 50;
    static int burgerPrice = 650;
    static int maxCapacity;

    public static void main(String[] args) {

        String[] cashier1 = {"X", "X"};
        String[] cashier2 = {"X", "X", "X"};
        String[] cashier3 = {"X", "X", "X", "X", "X"};

        Scanner Input = new Scanner(System.in);

        System.out.println("***************************");
        System.out.println("* Foodies Fave Food center " + "*");
        System.out.println("***************************");

        System.out.println("100 or VFQ: View all Queues");
        System.out.println("101 or VEQ: View all Empty Queues");
        System.out.println("102 or ACQ: Add customer to a Queue");
        System.out.println("103 or RCQ: Remove a customer from a Queue (From a specific location)");
        System.out.println("104 or PCQ: Remove a served customer");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
        System.out.println("106 or SPD: Store Program Data into file");
        System.out.println("107 or LPD: Load Program Data from file");
        System.out.println("108 or STK: View Remaining burgers Stock");
        System.out.println("109 or AFS: Add burgers to Stock");
        System.out.println("110 or IFQ: Income of each queue");
        System.out.println("112 or GUI: Graphical user interface");
        System.out.println("999 or EXT: Exit the Program");

        while (true) {
            System.out.print("Enter your choice: ");
            String User_choice = Input.next();

            switch (User_choice) {
                case "100", "VFQ" -> {
                    System.out.println("******************");
                    System.out.println("   *Cashier*  ");
                    System.out.println("******************");
                    int maxLength = Math.max(cashier1.length, Math.max(cashier2.length, cashier3.length));

                    for (int i = 0; i < maxLength; i++) {
                        String item1 = i < cashier1.length ? cashier1[i] : "";
                        String item2 = i < cashier2.length ? cashier2[i] : "";
                        String item3 = i < cashier3.length ? cashier3[i] : "";

                        System.out.println(item1 + "\t\t" + item2 + "\t\t" + item3);
                    }
                }
                case "101", "VEQ" -> {
                    System.out.println("Empty Queues:");
                    if (cashier1[0].equals("X")) {
                        System.out.println("Queue 1 is empty.");
                    }
                    if (cashier2[0].equals("X")) {
                        System.out.println("Queue 2 is empty.");
                    }
                    if (cashier3[0].equals("X")) {
                        System.out.println("Queue 3 is empty.");
                    }
                }
                case "102", "ACQ" -> {

                    Customer customer = new Customer();
                    System.out.print("Enter the first name of the customer: ");
                    String firstName = Input.next();
                    customer.setFirstName(firstName);

                    System.out.print("Enter the last name of the customer: ");
                    String lastName = Input.next();
                    customer.setLastName(lastName);

                    System.out.print("How many burgers does the customer want?: ");
                    int burgersCount = Input.nextInt();

                    customer.setBurgersCount(burgersCount);
                    customerNameList.add(customer);

                    if (burgersCount <= BurgerStock) {
                        if (FoodQueue1.size() < Queue1_max_size) {
                            FoodQueue1.add(customer);
                            customerNameList.add(customer);
                            FoodQueue.line_1.add(customer);
                            System.out.println("Customer added successfully.");
                            System.out.println("Customer added to Queue 1.");
                            BurgerStock -= burgersCount;
                            cashier1[FoodQueue.line_1.size() - 1] = "O";
                            break;
                        } else if (FoodQueue2.size() < Queue2_max_size) {
                            FoodQueue2.add(customer);
                            customerNameList.add(customer);
                            FoodQueue.line_2.add(customer);
                            System.out.println("Customer added successfully.");
                            System.out.println("Customer added to Queue 2.");
                            BurgerStock -= burgersCount;
                            cashier2[FoodQueue.line_2.size() - 1] = "O";
                            break;
                        } else if (FoodQueue3.size() < Queue3_max_size) {
                            FoodQueue3.add(customer);
                            customerNameList.add(customer);
                            FoodQueue.line_3.add(customer);
                            System.out.println("Customer added successfully.");
                            System.out.println("Customer added to Queue 3.");
                            BurgerStock -= burgersCount;
                            cashier3[FoodQueue.line_3.size() - 1] = "O";
                            break;
                        } else {
                            if (FoodQueue.isWaitingListFull(maxCapacity)) {
                                System.out.println("All queues are full. Customer added to the Waiting List");
                                Customer.addCustomerToWaitingList();
                                BurgerStock -= burgersCount;
                                break;
                            }
                        else {
                                System.out.println("Burger stock is insufficient. Customer cannot be added.");
                                customerNameList.remove(customer); // Remove the customer from the customerNameList
                                if (BurgerStock <= 10) {
                                    System.out.println("Burger Stock is low please add burgers to the stock (select option 109 to add).");
                                }
                            }
                        }
                    }
                }
                case "103", "RCQ" -> {
                    System.out.print("Enter Queue number to remove customer from (1-3): ");
                    int queueNumber = Input.nextInt();
                    if (queueNumber < 1 || queueNumber > 3) {
                        System.out.println("Invalid queue number. Please try again.");
                        break;
                    }

                    List<Customer> targetQueue = FoodQueue.getQueue(queueNumber);

                    System.out.print("Enter position to remove customer from (1-5): ");
                    int position = Input.nextInt();
                    if (position < 1 || position > targetQueue.size()) {
                        System.out.println("Invalid position. Please try again.");
                        break;
                    }

                    Customer removedCustomer = targetQueue.remove(position - 1);
                    if (removedCustomer != null) {
                        FoodQueue.removeCustomerFromLine(removedCustomer);
                        switch (queueNumber) {
                            case 1:
                                if (FoodQueue.line_1.size() > 0) {
                                    cashier1[FoodQueue.line_1.size() ] = "X";
                                }
                                break;
                            case 2:
                                if (FoodQueue.line_2.size() > 0) {
                                    cashier2[FoodQueue.line_2.size()] = "X";
                                }
                                break;
                            case 3:
                                if (FoodQueue.line_3.size() > 0) {
                                    cashier3[FoodQueue.line_3.size() ] = "X";
                                }
                                break;
                            default:
                                System.out.print("Invalid queue number.");
                                break;
                        }
                        System.out.println("Customer removed from Queue " + queueNumber + ".");
                        customerNameList.remove(removedCustomer);

                    } else {
                        System.out.println("No customer found at the specified position.");
                    }
                }
                case "104", "PCQ" -> {
                    System.out.print("Enter Queue number to remove served customer from (1-3): ");
                    int queueNumber = Input.nextInt();
                    if (queueNumber < 1 || queueNumber > 3) {
                        System.out.println("Invalid queue number. Please try again.");
                        break;
                    }

                    List<Customer> targetQueue = FoodQueue.getQueue(queueNumber);
                    boolean removed = false;

                    for (int i = 0; i < targetQueue.size(); i++) {
                        Customer customer = targetQueue.get(i);
                        if (customer != null) {
                            targetQueue.set(i, null);
                            FoodQueue.removeCustomerFromLine(customer);
                            switch (queueNumber) {
                                case 1:
                                    if (!FoodQueue.line_1.isEmpty()) {
                                        cashier1[FoodQueue.line_1.size() - 1] = "X";
                                    }
                                    break;
                                case 2:
                                    if (!FoodQueue.line_2.isEmpty()) {
                                        cashier2[FoodQueue.line_2.size() - 1] = "X";
                                    }
                                    break;
                                case 3:
                                    if (!FoodQueue.line_3.isEmpty()) {
                                        cashier3[FoodQueue.line_3.size() - 1 ] = "X";
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid queue number.");
                                    break;
                            }
                            System.out.println("Served customer removed from Queue " + queueNumber + ".");
                            customerNameList.remove(customer);
                            removed = true;

                            // Add customer from the waiting list to the queue if available
                            if (!Customer.waitingListQueue.isEmpty()) {
                                Customer nextCustomer = Customer.waitingListQueue.remove();
                                targetQueue.set(i, nextCustomer);
                                switch (queueNumber) {
                                    case 1 -> cashier1[FoodQueue.line_1.size() - 1] = "O";
                                    case 2 -> cashier2[FoodQueue.line_2.size() - 1] = "O";
                                    case 3 -> cashier3[FoodQueue.line_3.size() - 1] = "O";
                                    default -> System.out.println("Invalid queue number.");
                                }
                                System.out.println("Next customer from Waiting List added to Queue " + queueNumber + ".");
                            }
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("No served customer found in the queue.");

                    }
                }
                case "105", "VCS" -> {
                    System.out.println("Customer List:");

                    Set<Customer> uniqueCustomers = new TreeSet<>(Comparator.comparing(Customer::getFirstName, Comparator.nullsFirst(String::compareTo)));

                    // Add non-null elements to the TreeSet
                    for (Customer customer : Customer.waitingListQueue) {
                        if (customer != null) {
                            uniqueCustomers.add(customer);
                        }
                    }
                    for (Customer customer : customerNameList) {
                        if (customer != null) {
                            uniqueCustomers.add(customer);
                        }
                    }

                    for (Customer customer : uniqueCustomers) {
                        System.out.println("* Name: " + customer.getFirstName().toLowerCase());
                    }
                }
                case "106", "SPD" -> {
                    customerNameList.sort(Comparator.comparing(Customer::getFirstName));
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("programData.txt"))) {
                        ArrayList<String> uniqueFirstNames = new ArrayList<>();

                        for (Customer customer : customerNameList) {
                            String firstName = customer.getFirstName();

                            // Check if the first name is already present in the list

                            if (!uniqueFirstNames.contains(firstName)) {
                                uniqueFirstNames.add(firstName);
                                writer.write(firstName);
                                writer.newLine();
                            }
                        }

                        System.out.println("Customer names saved to file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while saving customer names: " + e.getMessage());
                    }
                }
                case "107", "LPD" -> {
                    // Clear the existing customer names in the list
                    customerNameList.clear();

                    try (BufferedReader br = new BufferedReader(new FileReader("programData.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            // Create a new Customer object for each line in the file
                            Customer customer = new Customer();
                            customer.setFirstName(line); // Assuming the 'setFirstName' method is available in the Customer class

                            customerNameList.add(customer);
                        }

                        // Print the loaded customer names
                        for (Customer customer : customerNameList) {
                            System.out.println(customer.getFirstName());
                        }
                        System.out.println("Customer names loaded from file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while loading customer names: " + e.getMessage());

                    }
                }

                case "108", "STK" -> {
                    System.out.println("Remaining burgers in stock: " + BurgerStock);
                }
                case "109", "AFS" -> {
                    while (BurgerStock < 50) {
                        BurgerStock++;
                    }
                    System.out.println("Burgers added to stock - Updated stock : " + BurgerStock);
                }
                case "110", "IFQ" -> {
                    int income1 = FoodQueue.calculateIncome(FoodQueue1);
                    int income2 = FoodQueue.calculateIncome(FoodQueue2);
                    int income3 = FoodQueue.calculateIncome(FoodQueue3);

                    int totalIncome = income1 + income2 + income3;

                    System.out.println("The Income of Queue 1 is: " + income1);
                    System.out.println("The Income of Queue 2 is: " + income2);
                    System.out.println("The Income of Queue 3 is: " + income3);
                    System.out.println("Total income is: " + totalIncome);
                    System.out.println();

                }
                case "999", "EXT" -> {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}







