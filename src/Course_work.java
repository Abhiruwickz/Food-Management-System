import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
public class Course_work {

    // Stock of burgers
    private static int burgerStock = 50;

    public static void main(String[] args) {
        // Arrays to store customer names for each cashier
        String[] cashier1 = {"X", "X"};
        String[] cashier2 = {"X", "X", "X"};
        String[] cashier3 = {"X", "X", "X", "X", "X"};
        String[] Name_c1 = new String[2];
        String[] Name_c2 = new String[3];
        String[] Name_c3 = new String[5];


        // Scanner to get user input
        Scanner Input = new Scanner(System.in);

        // Display the menu options
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

        // Main program loop
        while (true) {
            System.out.print("Enter your choice: ");
            String User_choice = Input.next();

            // Switch statement to handle user choices
            switch (User_choice) {
                // View all queues
                case "100", "VFQ" -> {
                    System.out.println("******************");
                    System.out.println("   *Cashier*  ");
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
                }
                case "101", "VEQ" -> {
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

                }
                case "102", "ACQ" -> {
                    // Add customer to a queue
                    System.out.print("Enter the name of the customer : ");
                    String name = Input.next();
                    System.out.print("Enter the Queue Number you want to add (1, 2, or 3): ");
                    int line = Input.nextInt();
                    if (line < 1 || line > 3) {
                        System.out.println("Invalid line please try again! ");
                        continue;
                    }
                    int i = 0;
                    if (line == 1) {
                        boolean added = false;
                        for (i = 0; i < cashier1.length; i++) {
                            // Check for available queues
                            if (cashier1[i].equals("X")) {
                                cashier1[i] = "O";
                                Name_c1[i] = name;
                                burgerStock = burgerStock - 5;
                                System.out.println("Customer added to Queue 1");
                                System.out.println();
                                added = true;
                                break;
                            }
                        }
                        if (!added) {
                            // No empty queues, display an error message
                            System.out.println("Queue 1 is full. Cannot add more customers.");
                        }
                    } else if (line == 2) {
                        boolean added = false;
                        for (i = 0; i < cashier2.length; i++) {
                            if (cashier2[i].equals("X")) {
                                cashier2[i] = "O";
                                Name_c2[i] = name;
                                burgerStock = burgerStock - 5;
                                System.out.println("Customer added to Queue 2");
                                added = true;
                                break;
                            }
                        }
                        if (!added) {
                            // No empty queues, display an error message
                            System.out.println("Queue 2 is full. Cannot add more customers.");
                        }

                    } else if (line == 3) {
                        boolean added = false;
                        for (i = 0; i < cashier3.length; i++) {
                            if (cashier3[i].equals("X")) {
                                cashier3[i] = "O";
                                burgerStock = burgerStock - 5;
                                Name_c3[i] = name;
                                System.out.println("Customer added to Queue 3");
                                added = true;
                                break;
                            }
                        }
                        if (!added) {
                            // No empty queues, display an error message
                            System.out.println("Queue 3 is full. Cannot add more customers.");
                        }
                    } else {
                        System.out.println("Invalid Queue Number. Please try again! ");

                    }
                }
                case "103", "RCQ" -> {
                    // Remove a customer from a specific location in a queue
                    System.out.print("Enter the customer name you want to remove: ");
                    String Name = Input.next();
                    System.out.print("Enter the Line you want to remove (1, 2, or 3) :  ");
                    int lines = Input.nextInt();
                    System.out.print("What is the specific location start from (1, 2 or 3) : ");
                    int location = Input.nextInt();
                    int i = 0;
                    while (true) {
                        if (lines == 1) {
                            // Validate location
                            if (location > 0 && location < Name_c1.length) {
                                cashier1[i] = "X";
                                Name_c1[i] = Name;
                                burgerStock = burgerStock + 5;
                                System.out.println("Successfully removed the customer!");
                                break;
                            }

                        }
                        if (lines == 2) {
                            if (location > 0 && location < Name_c2.length) {
                                cashier2[i] = "X";
                                Name_c2[i] = Name;
                                burgerStock = burgerStock + 5;
                                System.out.println("Successfully removed the customer!");
                                break;
                            }
                        }
                        if (lines == 3) {
                            if (location > 0 && location < Name_c3.length) {
                                cashier3[i] = "X";
                                Name_c3[i] = Name;
                                burgerStock = burgerStock + 5;
                                System.out.println("Successfully removed the customer!");
                                break;
                            }
                        }
                    }
                }
                case "104", "PCQ" -> {
                    System.out.print("Enter the customer name you want to remove: ");
                    String Name = Input.next();
                    System.out.print("Enter the Queue Number you want to remove (1, 2, or 3): ");
                    int Line = Input.nextInt();
                    int i = 0;
                    if (Line == 1 && cashier1.length <= 2) {
                        if (cashier1[i].equals("O")) {
                            cashier1[i] = ("X");
                            Name_c1[i] = Name;
                            burgerStock = burgerStock + 5;
                            System.out.println("Customer removed !");
                            System.out.println();
                        }
                    } else if (Line == 2 && cashier2.length <= 3) {
                        if (cashier2[i].equals("O")) {
                            cashier2[i] = "X";
                            Name_c2[i] = Name;
                            burgerStock = burgerStock + 5;
                            System.out.println("Customer removed !");
                        }
                    } else if (Line == 3 && cashier3.length <= 5) {
                        if (cashier3[i].equals("O")) {
                            cashier3[i] = "X";
                            Name_c3[i] = Name;
                            burgerStock = burgerStock + 5;
                            System.out.println("Customer removed !");
                        }
                    } else {
                        System.out.println("Invalid Queue Number. Please try again! ");
                    }
                }
                case "105", "VCS" -> {
                    // Sorted and print customer names in alphabetical order
                    System.out.println("Which line do you want to sort ( 1, 2 or 3 ) : ");
                    int sort_line = Input.nextInt();
                    if (sort_line == 1) {

                        bubbleSort(Name_c1);
                        for (String Name : Name_c1) {
                            if ( Name != null) {
                                System.out.println(Name);
                            }
                        }
                    }else if (sort_line == 2) {

                        bubbleSort(Name_c2);
                        for (String Name : Name_c2) {
                            if ( Name != null){
                                System.out.println(Name);
                            }
                        }
                    }else if (sort_line == 3) {

                        bubbleSort(Name_c3);
                        for (String Name : Name_c3) {
                            if ( Name != null){
                                System.out.println(Name);
                            }
                        }
                    } else {
                        System.out.println("Invalid line number.");
                    }
                }
                case "106", "SPD" -> {
                    //Store customer Names in to datafile
                    try (BufferedWriter br = new BufferedWriter(new FileWriter("SaveFile.txt"))) {
                        for (String customerName : Name_c1) {
                            if (customerName != null) {
                                br.write(customerName);
                                br.newLine();
                            }
                        }
                        for (String customerName : Name_c2) {
                            if (customerName != null) {
                                br.write(customerName);
                                br.newLine();
                            }
                        }
                        for (String customerName : Name_c3) {
                            if (customerName != null) {
                                br.write(customerName);
                                br.newLine();
                            }
                        }
                        System.out.println("Customer names saved to file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while saving customer names: " + e.getMessage());
                    }
                }
                case "107", "LPD" -> {
                    //Load customer names from datafile
                    try (BufferedReader br = new BufferedReader(new FileReader("SaveFile.txt"))) {
                        String[] loadedNames = new String[10]; // Assuming a maximum of 100 names

                        int index = 0;
                        String customerName;
                        while ((customerName = br.readLine()) != null && index < loadedNames.length) {
                            loadedNames[index] = customerName;
                            index++;
                        }

                        // Process the loadedNames array as needed
                        for (int i = 0; i < index; i++) {
                            System.out.println(loadedNames[i]);
                        }
                        System.out.println("Customer names loaded from file successfully.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while loading customer names: " + e.getMessage());
                    }

                } case "108", "STK" -> {
                    // Remaining burger stock after served
                    System.out.println("The Remaining Burger Stock : ");
                    System.out.println(burgerStock);
                    while (burgerStock==10){
                        System.out.println("please add burgers to the stock(stock is 10 now)"); //stock allocation
                        break;
                    }
                }
                case "109", "AFS" -> {
                    // Add burgers to the stock
                    while (burgerStock<50){
                        burgerStock++;
                    }
                    System.out.println("Burgers added to stock - Updated stock : " + burgerStock);
                }
                case "999", "EXT" -> {
                    // Exit from the program
                    System.exit(0);

                } default -> {
                    // Invalid option
                    System.out.println("Invalid choice! Please try again.");
                    break;
                }
            }
        }
    }
    private static void bubbleSort(String[] array) {
        int n = array.length;

        // Perform bubble sort algorithm
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent strings and swap if necessary
                if (compareStrings(array[j], array[j + 1]) > 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    } private static int compareStrings (String string1 , String string2) {
        if (string1 == null && string2 == null) {
            return 0;
        } else if (string1 == null) {
            return -1;
        } else if (string2 == null) {
            return 1;
        }
        int len1 = string1.length();
        int len2 = string2.length();
        int minLen = Math.min(len1 , len2);

        // Compare characters of the strings character by character
        for (int i = 0; i < minLen; i++) {
            if (string1.charAt(i) != string2.charAt(i)) {
                return string1.charAt(i) - string2.charAt(i);
            }
        }
        // If all characters match, return the difference in lengths
        return len1 - len2;

    }
}
/*Prompt for customer details (name and burger counts)
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

                                // Add the new customer to the waiting list queue
                                waitingListQueue.add(newCustomer);
                                customerNameList.add(newCustomer);
                                System.out.println("Customer added to Waiting List queue.");

    // Add customer from the waiting list to the queue if available
                                /*if (waitingListQueue.size() > 0) {
                                    Customer nextCustomer = waitingListQueue.remove();
                                    targetQueue.set(i, nextCustomer);
                                    switch (queueNumber) {
                                        case 1:
                                            cashier1[line_1.size() - 1] = "O";
                                            break;
                                        case 2:
                                            cashier2[line_2.size() - 1] = "O";
                                            break;
                                        case 3:
                                            cashier3[line_3.size() - 1] = "O";
                                            break;
                                    }
                                    System.out.println("Next customer from Waiting List added to Queue " + queueNumber + ".");
                                }
 */



