import java.util.*;

public class modifi {

    public static void main(String[] args) {
        // Create instances of FoodQueue
        FoodQueue cashier1 = new FoodQueue(2);
        FoodQueue cashier2 = new FoodQueue(3);
        FoodQueue cashier3 = new FoodQueue(5);

        int burgerStock = 50; // Initial stock of burgers

        Scanner input = new Scanner(System.in);

        while (true) {
            // Display menu options
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

            while (true){
            System.out.print("Enter your choice: ");
            String userChoice = input.next();

            switch (userChoice) {
                
                case "100", "VFQ" -> {
                    System.out.println("\t\t*****************");
                    System.out.println("\t\t*   Cashiers    *");
                    System.out.println("\t\t*****************");

                    int maxLength = Math.max(cashier1.getQueueLength(), Math.max(cashier2.getQueueLength(), cashier3.getQueueLength()));

                    for (int i = 0; i < maxLength; i++) {
                        if (i < 2) {
                            System.out.print("\t\t");
                            if (cashier1.getFoodQueueCustomer(i + 1) == null) {
                                System.out.print("X");
                            } else {
                                System.out.print("O");
                            }

                            System.out.print("\t\t");

                            if (cashier2.getFoodQueueCustomer(i + 1) == null) {
                                System.out.print("X");
                            } else {
                                System.out.print("O");
                            }

                            System.out.print("\t\t");

                            if (cashier3.getFoodQueueCustomer(i + 1) == null) {
                                System.out.print("X");
                            } else {
                                System.out.print("O");
                            }

                            System.out.println();
                        } else if (i == 2) {
                            System.out.print("\t\t\t\t");

                            if (cashier2.getFoodQueueCustomer(i + 1) == null) {
                                System.out.print("X");
                            } else {
                                System.out.print("O");
                            }

                            System.out.print("\t\t");

                            if (cashier3.getFoodQueueCustomer(i + 1) == null) {
                                System.out.print("X");
                            } else {
                                System.out.print("O");
                            }

                            System.out.println();
                        } else {
                            System.out.print("\t\t\t\t\t\t");

                            if (cashier3.getFoodQueueCustomer(i + 1) == null) {
                                System.out.print("X");
                            } else {
                                System.out.print("O");
                            }

                            System.out.println();
                        }
                    }
                }

                case "102", "ACQ"-> {
                    // Add a customer to a specific queue
                    System.out.print("Enter the queue number to add (1, 2, or 3): ");
                    int queueNumber = input.nextInt();

                    if (queueNumber < 1 || queueNumber > 3) {
                        System.out.println("Invalid queue number. Please try again!");
                        continue;
                    }

                    FoodQueue selectedQueue = null;

                    if (queueNumber == 1) {
                        selectedQueue = cashier1;
                    } else if (queueNumber == 2) {
                        selectedQueue = cashier2;
                    } else if (queueNumber == 3) {
                        selectedQueue = cashier3;
                    }
                    input.nextLine(); // Consume the remaining newline character

                    Customer customer = new Customer();
                    String firstName = customer.getFirstName();
                    String lastName =customer.getLastName();
                    int burgersCount = customer.getBurgersCount();


                    selectedQueue.setFoodQueueCustomer(queueNumber, firstName,lastName , burgersCount);

                    System.out.println("Customer added to queue " + queueNumber);
                    System.out.println();
                    burgerStock -= burgersCount;
                    break;

                }

                case "103", "RCQ"-> {
                    // Remove a customer from a specific location in a queue
                    System.out.print("Enter the queue number to remove customer from (1, 2, or 3): ");
                    int queueNumber = input.nextInt();
                    input.nextLine(); // Consume the remaining newline character

                    if (queueNumber < 1 || queueNumber > 3) {
                        System.out.println("Invalid queue number. Please try again!");
                        continue;
                    }

                    FoodQueue selectedQueue = null;

                    if (queueNumber == 1) {
                        selectedQueue = cashier1;
                    } else if (queueNumber == 2) {
                        selectedQueue = cashier2;
                    } else if (queueNumber == 3) {
                        selectedQueue = cashier3;
                    }

                    System.out.print("Enter the position of the customer to remove: ");
                    int position = input.nextInt();
                    input.nextLine(); // Consume the remaining newline character

                    if (position < 1 || position > selectedQueue.getQueueLength()) {
                        System.out.println("Invalid position. Please try again!");
                        continue;
                    }

                    selectedQueue.setFoodQueueCustomerNull(position);

                    System.out.println("Customer removed from queue " + queueNumber + " at position " + position);
                    System.out.println();
                    break;
                }

                case "104", "PCQ"-> {
                    // Process a customer from the front of a specific queue
                    System.out.print("Enter the queue number to process customer from (1, 2, or 3): ");
                    int queueNumber = input.nextInt();
                    input.nextLine(); // Consume the remaining newline character

                    if (queueNumber < 1 || queueNumber > 3) {
                        System.out.println("Invalid queue number. Please try again!");
                        continue;
                    }

                    FoodQueue selectedQueue = null;

                    if (queueNumber == 1) {
                        selectedQueue = cashier1;
                    } else if (queueNumber == 2) {
                        selectedQueue = cashier2;
                    } else if (queueNumber == 3) {
                        selectedQueue = cashier3;
                    }

                    if (selectedQueue.getFoodQueueCustomer(1) == null) {
                        System.out.println("No customer in queue " + queueNumber + " to process.");
                        continue;
                    }

                    Customer processedCustomer = selectedQueue.getFoodQueueCustomer(1);
                    int burgersCount = processedCustomer.getBurgersCount();

                    System.out.println("Processing customer: " + processedCustomer.getFirstName() + " " +
                            processedCustomer.getLastName());
                    System.out.println("Number of burgers: " + burgersCount);
                    System.out.println("Total price: " + (Customer.burgerPrice * burgersCount));

                    selectedQueue.setFoodQueueCustomerNull(1);

                    System.out.println("Customer processed and removed from queue " + queueNumber);
                    System.out.println();
                    break;
                }
                case "105", "VCS"-> {

                    System.out.print("Enter the queue number to display the customer list (1, 2, or 3): ");
                    int queueNumber = input.nextInt();
                    input.nextLine(); // Consume the remaining newline character

                    if (queueNumber < 1 || queueNumber > 3) {
                        System.out.println("Invalid queue number. Please try again!");
                        continue;
                    }

                    FoodQueue selectedQueue = null;

                    if (queueNumber == 1) {
                        selectedQueue = cashier1;
                    } else if (queueNumber == 2) {
                        selectedQueue = cashier2;
                    } else if (queueNumber == 3) {
                        selectedQueue = cashier3;
                    }

                    System.out.println("Customer list for queue " + queueNumber + ":");
                    for (int i = 1; i <= selectedQueue.getQueueLength(); i++) {
                        Customer customer = selectedQueue.getFoodQueueCustomer(i);

                        if (customer != null) {
                            String firstName = customer.getFirstName();
                            String lastName = customer.getLastName();

                            System.out.println(i + ". " + firstName + " " + lastName);
                        } else {
                            System.out.println(i + ". Empty");
                        }
                    }

                    System.out.println();
                    break;
                }
                case "108", "STK"-> {
                    // Get stock of remaining burgers
                    System.out.println("Remaining burgers in stock: " + burgerStock );
                    System.out.println();
                    break;
                }

                case "109", "ABG"-> {
                    // Add burgers to the stock
                    System.out.print("Enter the number of burgers to add: ");
                    int burgersToAdd = input.nextInt();
                    input.nextLine(); // Consume the remaining newline character

                    burgerStock += burgersToAdd;

                    System.out.println(burgersToAdd + " burgers added to stock.");
                    System.out.println("Total burgers in stock: " + burgerStock);
                    System.out.println();
                    break;
                }

                    case "110", "INC"-> {
                    // Get income from all queues
                    int income1 = 0;
                    int income2 = 0;
                    int income3 = 0;

                    for (int i = 1; i <= cashier1.getQueueLength(); i++) {
                        Customer customer = cashier1.getFoodQueueCustomer(i);
                        if (customer != null) {
                            income1 += Customer.burgerPrice * customer.getBurgersCount();
                        }
                    }

                    for (int i = 1; i <= cashier2.getQueueLength(); i++) {
                        Customer customer = cashier2.getFoodQueueCustomer(i);
                        if (customer != null) {
                            income2 += Customer.burgerPrice * customer.getBurgersCount();
                        }
                    }

                    for (int i = 1; i <= cashier3.getQueueLength(); i++) {
                        Customer customer = cashier3.getFoodQueueCustomer(i);
                        if (customer != null) {
                            income3 += Customer.burgerPrice * customer.getBurgersCount();
                        }
                    }

                    int totalIncome = income1 + income2 + income3;
                    System.out.println("Income from Queue 1: " + income1);
                    System.out.println("Income from Queue 2: " + income2);
                    System.out.println("Income from Queue 3: " + income3);
                    System.out.println("Total income from all queues: " + totalIncome);
                    System.out.println();
                    break;
                }

                case "999", "EXT"-> {
                    // Exit the program
                    System.out.println("Exiting the program...");
                    input.close();
                    System.exit(0);
                }

                default -> {
                    System.out.println("Invalid choice. Please try again!");
                    System.out.println();
                }
            }
        }
    }
}
}

