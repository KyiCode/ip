package meow;

import exceptions.InvalidCommandException;
import exceptions.InvalidMarkingException;
import fileoperator.Storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main class for MrMeow
 */
public class MrMeow {
    public static void main(String[] args)  {
        Parser meow = new Parser();
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
            Storage.load(filePath, meow);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Corrupted Data, Please Check!");
            return;
        }

        while (sc.hasNext()) {
            String text = sc.nextLine();
            try {
                text = meow.thinking(text, filePath);
                System.out.println(text);
            } catch (InvalidCommandException | InvalidMarkingException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (IOException e) {
                System.out.println("File error");
            }
        }
    }
}



