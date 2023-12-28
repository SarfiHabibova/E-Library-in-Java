import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

interface BooksInterface {


    void categories();

    void addBook();

    void deleteBook();

    void favoriteBooks();

}

public class BooksOperations implements BooksInterface{

    Scanner scanner = new Scanner(System.in);



    @Override
    public void categories() {
        System.out.println("In my library books are divided into 5 categories(genres).\n1. Modern classic\n2. Classic\n3. Fantastic\n4. Philosophy\n5. Romance");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String filePath1 = "src/Books.txt";
        switch (choice){
            case 1:
                try {
                    List<String> lines1 = Files.readAllLines(Paths.get(filePath1));

                    for (String line : lines1) {

                        String[] parts = line.split("/");

                        if (parts.length >= 3 && parts[2].trim().equalsIgnoreCase("Modern Classic")) {
                            System.out.println(parts[1].trim());
                        }
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case 2:

                try {
                    List<String> lines1 = Files.readAllLines(Paths.get(filePath1));

                    for (String line : lines1) {

                        String[] parts = line.split("/");

                        if (parts.length >= 3 && parts[2].trim().equalsIgnoreCase("Classic")) {
                            
                            System.out.println(parts[1].trim());
                        }
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            case 3:
                try {
                    List<String> lines1 = Files.readAllLines(Paths.get(filePath1));

                    for (String line : lines1) {

                        String[] parts = line.split("/");

                        if (parts.length >= 3 && parts[2].trim().equalsIgnoreCase("Fantastic")) {

                            System.out.println(parts[1].trim());
                        }
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            case 4:
                try {
                    List<String> lines1 = Files.readAllLines(Paths.get(filePath1));

                    for (String line : lines1) {

                        String[] parts = line.split("/");

                        if (parts.length >= 3 && parts[2].trim().equalsIgnoreCase("Philosophy")) {

                            System.out.println(parts[1].trim());
                        }
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            case 5:
                try {
                    List<String> lines1 = Files.readAllLines(Paths.get(filePath1));

                    for (String line : lines1) {

                        String[] parts = line.split("/");

                        if (parts.length >= 3 && parts[2].trim().equalsIgnoreCase("Romance")) {

                            System.out.println(parts[1].trim());
                        }
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }
    private List<String> favoriteBooksList = new ArrayList<>();


    private List<String> getFavoriteBooksList() {
        return new ArrayList<>(favoriteBooksList);
    }


    private void setFavoriteBooksList(List<String> favoriteBooksList) {
        this.favoriteBooksList = new ArrayList<>(favoriteBooksList);
    }
    private List<String> readFavorites(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void writeFavorites(String filePath, List<String> favoriteBooksList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String book : favoriteBooksList) {
                writer.write(book + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }




    public void favoriteBooks() {
        if (!MainClass.loggedIn) {
            System.out.println("Please log in to view your favorite books.");
            return;
        }

        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        String favoritesFilePath = "src/" + username + "_favorites.txt";

        List<String> favoriteBooksList = readFavorites(favoritesFilePath);

        System.out.println("Favorite Books for " + username + ":");

        if (favoriteBooksList.isEmpty()) {
            System.out.println("No favorite books added yet.");
        } else {
            for (String book : favoriteBooksList) {
                System.out.println(book);
            }
        }

        System.out.println("Do you want to add a book to your favorites? (yes/no)");
        String addChoice = scanner.nextLine().toLowerCase();

        if ("yes".equals(addChoice)) {
            System.out.println("Enter the book number to add to favorites:");
            int bookNumberToAdd;

            try {
                bookNumberToAdd = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); 
                return;
            }


            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get("src/Books.txt"));
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
                return;
            }


            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\. ");
                if (parts.length < 1) {
                    continue;
                }

                try {
                    int currentBookNumber = Integer.parseInt(parts[0]);


                    if (currentBookNumber == bookNumberToAdd) {
                        String bookToAdd = parts[1].trim();
                        favoriteBooksList.add(bookToAdd);
                        writeFavorites(favoritesFilePath, favoriteBooksList);
                        System.out.println("Book added to favorites successfully!");
                        return;
                    }
                } catch (NumberFormatException ignored) {

                }
            }

            System.out.println("Book with number " + bookNumberToAdd + " not found.");
        }
    }

    @Override
    public void addBook() {
        int entryNumber = getNextEntryNumber();
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        String favoritesFilePath = "src/" + username + "_favorites.txt";

        System.out.println("Enter Author Name:");
        String authorName = scanner.nextLine();

        System.out.println("Enter Book Name:");
        String bookName = scanner.nextLine();

        System.out.println("Enter Genre:");
        String genre = scanner.nextLine();


        String bookInfo = entryNumber + ". \"" + authorName + "\" / \"" + bookName + "\" / \"" + genre + "\"";


        entryNumber++;


        try (FileWriter writer = new FileWriter("src/Books.txt", true)) {
            writer.write(bookInfo + "\n");
            System.out.println("Book added successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter("src/Books.txt", true)) {
            writer.write(bookInfo + "\n");
            System.out.println("Book added successfully!");





            List<String> userFavoriteBooksList = readFavorites(favoritesFilePath);
            userFavoriteBooksList.add(bookInfo);
            writeFavorites(favoritesFilePath, userFavoriteBooksList);

            setFavoriteBooksList(userFavoriteBooksList);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }}

    @Override
    public void deleteBook() {
        System.out.println("Enter the book number to delete:");
        int bookNumberToDelete;

        try {
            bookNumberToDelete = scanner.nextInt();
            scanner.nextLine(); 
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.nextLine();
            return;
        }

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("src/Books.txt"));
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
            return;
        }

        StringBuilder modifiedContent = new StringBuilder();

      
        boolean bookFound = false;

      
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue; 
            }

            String[] parts = line.split("\\. ");
            if (parts.length < 1) {
                continue; 
            }

            try {
                int currentBookNumber = Integer.parseInt(parts[0]);

            
                if (currentBookNumber == bookNumberToDelete) {
                    bookFound = true;
                    System.out.println("Book found and deleted: " + line);
                } else {
                 
                    modifiedContent.append(line).append("\n");
                }
            } catch (NumberFormatException ignored) {
               
            }
        }


        if (!bookFound) {
            System.out.println("Book with number " + bookNumberToDelete + " not found.");
            return;
        }

      
        try (FileWriter writer = new FileWriter("src/Books.txt")) {
            writer.write(modifiedContent.toString());
            System.out.println("Book deleted successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }



    private int getNextEntryNumber() {
        int lastEntryNumber = 11;

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/Books.txt"));

            if (!lines.isEmpty()) {
                String lastLine = lines.get(lines.size() - 1);
                String[] parts = lastLine.split("\\. ");

                
                if (parts.length >= 1 && !parts[0].isEmpty()) {
                    lastEntryNumber = Integer.parseInt(parts[0]) + 1;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return lastEntryNumber;
    }
}
