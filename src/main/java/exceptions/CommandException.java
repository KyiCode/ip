package exceptions;

/**
 * Any command errors.
 */
class CommandException extends Exception {
    /**
     * construct a command exception.
     *
     * @param msg to be output.
     */
    public CommandException(String msg) {
        super(msg);
     }
}









