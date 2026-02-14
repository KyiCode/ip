package meow;

import exceptions.InvalidCommandException;
import exceptions.InvalidCommandFormatException;
import exceptions.InvalidMarkingException;
import fileoperator.FileOperator;
import fileoperator.Storage;
import helper.StringHelper;
import task.DeadLine;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Parser Class acts as a processing unit for user input commands, delegating them to respective handlers.
 */
public class Parser {
    String intro = "Hello! I'm Meow\n" + "What can I do for you?\n";
    String outro = "Bye. Hope to see you again soon!\n";
    String byeCommand = "bye";
    String listCommand = "list";

//    Temporary
    Path filePath;

    /**
     * Constructs a Parser instance, and Prints the Introduction text of MrMeow.
     */
    public Parser() {
        System.out.println(intro);
    }

    /**
     * Processes the String Input and propagate the command.
     *
     * @param input String input of user.
     * @param filePath Filepath of file for storage.
     * @return String The String representation of output.
     * @throws InvalidCommandException If command is of not proper format.
     * @throws InvalidMarkingException If command is on empty task.
     * @throws IOException If File is not Found or File Error.
     */
    public String thinking(String input, Path filePath) throws InvalidCommandException,
            InvalidMarkingException, IOException {
        StringHelper stringHelper = new StringHelper(input);
        String command = stringHelper.getCommand();
        Task task;

//        temporary var, change to constructor later
        this.filePath = filePath;

        if (input.trim().isEmpty()) {
            return "";
        }

        if (command.equals(byeCommand)) {
            return outro;
        }

        if (command.equals(listCommand)) {
            return TaskList.getTaskList();
            //FileOperator.iterateList(filePath);
        }

        if (!stringHelper.isValidCommandFormat()) {
            throw new InvalidCommandFormatException();
        }

        switch (command) {
        case "bye":
            return outro;
        case "list":
            return TaskList.getTaskList();
        case "mark":
            task = TaskList.markDone(stringHelper.getIndex());
            FileOperator.markOperation(filePath, task);
            return "Done: " + task.toString();
        case "unmark":
            task = TaskList.markUndone(stringHelper.getIndex());
            FileOperator.markOperation(filePath, task);
            return "Unmarked: " + task.toString();
        case "delete":
            task = TaskList.delete(stringHelper.getIndex());
            FileOperator.delOperation(stringHelper.getIndex());
            return "Removed: " + task.toString();
        case "find":
            return TaskList.find(stringHelper.getTaskDetails());
        }

        task = createEvent(command, stringHelper);

        TaskList.add(task);
        FileOperator.append(filePath, task);

        return "Added: " + task.toString() + "\n" + TaskList.getListSize() + " tasks in list.";
    }


    public Task createEvent(String command, StringHelper stringHelper) throws IOException, InvalidCommandException {
        Task task = switch (command) {
            case "todo" -> new ToDo(stringHelper.getTaskDetails());
            case "deadline" -> new DeadLine(stringHelper.getDeadLineDetails());
            case "event" -> new Event(stringHelper.getEventDetails());
            default -> throw new InvalidCommandException();
        };

        assert TaskList.contain(task) : "task not in task list or task not updated";
        assert Storage.inFile(filePath, task) : "task not in storage file";

        return task;
    }
}
