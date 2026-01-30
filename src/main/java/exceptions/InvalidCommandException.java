package exceptions;

public class InvalidCommandException extends CommandException {
    public InvalidCommandException() {
        super("invalid command! \n");
    }

    public InvalidCommandException(String msg) {
        super(msg);
    }
}
