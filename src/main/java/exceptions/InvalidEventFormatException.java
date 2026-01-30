package exceptions;

public class InvalidEventFormatException extends InvalidCommandException {
    public InvalidEventFormatException() {
        super("Event Format should be in Event ... /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\n");
    }
}