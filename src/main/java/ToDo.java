import java.io.IOException;

class ToDo extends Task {
    public ToDo(String taskName) throws InvalidCommandException, IOException {
        super(taskName);
    }

    public ToDo(String taskName, int mode) throws InvalidCommandException, IOException {
        super(taskName);
        TaskList.add(this);
    }
}