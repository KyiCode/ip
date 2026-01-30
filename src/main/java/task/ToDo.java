package task;

import exceptions.InvalidCommandException;

import java.io.IOException;

public class ToDo extends Task {
    public ToDo(String taskName) throws InvalidCommandException, IOException {
        super(taskName);
    }

    public ToDo(String taskName, int mode) throws InvalidCommandException, IOException {
        super(taskName);
        TaskList.add(this);
    }
}