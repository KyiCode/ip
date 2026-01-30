package exceptions;

public class InvalidCommandFormatException extends  InvalidCommandException {
    public InvalidCommandFormatException() {
        super("Check command Format! /help for lists of commands!");
    }
}