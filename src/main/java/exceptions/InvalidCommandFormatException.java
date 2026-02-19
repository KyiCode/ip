package exceptions;


/**
 * Thrown when input command is of invalid format.
 */
public class InvalidCommandFormatException extends InvalidCommandException {
    public InvalidCommandFormatException() {
        super("Check command Format! Input help for lists of commands!");
    }
}
