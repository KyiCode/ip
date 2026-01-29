import java.io.IOException;
import java.time.LocalDateTime;


class DeadLineTask extends Task {
    DeadLine deadline = " ";
    public DeadLineTask (String[] taskName) throws InvalidCommandException, IOException {
        super(taskName[0]);
        this.deadline = taskName[1];
        if (this.deadline == null) throw new NullDateException();
        System.out.println("Added: " + this.toString());
        System.out.println(TaskList.getListSize() + " tasks in list.");
    }

    public DeadLineTask (String taskName, String deadline, int mode) throws InvalidCommandException, IOException {
        super(taskName);
        this.deadline = deadline;
        if (this.deadline == null) throw new NullDateException();
    }

    @Override
    public String toString() {
        String doneStatus = "[D][ ] ";
        if (this.status()) doneStatus = "[D][X] ";
        return doneStatus + this.getTaskName() + " (by: " + deadline +")";
    }
}

class DeadLine {
    LocalDateTime deadline;

    public DeadLine() {

    }

}