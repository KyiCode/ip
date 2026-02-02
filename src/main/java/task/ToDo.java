package task;

import exceptions.InvalidCommandException;

import java.io.IOException;

/**
 * Class to represent a task.
 */
public class ToDo extends Task {

    /**
     * Construct a task instance.
     *
     * @param taskName string of Task details.
     * @throws InvalidCommandException if input command is invalid.
     * @throws IOException if string is invalid.
     */
    public ToDo(String taskName) throws InvalidCommandException, IOException {
        super(taskName);
    }

    /**
     * Loads a task String from storage file.
     *
     * @param taskName string of Task details.
     * @param mode any integer to overload constructor.
     * @throws InvalidCommandException if input command is invalid.
     * @throws IOException if string is invalid.
     */
    public ToDo(String taskName, int mode) throws InvalidCommandException, IOException {
        super(taskName);
        TaskList.add(this);
    }
}