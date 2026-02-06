package exceptions;

/**
 * Thrown when task description is missing.
 */
public class NullTaskDescriptionException extends InvalidCommandException {
    public NullTaskDescriptionException() {
        super("task description is empty! \n");
    }
}
