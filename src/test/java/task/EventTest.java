package task;

import exceptions.InvalidCommandFormatException;
import exceptions.InvalidEventFormatException;
import exceptions.NullDateException;
import meow.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
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
    void event_noDescription_exceptionThrown() throws Exception {
        assertThrows(InvalidCommandFormatException.class, () -> parser.thinking("event  "));
    }

    @Test
    void event_inValidFormat_exceptionThrown() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w  "));
    }

    @Test
    void event_inValidFormatTwo_exceptionThrown() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from  "));
    }

    @Test
    void event_inValidFormatThree_exceptionThrown() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from 2024-13-12 /to    "));
    }

    @Test
    void event_inValidFormatFour_exceptionThrown() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from 2024-12-12 /to 2025-1-2   "));
    }

    @Test
    void event_trailingSpaces_exceptionThrown() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from 2024-13-12 /to 2024-10-20   "));
    }

    //throws wrong exception
    @Test
    void event_inValidDate_exceptionThrown() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from 2024-13-12 /to 2024-10-20"));
    }

    //should throw an exception
    @Test
    void event_fromDateLaterThanToDate_exceptionThrown() throws Exception {
        assertThrows(InvalidEventFormatException.class, () -> parser.thinking("event w /from 2024-12-12 /to 2024-10-20"));
    }

    @Test
    void event_addEvent_success() throws Exception {
        String result = parser.thinking("event test /from 2024-11-12 /to 2024-12-22");

        assertTrue(result.contains("test"));
        assertTrue(result.contains("[E][ ]"));
        assertTrue(result.contains("From: 2024-11-12"));
        assertTrue(result.contains("To: 2024-12-22"));
    }


}
