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
    static ArrayList<Customer> line_1 = new ArrayList<>();
    static ArrayList<Customer> line_2 = new ArrayList<>();
    static ArrayList<Customer> line_3 = new ArrayList<>();

    private static int BurgerStock = 50;
    static int burgerPrice = 650;

    public static void main(String[] args) {

        String[] cashier1 = {"X", "X"};
        String[] cashier2 = {"X", "X", "X"};
        String[] cashier3 = {"X", "X", "X", "X", "X"};

        Scanner Input = new Scanner(System.in);

        System.out.println("**************************");
        System.out.println("* Foodies Fave Food center " + "*");
        System.out.println("**************************");

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

                    BurgerStock -= burgersCount;
                    customerNameList.add(customer);

                    if (BurgerStock >= burgersCount) {
                        System.out.println("Customer added successfully.");
                        if (FoodQueue1.size() < Queue1_max_size) {
                            FoodQueue1.add(customer);
                            customerNameList.add(customer);
                            line_1.add(customer);
                            System.out.println("Customer added to Queue 1.");
                            cashier1[line_1.size() - 1] = "O";
                        } else if (FoodQueue2.size() < Queue2_max_size) {
                            FoodQueue2.add(customer);
                            customerNameList.add(customer);
                            line_2.add(customer);
                            System.out.println("Customer added to Queue 2.");
                            cashier2[line_2.size() - 1] = "O";
                        } else if (FoodQueue3.size() < Queue3_max_size) {
                            FoodQueue3.add(customer);
                            customerNameList.add(customer);
                            line_3.add(customer);
                            System.out.println("Customer added to Queue 3.");
                            cashier3[line_3.size() - 1] = "O";
                        } else {
                            System.out.println("All queues are full. Customer cannot be added.");
                        }
                    } else  {
                        System.out.println("Burger stock is insufficient. Customer cannot be added.");
                    }
                }
                case "103", "RCQ" -> {
                    System.out.print("Enter Queue number to remove customer from (1-3): ");
                    int queue_number = Input.nextInt();
                    System.out.print("Enter position to remove customer from (1-5): ");
                    int position = Input.nextInt();

                    boolean removed = false;
                    List<Customer> targetQueue = getQueue(queue_number);

                    if (position >= 1 && position <= targetQueue.size()) {
                        Customer customerToRemove = targetQueue.get(position - 1);

                        if (customerToRemove != null) {
                            targetQueue.set(position - 1, null);
                            removeCustomerFromLine(customerToRemove);
                            System.out.println("Customer removed from Queue " + queue_number + ".");
                            removed = true;

                        }
                    }

                    if (!removed) {
                        System.out.println("Invalid position or customer not found in the queue.");
                    }
                }
                case "104", "PCQ" -> {
                    System.out.print("Enter Queue number to remove served customer from (1-3): ");
                    int queue_number = Input.nextInt();
                    if (queue_number > 3){
                        System.out.print("Please enter the correct number : ");
                        queue_number = Input.nextInt();
                    }

                    boolean removed = false;
                    List<Customer> targetQueue = getQueue(queue_number);

                    for (int i = 0; i < targetQueue.size(); i++) {
                        Customer customer = targetQueue.get(i);
                        if (customer != null) {
                            targetQueue.set(i, null);
                            removeCustomerFromLine(customer);
                            Customer.cashierStatusUpdate(queue_number);
                            System.out.println("Served customer removed from Queue " + queue_number + ".");
                            removed = true;
                            // Automatically place the next customer from the waiting list into the food queue
                            if (queue_number == 1 && line_1.size() > 0) {
                                Customer nextCustomer = line_1.remove(0);
                                targetQueue.set(i, nextCustomer);
                                System.out.println("Next customer in line added to Queue 1.");
                            } else if (queue_number == 2 && line_2.size() > 0) {
                                Customer nextCustomer = line_2.remove(0);
                                targetQueue.set(i, nextCustomer);
                                System.out.println("Next customer in line added to Queue 2.");
                            } else if (queue_number == 3 && line_3.size() > 0) {
                                Customer nextCustomer = line_3.remove(0);
                                targetQueue.set(i, nextCustomer);
                                System.out.println("Next customer in line added to Queue 3.");
                            } else {
                                System.out.println("No customers in line to add to the queue.");
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
                    //The TreeSet implementation is used with a comparator to maintain the sorting order based on the first name
                    Set<Customer> uniqueCustomers = new TreeSet<>(Comparator.comparing(Customer::getFirstName));

                    uniqueCustomers.addAll(customerNameList);

                    for (Customer customer : uniqueCustomers) {
                        System.out.println("* Name: " + customer.getFirstName().toUpperCase() + " * No. of Burgers: " + customer.getBurgersCount());
                    }


                }
                case "106", "SPD" -> {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("programData.txt"))) {
                        for (Customer customer : customerNameList) {
                            writer.write(customer.getFirstName());
                            writer.write(customer.burgersCount);
                            writer.newLine();
                        }
                        System.out.println("Customer names saved to file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while saving customer names: " + e.getMessage());
                    }


                }
                case "107", "LPD" -> {
                    try (BufferedReader br = new BufferedReader(new FileReader("programData.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            Customer customer = new Customer();
                            customer.firstName = line;// Assuming the 'firstName' variable is accessible
                            customer.lastName = line;
                            customerNameList.add(customer);
                        }

                        // Print the loaded customer names
                        for (Customer customer : customerNameList) {
                            System.out.println(customer.firstName + customer.lastName);
                        }
                        System.out.println("Customer names loaded from file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while loading customer names: " + e.getMessage());
                    }

                }
                //demo project testing



                case "108", "STK" -> {
                    System.out.println("Remaining burgers in stock: " + BurgerStock);
                }
                case "109", "AFS" -> {
                    System.out.print("Enter number of burgers to add to stock: ");
                    int additional_burgers = Input.nextInt();
                    BurgerStock += additional_burgers;
                    System.out.println(additional_burgers + " burgers added to stock. Current stock: " + BurgerStock);
                }
                case "110", "IFQ" -> {

                    int income1 = 0;
                    int income2 = 0;
                    int income3 = 0;

                    for (Customer customer : FoodQueue1) {
                        if (customer != null) {

                            income1 += customer.burgersCount * burgerPrice;
                        }
                    }

                    for (Customer customer : FoodQueue2) {
                        if (customer != null) {
                            income2 += customer.burgersCount * burgerPrice;
                        }
                    }

                    for (Customer customer : FoodQueue3) {
                        if (customer != null) {
                            income3 +=customer.burgersCount * burgerPrice;
                        }
                    }

                    int totalIncome = income1 + income2 + income3;

                    System.out.println("The Income of Queue 1 is: " + income1);
                    System.out.println("The Income of Queue 2 is: " + income2);
                    System.out.println("The Income of Queue 3 is: " + income3);
                    System.out.println("Total income is: " + totalIncome);
                    System.out.println();
                    break;

                }
                case "999", "EXT" -> {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static List<Customer> getQueue(int queueNumber) {
        switch (queueNumber) {
            case 1:
                return FoodQueue1;
            case 2:
                return FoodQueue2;
            case 3:
                return FoodQueue3;
            default:
                return null;
        }
    }

    private static void removeCustomerFromLine(Customer customer) {
        line_1.remove(customer);
        line_2.remove(customer);
        line_3.remove(customer);
    }



}

