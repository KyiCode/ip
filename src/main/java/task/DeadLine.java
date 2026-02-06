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
    LocalDate deadline;

    /**
     * Construct a DeadLine instance.
     *
     * @param taskDetails String Array of Task Details and Task Deadline.
     * @throws InvalidCommandException if Command input is in wrong format.
     * @throws IOException if task details String is not valid.
     */
    public DeadLine(String[] taskDetails) throws InvalidCommandException, IOException {
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

    /**
     * Loading a DeadlineTask from file.
     *
     * @param taskDetails string of Task Description.
     * @param deadLineDetails string of Task Deadline.
     * @param status completion status of loaded deadline task.
     * @throws InvalidCommandException if file is corrupted with invalid tasks.
     * @throws IOException if string from task is invalid.
     */
    public DeadLine(String taskDetails, String deadLineDetails, boolean status) throws InvalidCommandException, IOException {
        super(taskDetails);
        this.deadline = LocalDate.parse(deadLineDetails);
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
        return doneStatus + this.getTaskName() + " || Deadline: " + deadline.toString();
    }
}

