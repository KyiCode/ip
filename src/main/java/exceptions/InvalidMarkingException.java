package exceptions;

/**
 * Thrown when marking command is on a missing task.
 */
public class InvalidMarkingException extends Exception {
    public InvalidMarkingException(){
        super("Task do not exists. \n");
    }
}
