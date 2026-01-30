package exceptions;

public class InvalidMarkingException extends Exception {
    public InvalidMarkingException(){
        super("Task do not exists. \n");
    }
}
