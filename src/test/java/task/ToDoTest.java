package task;

import exceptions.InvalidCommandFormatException;
import meow.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    private Parser parser;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException, IOException {
        parser = new Parser();
        tempFile = Files.createTempFile("meow-test", ".txt");
        Files.writeString(tempFile, "");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void thinking_addTodo_no_description() throws Exception {
        assertThrows(InvalidCommandFormatException.class, () -> parser.thinking("todo  ", tempFile));
    }

    @Test
    void thinking_addTodo_success() throws Exception {
        String result = parser.thinking("todo read book", tempFile);

        assertTrue(result.contains("Added:"));
        assertTrue(result.contains("read book"));
    }
}
