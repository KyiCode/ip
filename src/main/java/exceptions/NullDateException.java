package exceptions;

public class NullDateException extends InvalidCommandException {
    public NullDateException() {
        super("Date description should not be empty! \n");
    }
}
