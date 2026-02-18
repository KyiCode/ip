package task;

import java.io.IOException;

import exceptions.InvalidCommandException;

/**
 * Represents a ToDo task.
 * A ToDo is a simple task with just a name and completion status.
 */
public class ToDo extends Task {

    /**
     * Construct a ToDo task instance.
     *
     * @param taskName Name or details of the task.
     * @throws InvalidCommandException If input command is invalid.
     * @throws IOException If string is invalid.
     */
    public ToDo(String taskName) throws InvalidCommandException, IOException {
        super(taskName);
    }

    /**
     * Loads a ToDo task from storage file.
     *
     * @param taskName Name or details of the task
     * @param status Completion status of loaded task.
     * @throws InvalidCommandException if input command is invalid.
     * @throws IOException if string is invalid.
     */
    public ToDo(String taskName, boolean status) throws InvalidCommandException, IOException {
        super(taskName);
        if (status) {
            this.setDone();
        }
        TaskList.add(this);
    }
}
