package exceptions;

/**
 * Thrown when event command is in invalid format.
 */
public class InvalidEventFormatException extends InvalidCommandException {
    public InvalidEventFormatException() {
        super("Event Format should be in Event ... /from YYYY-MM-DD /to YYYY-MM-DD \n");
    }

    public InvalidEventFormatException(String msg) {
        super(msg);
    }


}
