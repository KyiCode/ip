import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Meow {
    public static void main(String[] args) throws IOException {
        Brain meowmeow = new Brain();
        Scanner sc = new Scanner(System.in);


        Path dataDir = Paths.get("ip/data");
        Path filePath = Paths.get("ip/data/meow.txt");

        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
            Files.createFile(filePath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try {
            TaskList.load(filePath, meowmeow);
        } catch (Exception e) {
            return;
        }

        while (sc.hasNext()) {
            String text = sc.nextLine();
            try {
                meowmeow.thinking(text, filePath);
            } catch (InvalidCommandException | InvalidMarkingException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}






class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("invalid command!");
    }

    public InvalidCommandException(String msg) {
        super(msg);
    }
}

class NullTaskDescriptionException extends InvalidCommandException {
    public NullTaskDescriptionException() {
        super("task description is empty!");
    }
}

class NullDateException extends InvalidCommandException {
    public NullDateException() {
        super("Date description should not be empty!");
    }
}

class InvalidMarkingException extends Exception {
    public InvalidMarkingException(){
        super("Task do not exists.");
    }
}
