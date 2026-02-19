package exceptions;


/**
 * Thrown when deadline command is in invalid format.
 */
public class InvalidDeadLineFormatException extends InvalidCommandException {
    public InvalidDeadLineFormatException() {
        super("Date Format should be in YYYY-MM-DD \n");
    }
}
