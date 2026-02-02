package exceptions;

/**
 * Thrown when input command is invalid.
 */
public class InvalidCommandException extends CommandException {

    /**
     * constructor
     */
    public InvalidCommandException() {
        super("invalid command! \n");
    }

    /**
     * constructor
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
