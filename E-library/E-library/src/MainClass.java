import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class MainClass {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    static boolean loggedIn = false;

    public static void main(String[] args) throws IOException {
        System.out.println(YELLOW + "|========================== WELCOME TO MY E-LIBRARY! =========================|" + RESET);
        System.out.println(GREEN + "\nBelow you can see what characteristics e-library has. Choose which one you want.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int mainChoice;
            if (!loggedIn) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                mainChoice = getUserChoice(scanner);
                switch (mainChoice) {
                    case 1:
                        UserRegistration.registerUser();
                        break;
                    case 2:
                        loggedIn = UserRegistration.loginUser();
                        break;
                    default:
                        System.out.println(RED+"Invalid choice. Please try again."+RESET);
                        break;
                }
            } else {
                System.out.println(YELLOW+"Here are the options."+RESET);
                System.out.println(GREEN+"1. List of Books");
                System.out.println("2. Categories");
                System.out.println("3. Add a book");
                System.out.println("4. Favorite books");
                System.out.println("5. Delete a book");
                System.out.println("6. Logout");
                System.out.println("7. Exit");
                mainChoice = getUserChoice(scanner);
                switch (mainChoice) {
                    case 1:
                        System.out.println(  "The list of the Books:");
                        Books.readAndPrintBooks();
                        System.out.println("\n");
                        break;
                    case 2:
                        new BooksOperations().categories();
                        break;
                    case 3:
                        new BooksOperations().addBook();
                        break;
                    case 4:
                        new BooksOperations().favoriteBooks();
                        break;
                    case 5:
                        new BooksOperations().deleteBook();
                        break;
                    case 6:
                        System.out.println("Logging out...");
                        loggedIn = false;
                        break;
                    case 7:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }



    private static int getUserChoice(Scanner scanner) {
        System.out.print("Choose an option: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please, enter a valid integer.");
            scanner.nextLine();
            return -1;
        }
    }
}




