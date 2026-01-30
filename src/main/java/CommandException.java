class CommandException extends Exception {
    public CommandException(String msg) { super(msg); }
}


class InvalidCommandException extends CommandException {
    public InvalidCommandException() {
        super("invalid command! \n");
    }

    public InvalidCommandException(String msg) {
        super(msg);
    }
}

class NullTaskDescriptionException extends InvalidCommandException {
    public NullTaskDescriptionException() {
        super("task description is empty! \n");
    }
}

class NullDateException extends InvalidCommandException {
    public NullDateException() {
        super("Date description should not be empty! \n");
    }
}

class InvalidMarkingException extends Exception {
    public InvalidMarkingException(){
        super("Task do not exists. \n");
    }
}

class InvalidDeadLineFormatException extends InvalidCommandException {
    public InvalidDeadLineFormatException() {
        super("Date Format should be in YYYY-MM-DD or YYYY-MM-DD HH:MM \n");
    }
}

class InvalidEventFormatException extends InvalidCommandException {
    public InvalidEventFormatException() {
        super("Event Format should be in Event ... /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM\n");
    }
}