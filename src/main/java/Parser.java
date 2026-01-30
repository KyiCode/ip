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

        if (command.equals("mark")) {
            Task task = TaskList.markDone(stringHelper.getIndex());
            FileOperator.markOperation(filePath, task);
            return;
        }

        if (command.equals("unmark")) {
            Task task = TaskList.markUndone(stringHelper.getIndex());
            FileOperator.markOperation(filePath, task);
            return;
        }

        if (command.equals("delete")) {
            TaskList.delete(stringHelper.getIndex());
            FileOperator.delOperation(stringHelper.getIndex());
            return;
        }

        if (!stringHelper.hasTaskDescription()) {
            throw new NullTaskDescriptionException();
        }

        if (command.equals("todo")) {
            splittedinput = input.split("todo ");

            Task task = new ToDo(splittedinput[1]);
            FileOperator.append(filePath, task);
            return;
        }

        if (command.equals("deadline")) {
            splittedinput = input.split("deadline ");

            Task task = new DeadLineTask(splittedinput[1].split(" /by "));
            FileOperator.append(filePath, task);
            return;
        }

        if (command.equals("event")) {
            splittedinput = input.split("event ");


            Task task = new Event(splittedinput[1].split(" /from "));
            FileOperator.append(filePath, task);
            return;
        }

        throw new InvalidCommandException();

    }
}