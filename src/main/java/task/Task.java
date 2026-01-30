package task;

import exceptions.NullTaskDescriptionException;
import java.io.IOException;

public class Task {
    private String taskName;
    private boolean done = false;

    public Task (String taskName) throws NullTaskDescriptionException, IOException {
        this.taskName = taskName;
        if (this.taskName == null) {
            throw new NullTaskDescriptionException();
        }
    }

    public void setDone() {
        this.done = true;
    }

    public void setNotDone() {
        this.done = false;
    }

    public boolean status() {
        return this.done;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String doneStatus = "[T][ ] ";
        if (this.done) doneStatus = "[T][X] ";
        return doneStatus + this.taskName;
    }
}