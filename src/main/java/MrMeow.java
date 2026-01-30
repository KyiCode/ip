import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MrMeow {
    public static void main(String[] args)  {
        Parser meowmeow = new Parser();
        Scanner sc = new Scanner(System.in);

        Path dataDir = Paths.get("ip/data");
        Path filePath = Paths.get("ip/data/meow.txt");

        try {
            Files.createDirectories(dataDir);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("File exists, skipping...");
        }

        try {
            TaskList.load(filePath, meowmeow);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Corrupted Data, Please Check!");
            return;
        }

        while (sc.hasNext()) {
            String text = sc.nextLine();
            try {
                meowmeow.thinking(text, filePath);
                System.out.println();
            } catch (InvalidCommandException | InvalidMarkingException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (IOException e) {
                System.out.println("File error");
            }
        }
    }
}



