/* import java.util.Queue;
import java.util.Scanner;
public class Modify {
    // Stock of burgers
    private static int burgerStock = 50;
    static final int burgerPrice = 650;
    static int burgerCount = 0;

    public static void main(String[] args) {
        // Arrays to store customer names for each cashier
        String[] cashier1 = {"X", "X"};
        String[] cashier2 = {"X", "X", "X"};
        String[] cashier3 = {"X", "X", "X", "X", "X"};
        String[] Name_c1 = new String[2];
        String[] Name_c2 = new String[3];
        String[] Name_c3 = new String[5];

        // Scanner to get user input
        Scanner input = new Scanner(System.in);

        // Display the menu options
        System.out.println("**************************");
        System.out.println("* Foodies Fave Food Center *");
        System.out.println("**************************");

        System.out.println("100 or VFQ: View all Queues");
        System.out.println("101 or VEQ: View all Empty Queues");
        System.out.println("102 or ACQ: Add customer to a Queue");
        System.out.println("103 or RCQ: Remove a customer from a Queue (From a specific location)");
        System.out.println("104 or PCQ: Remove a served customer");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
        System.out.println("106 or SPD: Store Program Data into file");
        System.out.println("107 or LPD: Load Program Data from file");
        System.out.println("108 or STK: View Remaining Burgers Stock");
        System.out.println("109 or AFS: Add Burgers to Stock");
        System.out.println("999 or EXT: Exit the Program");

        // Main program loop
        while (true) {
            System.out.print("Enter your choice: ");
            String userChoice = input.next();

            // Switch statement to handle user choices
            switch (userChoice) {
                // View all queues
                case "100", "VFQ":
                    System.out.println("******************");
                    System.out.println("*   Cashier    *");
                    System.out.println("******************");

                    // Find the maximum length among all cashiers
                    int maxLength = Math.max(cashier1.length, Math.max(cashier2.length, cashier3.length));

                    // Print each cashier's queue in a tabular format
                    for (int i = 0; i < maxLength; i++) {
                        String item1 = i < cashier1.length ? cashier1[i] : "";
                        String item2 = i < cashier2.length ? cashier2[i] : "";
                        String item3 = i < cashier3.length ? cashier3[i] : "";

                        System.out.println(item1 + "\t\t" + item2 + "\t\t" + item3);
                    }
                    System.out.println("X - Not occupied   O - Occupied");
                    System.out.println();
                    break;

                case "101", "VEQ":
                    // View all empty queues
                    if (cashier1[0].equals("X")) {
                        System.out.println("Queue 1 is empty.");
                    }
                    if (cashier2[0].equals("X")) {
                        System.out.println("Queue 2 is empty.");
                    }
                    if (cashier3[0].equals("X")) {
                        System.out.println("Queue 3 is empty.");
                    }
                    System.out.println();
                    break;

                case "102", "ACQ": {
                    // Add customer to a queue
                    System.out.print("Enter the Queue Number you want to add (1, 2, or 3): ");
                    int queueNumber = input.nextInt();

                    if (queueNumber < 1 || queueNumber > 3) {
                        System.out.println("Invalid queue number. Please try again!");
                        continue;
                    }

                    String[] selectedQueue = null;
                    String[] selectedNameArray = null;

                    if (queueNumber == 1) {
                        selectedQueue = cashier1;
                        selectedNameArray = Name_c1;
                    } else if (queueNumber == 2) {
                        selectedQueue = cashier2;
                        selectedNameArray = Name_c2;
                    } else if (queueNumber == 3) {
                        selectedQueue = cashier3;
                        selectedNameArray = Name_c3;
                    }
                    else {
                        continue;
                    }

                    for (int i = 0; i < selectedQueue.length; i++) {
                        if (selectedQueue[i].equals("X")) {
                            selectedQueue[i] = "O";
                            Customer customer = new Customer();
                            String firstName = customer.getFirstName();
                            String lastName = customer.getLastName();
                            int burgersCount = customer.getBurgersCount();


                            selectedNameArray[i] = firstName + " " + lastName + " "+ burgersCount;
                            System.out.println("Customer added to Queue " + queueNumber);
                            break;
                        }
                    }
                    System.out.println();
                    break;
                }

                case "103", "RCQ" : {
                    // Remove a customer from a specific location in a queue
                    System.out.print("Enter the customer name you want to remove: ");
                    String name = input.next();
                    System.out.print("Enter the Line you want to remove (1, 2, or 3): ");
                    int line = input.nextInt();
                    System.out.print("What is the specific location start from (1, 2 or 3): ");
                    int location = input.nextInt();

                    if (line < 1 || line > 3) {
                        System.out.println("Invalid queue number. Please try again!");
                        break;
                    }

                    String[] selectedQueue = null;
                    String[] selectedNameArray = null;

                    if (line == 1) {
                        selectedQueue = cashier1;
                        selectedNameArray = Name_c1;
                    } else if (line == 2) {
                        selectedQueue = cashier2;
                        selectedNameArray = Name_c2;
                    } else if (line == 3) {
                        selectedQueue = cashier3;
                        selectedNameArray = Name_c3;
                    }

                    if (location < 1 || location > selectedNameArray.length) {
                        System.out.println("Invalid location. Please try again!");
                        break;
                    }

                    int index = location - 1;
                    if (selectedQueue[index].equals("O")) {
                        selectedQueue[index] = "X";
                        selectedNameArray[index] = name;
                        burgerStock += 5;
                        System.out.println("Successfully removed the customer!");
                    } else {
                        System.out.println("No customer found at the specified location.");
                    }

                    System.out.println();
                    break;
                }


                case "104", "PCQ" : {
                        System.out.print("Enter the customer name you want to remove: ");
                        String name = input.next();
                        System.out.print("Enter the Queue Number you want to remove (1, 2, or 3): ");
                        int line = input.nextInt();

                        if (line < 1 || line > 3) {
                            System.out.println("Invalid queue number. Please try again!");
                            break;
                        }

                        String[] selectedQueue = null;
                        String[] selectedNameArray = null;

                        if (line == 1) {
                            selectedQueue = cashier1;
                            selectedNameArray = Name_c1;
                        } else if (line == 2) {
                            selectedQueue = cashier2;
                            selectedNameArray = Name_c2;
                        } else if (line == 3) {
                            selectedQueue = cashier3;
                            selectedNameArray = Name_c3;
                        }

                        if (selectedQueue[0].equals("O")) {
                            selectedQueue[0] = "X";
                            selectedNameArray[0] = name;
                            burgerStock += 5;
                            System.out.println("Customer removed!");
                        } else {
                            System.out.println("No customer found in the specified queue.");
                        }

                        System.out.println();
                        break;
                    }

                case "109", "AFS" : {
                    // Add burgers to the stock
                    while (burgerStock<50){
                        burgerStock++;
                    }
                    System.out.println("Burgers added to stock - Updated stock : " + burgerStock);
                }

                    case "110", "IFQ":

                        int income1 = 0;
                        int income2 = 0;
                        int income3 = 0;

                        for (String customerStatus : cashier1) {
                            if (customerStatus.equals("O")) {
                                income1 += burgerCount * burgerPrice;
                            }
                        }

                        for (String customerStatus : cashier2) {
                            if (customerStatus.equals("O")) {
                                income2 += burgerCount * burgerPrice;
                            }
                        }

                        for (String customerStatus : cashier3) {
                            if (customerStatus.equals("O")) {
                                income3 += burgerCount * burgerPrice;
                            }
                        }

                        int totalIncome = income1 + income2 + income3;

                        System.out.println("The Income of Queue 1 is: " + income1);
                        System.out.println("The Income of Queue 2 is: " + income2);
                        System.out.println("The Income of Queue 3 is: " + income3);
                        System.out.println("Total income is: " + totalIncome);
                        System.out.println();
                        break;




                case "999", "EXT":
                    // Exit the program
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again!");
                    break;
            }
        }
    }
}


*/