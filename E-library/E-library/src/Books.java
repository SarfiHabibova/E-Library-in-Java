import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Books {
    public static void readAndPrintBooks() {
        String filePath = "src/Books.txt";

        Path path = Paths.get(filePath);

        try {
            Files.lines(path).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
