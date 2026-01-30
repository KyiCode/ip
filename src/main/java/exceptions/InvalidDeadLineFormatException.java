package exceptions;

public class InvalidDeadLineFormatException extends InvalidCommandException {
    public InvalidDeadLineFormatException() {
        super("Date Format should be in YYYY-MM-DD or YYYY-MM-DD HH:MM \n");
    }
}
