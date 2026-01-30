import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Arrays;


class DeadLineTask extends Task {
    DeadLine deadline;
    public DeadLineTask (String[] taskDetails) throws InvalidCommandException, IOException {
        super(taskDetails[0]);
        if (taskDetails.length != 2) {
            throw new NullDateException();
        }
        this.deadline = new DeadLine(taskDetails[1]);

        System.out.println("Added: " + this.toString());
        System.out.println(TaskList.getListSize() + " tasks in list.");
    }

    public DeadLineTask (String taskDetails, String deadLineDetails, int mode) throws InvalidCommandException, IOException {
        super(taskDetails);
        this.deadline = new DeadLine(deadLineDetails);
        TaskList.add(this);
    }



    @Override
    public String toString() {
        String doneStatus = "[D][ ] ";
        if (this.status()) doneStatus = "[D][X] ";
        return doneStatus + this.getTaskName() + " || Deadline: " + deadline.toString();
    }
}

class DeadLine {
    LocalDateTime deadLineDateTime;
    LocalDate deadLineDate;
    public DeadLine(String deadLineDetails) throws InvalidDeadLineFormatException {
        String[] details = deadLineDetails.split("[- :T]");
        if (details.length != 3 && details.length != 5) {
            throw new InvalidDeadLineFormatException();
        }

        try {
            int year = Integer.parseInt(details[0]);
            int month = Integer.parseInt(details[1]);
            int date = Integer.parseInt(details[2]);
            if (details.length == 5) {
                int hour = Integer.parseInt(details[3]);
                int minute = Integer.parseInt(details[4]);
                deadLineDateTime = LocalDateTime.of(year, month, date, hour, minute);
            } else {
                deadLineDate = LocalDate.of(year, month, date);
            }
        } catch (Exception e) {
            throw new InvalidDeadLineFormatException();
        }
    }

    public Temporal getDeadLine() {
        if (deadLineDate == null) {
            return deadLineDateTime;
        }
        return deadLineDate;
    }

    @Override
    public String toString() {
        return getDeadLine().toString();
    }
}