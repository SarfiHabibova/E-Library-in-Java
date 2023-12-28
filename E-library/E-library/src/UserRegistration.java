import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


class User {
    String name;
    String password;

    User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

public class UserRegistration {

    private static boolean checkExistingUser(String name, boolean printMessage) {
        try (Scanner scanner = new Scanner(new File("Users.txt"))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                String existingName = parts[0].trim();
                if (existingName.equals(name)) {
                    if (printMessage) {
                        System.out.println("User with the same name already exists.");
                    }
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    static void registerUser() {
        try (PrintWriter usersWriter = new PrintWriter(new FileWriter("Users.txt", true))) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your Name: ");
            String name = scanner.nextLine();

            if (checkExistingUser(name, true)) {
                return;
            }

            System.out.print("Define a Password: ");
            String password = scanner.nextLine();

            User newUser = new User(name, password);

            usersWriter.println(newUser.name + ":" + newUser.password);
            System.out.println("User registered successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static boolean loginUser() throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your Name: ");
            String name = scanner.nextLine();

            if (!checkExistingUser(name, false)) {
                System.out.println("User does not exist. Please register first.");
                return false;
            }

            System.out.print("Enter your Password: ");
            String password = scanner.nextLine();

            if (validateCredentials(name, password)) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Invalid credentials. Login failed.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please enter a valid value.");
        }
        return false;
    }
    static boolean validateCredentials(String name, String password) {
        try (Scanner scanner = new Scanner(new File("Users.txt"))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(":");
                String existingName = parts[0].trim();
                String existingPassword = parts[1].trim();
                if (existingName.equals(name) && existingPassword.equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return false;
    }

}

