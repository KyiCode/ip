package task;

import exceptions.InvalidCommandException;
import exceptions.InvalidMarkingException;
import meow.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeadLineTest {

    @Test
    public void deadLineConstructorTest() throws InvalidCommandException, IOException, InvalidMarkingException {
        Parser test = new Parser();
        Path filePath = Paths.get("ip/data/testMeow.txt");

        try {
            test.thinking(" ", filePath);
        } catch (Exception e) {
            e.getMessage();
        }

        try {
            test.thinking("deadline return book  ", filePath);
        } catch (Exception e) {
            e.getMessage();
        }



        try {
            test.thinking(" deadline return book /by ", filePath);
        } catch (Exception e) {
            e.getMessage();
        }

        try {
            test.thinking(" deadline return book /by 20 26/12/01-", filePath);
        } catch (Exception e) {
            e.getMessage();
        }

        try {
            test.thinking("deadline run home /by 2026/12/0 ", filePath);
        } catch (Exception e) {
            e.getMessage();
        }

        try {
            test.thinking(" deadline run home /by 2026/12/01", filePath);

        } catch (Exception e) {
            e.getMessage();
        }












    }

}
