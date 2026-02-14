package meow;

import exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException, IOException {
        TaskList.reset();
        tempFile = Files.createTempFile("meow-test", ".txt");
        parser = new Parser(tempFile);
        Files.writeString(tempFile, "");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void thinking_emptyInput_returnsEmptyString() throws Exception {
        String result = parser.thinking("");
        assertEquals("", result);
    }

    @Test
    void markTest_Task_success() throws Exception {
        parser.thinking("todo test1");
        parser.thinking("todo test2");
        String result = parser.thinking("mark 1");

        assertTrue(result.contains("[X]"));
        assertTrue(result.contains("test1"));

        result = parser.thinking("mark 2");

        assertTrue(result.contains("[X]"));
        assertTrue(result.contains("test2"));
    }

    @Test
    void unmarkTest_task_success() throws Exception {
        parser.thinking("todo test1");
        parser.thinking("todo test2");
        String result = parser.thinking("mark 1");
        result = parser.thinking("unmark 1");

        assertFalse(result.contains("[X]"));
        assertTrue(result.contains("test1"));

        result = parser.thinking("mark 2");
        result = parser.thinking("unmark 2");

        assertFalse(result.contains("[X]"));
        assertTrue(result.contains("test2"));
    }

    @Test
    void list_test_success() throws Exception {
        parser.thinking("todo test1");
        parser.thinking("todo test2");
        parser.thinking("todo test3");


        String result1 = parser.thinking("find test1");
        String result2 = parser.thinking("find test2");
        String result3 = parser.thinking("find test3");

        assertTrue(result1.contains("test1"));
        assertTrue(result2.contains("test2"));
        assertTrue(result3.contains("test3"));
    }

    @Test
    void delete_success() throws Exception {
        parser.thinking("todo test1");
        parser.thinking("todo test2");
        parser.thinking("todo test3");
        parser.thinking("delete 2");
        String result = parser.thinking("find test2");

        assertTrue(result.contains("No Task Found"));
    }

    @Test
    void thinking_markInvalidIndex_throwsException() {
        assertThrows(
                InvalidMarkingException.class,
                () -> parser.thinking("mark 1")
        );
    }
}
