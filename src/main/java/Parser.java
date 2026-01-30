import java.io.IOException;
import java.nio.file.Path;

class Parser {
    String intro = "Hello! I'm Meow\n" + "What can I do for you?\n";
    String outro = "Bye. Hope to see you again soon!\n";

    public Parser() {
        System.out.println(intro);
    }

    public void thinking(String input, Path filePath) throws InvalidCommandException, InvalidMarkingException, IOException {
        StringHelper stringHelper = new StringHelper(input);
        String command = stringHelper.getCommand();
        Task task;

        if (input.equals(" ") || input.isEmpty()) {
            return;
        }

        if (command.equals("bye")) {
            System.out.println(outro);
            return;
        }

        if (command.equals("list")) {
            TaskList.getTaskList();
            //FileOperator.iterateList(filePath);
            return;
        }

        if (!stringHelper.isValidCommandFormat()) {
            throw new InvalidCommandFormatException();
        }

        switch (command) {
            case "mark" -> {
                task = TaskList.markDone(stringHelper.getIndex());
                FileOperator.markOperation(filePath, task);
                return;
            }
            case "unmark" -> {
                task = TaskList.markUndone(stringHelper.getIndex());
                FileOperator.markOperation(filePath, task);
                return;
            }
            case "delete" -> {
                TaskList.delete(stringHelper.getIndex());
                FileOperator.delOperation(stringHelper.getIndex());
                return;
            }
        }

        if (!stringHelper.hasTaskDescription()) {
            throw new NullTaskDescriptionException();
        }

        switch (command) {
            case "todo" -> {
                task = new ToDo(stringHelper.getTaskDetails());
            }
            case "deadline" -> {
                task = new DeadLineTask(stringHelper.getDeadLineDetails());
            }
            case "event" -> {
                task = new Event(stringHelper.getEventDetails());
            }
            default -> throw new InvalidCommandException();
        }

        TaskList.add(task);
        FileOperator.append(filePath, task);

    }
}