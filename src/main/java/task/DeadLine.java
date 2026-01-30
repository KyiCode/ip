package task;

import exceptions.InvalidCommandException;
import exceptions.InvalidDeadLineFormatException;
import exceptions.NullDateException;

import java.io.IOException;
import java.time.LocalDate;

public class DeadLine extends Task {
    LocalDate deadline;
    public DeadLine (String[] taskDetails) throws InvalidCommandException, IOException {
        super(taskDetails[0]);
        if (taskDetails.length != 2) {
            throw new NullDateException();
        }

        try {
            this.deadline = LocalDate.parse(taskDetails[1]);
        } catch (Exception e) {
            throw new InvalidDeadLineFormatException();
        }
    }

    public DeadLine (String taskDetails, String deadLineDetails, int mode) throws InvalidCommandException, IOException {
        super(taskDetails);
        this.deadline = LocalDate.parse(deadLineDetails);
        TaskList.add(this);
    }

    @Override
    public String toString() {
        String doneStatus = "[D][ ] ";
        if (this.status()) doneStatus = "[D][X] ";
        return doneStatus + this.getTaskName() + " || Deadline: " + deadline.toString();
    }
}

