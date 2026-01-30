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
        
        String[] splittedinput = input.split(" ");
        String command = stringHelper.getCommand();

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
                Task task = TaskList.markDone(stringHelper.getIndex());
                FileOperator.markOperation(filePath, task);
                return;
            }
            case "unmark" -> {
                Task task = TaskList.markUndone(stringHelper.getIndex());
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

        Task task;
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

        FileOperator.append(filePath, task);

    }
}