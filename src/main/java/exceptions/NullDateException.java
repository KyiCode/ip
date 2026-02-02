package exceptions;

/**
 * Thrown when date description is missing.
 */
public class NullDateException extends InvalidCommandException {
    public NullDateException() {
        super("Date description should not be empty! \n");
    }
}
