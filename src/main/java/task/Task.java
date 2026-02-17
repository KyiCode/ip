package task;

import exceptions.NullTaskDescriptionException;

import java.io.IOException;

/**
 * Parent class of all task classes.
 */
public class Task {
    private String taskName;
    private boolean isDone = false;

    /**
     * Construct a task instance.
     *
     * @param taskName Task description.
     * @throws NullTaskDescriptionException Thrown when no description is given.
     * @throws IOException Thrown when invalid.
     */
    public Task(String taskName) throws NullTaskDescriptionException, IOException {
        this.taskName = taskName;
        if (this.taskName == null) {
            throw new NullTaskDescriptionException();
        }
    }

    /**
     * Sets task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets task as not completed.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the completion status of task.
     *
     * @return True if task is completed.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns Task Description.
     *
     * @return String Task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String doneStatus = "[T][ ] ";
        if (this.isDone) {
            doneStatus = "[T][X] ";
        }
        return doneStatus + this.taskName;
    }
}
