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
 * Main MrMeow class
 */
public class MrMeow {
    Parser parser;
    Path dataDir;
    Path filePath;

    public MrMeow() {
        dataDir = Paths.get("ip/data");
        filePath = Paths.get("ip/data/meow.txt");
        parser = new Parser(filePath);
        try {
            Files.createDirectories(dataDir);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            Storage.load(filePath, parser);
        } catch (IOException e) {
            System.out.println("File exists, skipping...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Corrupted Data, Please Check!");
        }
    }

    public String getResponse(String input) {
        String output = null;
        try {
            output = parser.thinking(input);
        } catch (InvalidCommandException | InvalidMarkingException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "File error";
        }
        return output;
    }

}



