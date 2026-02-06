package task;

import exceptions.InvalidCommandException;
import exceptions.InvalidEventFormatException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Event Class to represent a Task with a start and end duration.
 */
public class Event extends Task {
    LocalDate from;
    LocalDate to;

    /**
     * Construct an Event instance.
     *
     * @param eventDetails String array of Event details, Event start date and Event end date.
     * @throws InvalidCommandException if Event command is in invalid format.
     * @throws IOException if input string is invalid.
     */
    public Event (String[] eventDetails) throws InvalidCommandException, IOException {
        super(eventDetails[0]);
        
        if (eventDetails[0].isEmpty() || eventDetails[1].isEmpty()) {
            throw new InvalidEventFormatException();
        }

        try {
            this.from = LocalDate.parse(eventDetails[1].split(" /to ")[0]);
            this.to = LocalDate.parse(eventDetails[1].split(" /to ")[1]);
        } catch (Exception e) {
            throw new InvalidEventFormatException();
        }
    }

    /**
     * loads the Event string from Storage File.
     *
     * @param taskName string task details.
     * @param from string event start date.
     * @param to string event end date.
     * @param status completion status of loaded event task.
     * @throws InvalidCommandException if Event command is in invalid format.
     * @throws IOException if input string is invalid.
     */
    public Event (String taskName, String from, String to, boolean status) throws InvalidCommandException, IOException {
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
        if (this.getStatus()) doneStatus = "[E][X] ";
        return doneStatus + this.getTaskName() + " || From: " + from + " To: " + to;
    }
}
