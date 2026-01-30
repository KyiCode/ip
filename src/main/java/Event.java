import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

class Event extends Task {
    LocalDate from;
    LocalDate to;
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

    public Event (String taskName, String from, String to, int mode) throws InvalidCommandException, IOException {
        super(taskName);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        TaskList.add(this);
    }

    @Override
    public String toString() {
        String doneStatus = "[E][ ] ";
        if (this.status()) doneStatus = "[E][X] ";
        return doneStatus + this.getTaskName() + " || From: " + from + " To: " + to;
    }
}
