package task;

import java.io.IOException;
import java.time.LocalDate;

import exceptions.InvalidCommandException;
import exceptions.InvalidEventFormatException;

/**
 * Event Class to represent a Task with a start and end duration.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Construct an Event instance.
     *
     * @param eventDetails String array of Event details, Event start date and Event end date.
     * @throws InvalidCommandException If Event command is in invalid format.
     * @throws IOException If input string is invalid.
     */
    public Event(String[] eventDetails) throws InvalidCommandException, IOException {
        super(eventDetails[0]);
        if (eventDetails[0].isEmpty() || eventDetails[1].isEmpty()) {
            throw new InvalidEventFormatException();
        }

        try {
            this.from = LocalDate.parse(eventDetails[1].split(" /to ")[0].trim());
            this.to = LocalDate.parse(eventDetails[1].split(" /to ")[1].trim());
        } catch (Exception e) {
            throw new InvalidEventFormatException();
        }

        if (from.isAfter(to)) {
            throw new InvalidEventFormatException("From date is after To Date!");
        }

    }

    /**
     * Loads the Event Task from Storage File.
     *
     * @param taskName String of task description.
     * @param from String of event start date.
     * @param to String of event end date.
     * @param status Completion status of loaded event task.
     * @throws InvalidCommandException If Event command is in invalid format.
     * @throws IOException If input string is invalid.
     */
    public Event(String taskName, String from, String to, boolean status) throws InvalidCommandException, IOException {
        super(taskName);
        if (status) {
            this.setDone();
        }
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        TaskList.add(this);
    }

    @Override
    public String toString() {
        String doneStatus = "[E][ ] ";
        if (this.getStatus()) {
            doneStatus = "[E][X] ";
        }
        return doneStatus + this.getTaskName() + " || From: " + from + " To: " + to;
    }
}
