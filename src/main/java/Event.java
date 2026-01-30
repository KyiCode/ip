import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

class Event extends Task {
    EventDateTime from;
    EventDateTime to;
    public Event (String[] eventDetails) throws InvalidCommandException, IOException {
        super(eventDetails[0]);
        if (eventDetails.length != 2) {
            throw new InvalidEventFormatException();
        }
        eventDetails = eventDetails[1].split(" /to ");
        if (eventDetails.length != 2) {
            throw new InvalidEventFormatException();
        }
        this.from = new EventDateTime(eventDetails[0]);
        this.to = new EventDateTime(eventDetails[1]);
        TaskList.add(this);
        System.out.println("Added: " + this.toString());
        System.out.println(TaskList.getListSize() + " tasks in list.");
    }

    public Event (String taskName, String from, String to, int mode) throws InvalidCommandException, IOException {
        super(taskName);
        this.from = new EventDateTime(from);
        this.to = new EventDateTime(to);
        TaskList.add(this);
    }

    @Override
    public String toString() {
        String doneStatus = "[E][ ] ";
        if (this.status()) doneStatus = "[E][X] ";
        return doneStatus + this.getTaskName() + " || From: " + from + " To: " + to;
    }
}

class EventDateTime {
    LocalDateTime eventDateTime;
    LocalDate eventDate;
    public EventDateTime(String eventDetails) throws InvalidEventFormatException {
        String[] details = eventDetails.split("[- :T]");
        if (details.length != 3 && details.length != 5) {
            throw new InvalidEventFormatException();
        }

        try {
            int year = Integer.parseInt(details[0]);
            int month = Integer.parseInt(details[1]);
            int date = Integer.parseInt(details[2]);
            if (details.length == 5) {
                int hour = Integer.parseInt(details[3]);
                int minute = Integer.parseInt(details[4]);
                eventDateTime = LocalDateTime.of(year, month, date, hour, minute);
            } else {
                eventDate = LocalDate.of(year, month, date);
            }
        } catch (Exception e) {
            throw new InvalidEventFormatException();
        }
    }

    public Temporal getevent() {
        if (eventDate == null) {
            return eventDateTime;
        }
        return eventDate;
    }

    @Override
    public String toString() {
        return getevent().toString();
    }
}
