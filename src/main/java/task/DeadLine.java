package task;

import exceptions.InvalidCommandException;
import exceptions.InvalidDeadLineFormatException;
import exceptions.NullDateException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * DeadLine Class to represent Tasks with a Deadline.
 */
public class DeadLine extends Task {
    LocalDate deadLine;

    /**
     * Construct a DeadLine instance.
     *
     * @param taskDetails String Array of Task Details and Task Deadline.
     * @throws InvalidCommandException If Command input is in wrong format.
     * @throws IOException If task details String is not valid.
     */
    public DeadLine(String[] taskDetails) throws InvalidCommandException, IOException {
        super(taskDetails[0]);
        if (taskDetails.length != 2) {
            throw new NullDateException();
        }

        try {
            this.deadLine = LocalDate.parse(taskDetails[1]);
        } catch (Exception e) {
            throw new InvalidDeadLineFormatException();
        }
    }

    /**
     * Loading a Deadline Task from Storage file.
     *
     * @param taskDetails String of Task Description.
     * @param deadLineDetails String of Task Deadline.
     * @param status Completion status of loaded deadline task.
     * @throws InvalidCommandException If file is corrupted with invalid tasks.
     * @throws IOException If string from task is invalid.
     */
    public DeadLine(String taskDetails, String deadLineDetails, boolean status) throws InvalidCommandException, IOException {
        super(taskDetails);
        this.deadLine = LocalDate.parse(deadLineDetails);
        if (status) {
            this.setDone();
        }
        TaskList.add(this);
    }

    @Override
    public String toString() {
        String doneStatus = "[D][ ] ";
        if (this.getStatus()) {
            doneStatus = "[D][X] ";
        }
        return doneStatus + this.getTaskName() + " || Deadline: " + deadLine.toString();
    }
}

