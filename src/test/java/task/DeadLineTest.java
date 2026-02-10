package task;

import exceptions.InvalidCommandFormatException;
import exceptions.InvalidDeadLineFormatException;
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

public class DeadLineTest {
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
    void deadLine_noDescription_exceptionThrown() throws Exception {
        assertThrows(InvalidCommandFormatException.class,
                () -> parser.thinking("deadline  ", tempFile));
    }


    //technically not null date?
    @Test
    void deadLine_noDescriptionTwo_exceptionThrown() throws Exception {
        assertThrows(NullDateException.class,
                () -> parser.thinking("deadline /by 2024.12.12  ", tempFile));
    }

    @Test
    void deadLine_noDeadLineCommand_exceptionThrown() throws Exception {
        assertThrows(NullDateException.class,
                () -> parser.thinking("deadline test  ", tempFile));
    }

    @Test
    void deadLine_noDeadLineOne_exceptionThrown() throws Exception {
        assertThrows(NullDateException.class,
                () -> parser.thinking("deadline test /by", tempFile));
    }

    @Test
    void deadLine_noDeadLineTwo_exceptionThrown() throws Exception {
        assertThrows(NullDateException.class,
                () -> parser.thinking("deadline test /by ", tempFile));
    }

    @Test
    void deadLine_inValidDeadLineTwo_exceptionThrown() throws Exception {
        assertThrows(InvalidDeadLineFormatException.class,
                () -> parser.thinking("deadline test /by 2024-13-12  ", tempFile));
    }

    @Test
    void deadLine_test_noTrailingSpace() throws Exception {
        assertThrows(InvalidDeadLineFormatException.class,
                () -> parser.thinking("deadline test /by 2026-12-12  ", tempFile));
    }

    @Test
    void thinking_adddeadline_success() throws Exception {
        String result = parser.thinking("deadline test /by 2026-12-03", tempFile);
        System.out.println(result);
        assertTrue(result.contains("[D][ ] test"));
        assertTrue(result.contains("2026-12-03"));
    }
}

