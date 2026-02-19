package task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import exceptions.InvalidCommandFormatException;
import meow.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    private Parser parser;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException, IOException {
        tempFile = Files.createTempFile("meow-test", ".txt");
        parser = new Parser(tempFile);
        Files.writeString(tempFile, "");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void thinking_addTodo_noDescription() throws Exception {
        assertThrows(InvalidCommandFormatException.class, () -> parser.thinking("todo  "));
    }

    @Test
    void thinking_addTodo_success() throws Exception {
        String result = parser.thinking("todo read book");

        assertTrue(result.contains("Added:"));
        assertTrue(result.contains("read book"));
    }
}
