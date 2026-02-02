package meow;

import exceptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
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
    void thinking_emptyInput_returnsEmptyString() throws Exception {
        String result = parser.thinking("", tempFile);
        assertEquals("", result);
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

    @Test
    void mark_Task_success() throws Exception {
        parser.thinking("todo read book", tempFile);
        String result = parser.thinking("mark 1", tempFile);

        assertTrue(result.contains("[X]"));
        assertTrue(result.contains("read book"));
    }

    @Test
    void unmark_Task_success() throws Exception {
        parser.thinking("todo read book", tempFile);
        String result = parser.thinking("unmark 1", tempFile);

        assertFalse(result.contains("[X]"));
        assertTrue(result.contains("read book"));
    }

    @Test
    void thinking_markInvalidIndex_throwsException() {
        assertThrows(
                InvalidMarkingException.class,
                () -> parser.thinking("mark 1", tempFile)
        );
    }

    @Test
    void thinking_adddeadline_no_description() throws Exception {
        assertThrows(InvalidCommandFormatException.class, () -> parser.thinking("deadline  ", tempFile));
    }

    @Test
    void thinking_adddeadline_no_deadline() throws Exception {
        assertThrows(NullDateException.class, () -> parser.thinking("deadline w  ", tempFile));
    }

    @Test
    void thinking_adddeadline_no_deadline_2() throws Exception {
        assertThrows(InvalidDeadLineFormatException.class, () -> parser.thinking("deadline w /by  ", tempFile));
    }

    @Test
    void thinking_adddeadline_no_deadline_3() throws Exception {
        assertThrows(InvalidDeadLineFormatException.class, () -> parser.thinking("deadline /by 2024.12.12  ", tempFile));
    }

    //change to invalid date
    @Test
    void thinking_adddeadline_no_deadline_4() throws Exception {
        assertThrows(InvalidDeadLineFormatException.class, () -> parser.thinking("deadline /by 2024-13-12  ", tempFile));
    }

    @Test
    void thinking_adddeadline_success() throws Exception {
        String result = parser.thinking("deadline dead /by 2026-12-03", tempFile);

        assertTrue(result.contains("dead"));
        assertTrue(result.contains("[D]"));
        assertTrue(result.contains("2026-12-03"));
    }


    @Test
    void thinking_addevent_no_description() throws Exception {
        assertThrows(InvalidCommandFormatException.class, () -> parser.thinking("event  ", tempFile));
    }

    @Test
    void thinking_addevent_no_date() throws Exception {
        assertThrows(NullDateException.class, () -> parser.thinking("event w  ", tempFile));
    }

    @Test
    void thinking_addevent_no_deadline_2() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from  ", tempFile));
    }

    @Test
    void thinking_addevent_no_deadline_3() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from 2024-13-12 /to    ", tempFile));
    }

    @Test
    void thinking_addevent_no_deadline_4() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from 2024-13-12 /to 2024-1-2   ", tempFile));
    }

    @Test
    void thinking_adddevent_success() throws Exception {
        String result = parser.thinking("event w /from 2024-11-12 /to 2024-12-22", tempFile);

        assertTrue(result.contains("w"));
        assertTrue(result.contains("[E]"));
        assertTrue(result.contains("From: 2024-11-12"));
    }

}
