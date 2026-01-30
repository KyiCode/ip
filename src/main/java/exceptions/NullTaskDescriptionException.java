package exceptions;

public class NullTaskDescriptionException extends InvalidCommandException {
    public NullTaskDescriptionException() {
        super("task description is empty! \n");
    }
}