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
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser Class acts as a processing unit for user input commands, delegating them to respective handlers.
 */
public class Parser {
    String intro = "Hello! I'm Meow\n" + "What can I do for you?\n";
    String outro = "Bye. Hope to see you again soon!\n";
    String byeCommand = "bye";
    String listCommand = "list";
    String output;

//    Temporary
    Path filePath;

    /**
     * Constructs a Parser instance, and Prints the Introduction text of MrMeow.
     */
    public Parser(Path filePath) {
        this.filePath = filePath;
        System.out.println(intro);
    }

    /**
     * Processes the String Input and propagate the command.
     *
     * @param input String input of user.
     * @return String The String representation of output.
     * @throws InvalidCommandException If command is of not proper format.
     * @throws InvalidMarkingException If command is on empty task.
     * @throws IOException If File is not Found or File Error.
     */
    public String thinking(String input) throws InvalidCommandException,
            InvalidMarkingException, IOException {
        StringHelper stringHelper = new StringHelper(input);
        String command = stringHelper.getCommand();
        Task task;
        output = "";

        if (input.trim().isEmpty()) {
            return "";
        }

        boolean isSingleInput = executeSingleInput(command, stringHelper);
        boolean isIndexInput = executeIndexCommand(command, stringHelper);
        boolean isEventInput = !isSingleInput && !isIndexInput;

        if (isEventInput) {
            createTask(command, stringHelper);
        }

        String duplicateTaskResult = checkDupe();

        return output + duplicateTaskResult;
    }

    public String checkDupe() throws IOException {
        ArrayList<Task> duplicateTasks = TaskList.getDupes();
        if (!duplicateTasks.isEmpty()) {
            return "\nDuplicate Tasks present, removeDupe to execute removal";
        }
        return "";
    }




    public boolean executeSingleInput(String command, StringHelper stringHelper) throws InvalidCommandFormatException, IOException {
        if (command.equals(byeCommand)) {
            output = outro;
            return true;
        }

        if (command.equals(listCommand)) {
            output = TaskList.getTaskList();
            return true;
        }

        if (command.equals("dupes")) {
            output = TaskList.getConflict();
            return true;
        }

        if (command.equals("removedupe")) {
            ArrayList<Task> duplicateTasks = TaskList.getDupes();
            TaskList.removeDupes(duplicateTasks);
            FileOperator.overWriteLoad(filePath);
            output = "Removed!";
            return true;
        }

        if (!stringHelper.isValidCommandFormat()) {
            throw new InvalidCommandFormatException();
        }

        return false;
    }


    public boolean executeIndexCommand(String command, StringHelper stringHelper) throws InvalidMarkingException, IOException {
        Task task;
        switch (command) {
            case "mark":
                task = TaskList.markDone(stringHelper.getIndex());
                FileOperator.markOperation(filePath, task);
                output = "Done: " + task.toString();
                return true;
            case "unmark":
                task = TaskList.markUndone(stringHelper.getIndex());
                FileOperator.markOperation(filePath, task);
                output = "Unmarked: " + task.toString();
                return true;
            case "delete":
                task = TaskList.delete(stringHelper.getIndex());
                FileOperator.delOperation(stringHelper.getIndex());
                output = "Removed: " + task.toString();
                return true;
            case "find":
                output = TaskList.find(stringHelper.getTaskDetails());
                return true;
        }
        return false;
    }


    public boolean createTask(String command, StringHelper stringHelper) throws IOException, InvalidCommandException {
        Task task = switch (command) {
            case "todo" -> new ToDo(stringHelper.getTaskDetails());
            case "deadline" -> new DeadLine(stringHelper.getDeadLineDetails());
            case "event" -> new Event(stringHelper.getEventDetails());
            default -> throw new InvalidCommandException();
        };

        TaskList.add(task);
        FileOperator.append(filePath, task);

        assert TaskList.contain(task) : "task not in task list or task not updated";
        assert Storage.inFile(filePath, task) : "task not in storage file";

        output = "Added: " + task.toString() + "\n" + TaskList.getListSize() + " tasks in list.";
        return true;
    }
}
