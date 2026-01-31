package meow;

import exceptions.InvalidCommandException;
import exceptions.InvalidCommandFormatException;
import exceptions.InvalidMarkingException;
import fileoperator.FileOperator;
import helper.StringHelper;
import task.DeadLine;
import task.Task;
import task.TaskList;
import task.ToDo;
import task.Event;

import java.io.IOException;
import java.nio.file.Path;

public class Parser {
    String intro = "Hello! I'm Meow\n" + "What can I do for you?\n";
    String outro = "Bye. Hope to see you again soon!\n";

    public Parser() {
        System.out.println(intro);
    }

    public String thinking(String input, Path filePath) throws InvalidCommandException, InvalidMarkingException, IOException {
        StringHelper stringHelper = new StringHelper(input);
        String command = stringHelper.getCommand();
        Task task;

        if (input.equals(" ") || input.isEmpty()) {
            return "";
        }

        if (command.equals("bye")) {
            return outro;
        }

        if (command.equals("find")) {

        }

        if (command.equals("list")) {
            TaskList.getTaskList();
            //FileOperator.iterateList(filePath);
            return "";
        }

        if (!stringHelper.isValidCommandFormat()) {
            throw new InvalidCommandFormatException();
        }

        switch (command) {
            case "mark" -> {
                task = TaskList.markDone(stringHelper.getIndex());
                FileOperator.markOperation(filePath, task);
                return "Done: " + task.toString();
            }
            case "unmark" -> {
                task = TaskList.markUndone(stringHelper.getIndex());
                FileOperator.markOperation(filePath, task);
                return "Unmarked: " + task.toString();
            }
            case "delete" -> {
                task = TaskList.delete(stringHelper.getIndex());
                FileOperator.delOperation(stringHelper.getIndex());
                return "Removed: " + task.toString();
            }
        }

        switch (command) {
            case "todo" -> {
                task = new ToDo(stringHelper.getTaskDetails());
            }
            case "deadline" -> {
                task = new DeadLine(stringHelper.getDeadLineDetails());
            }
            case "event" -> {
                task = new Event(stringHelper.getEventDetails());
            }
            default -> throw new InvalidCommandException();
        }

        TaskList.add(task);
        FileOperator.append(filePath, task);

        return "Added: " + task.toString() + "\n" + TaskList.getListSize() + " tasks in list.";
    }
}