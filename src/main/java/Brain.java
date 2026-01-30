import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class Brain {
    String intro = "Hello! I'm Meow\n" + "What can I do for you?\n";
    String outro = "Bye. Hope to see you again soon!\n";

    public Brain() {
        System.out.println(intro);
    }

    public void thinking(String text, Path filePath) throws InvalidCommandException, InvalidMarkingException, IOException {
        String[] splittedText = text.split(" ");

        if (text.equals(" ") || text.isEmpty()) {
            return;
        }

        if (text.equals("bye")) {
            System.out.println(outro);
            return;
        }

        if (text.equals("list")) {
            TaskList.getTaskList();
            //FileOperator.iterateList(filePath);
            return;
        }

        if (splittedText.length <= 1) {
            throw new InvalidCommandException();
        }


        if (splittedText[0].equals("mark")) {
            Task task = TaskList.markDone(Integer.parseInt(splittedText[1]));
            FileOperator.markOperation(filePath, task);
            return;
        }

        if (splittedText[0].equals("unmark")) {
            Task task = TaskList.markUndone(Integer.parseInt(splittedText[1]));
            FileOperator.markOperation(filePath, task);
            return;
        }

        if (splittedText[0].equals("delete")) {
            TaskList.delete(Integer.parseInt(splittedText[1]));
            return;
        }

        if (splittedText[0].equals("todo")) {
            splittedText = text.split("todo ");
            if (splittedText.length != 2) {
                throw new NullTaskDescriptionException();
            }
            Task task = new ToDo(splittedText[1]);
            FileOperator.append(filePath, task);
            return;
        }

        if (splittedText[0].equals("deadline")) {
            splittedText = text.split("deadline ");
            if (splittedText.length != 2) {
                throw new NullTaskDescriptionException();
            }
            Task task = new DeadLineTask(splittedText[1].split(" /by "));
            FileOperator.append(filePath, task);
            return;
        }

        if (splittedText[0].equals("event")) {
            splittedText = text.split("event ");
            if (splittedText.length != 2) {
                throw new NullTaskDescriptionException();
            }

            Task task = new Event(splittedText[1].split(" /from "));
            FileOperator.append(filePath, task);
            return;
        }

        throw new InvalidCommandException();

    }
}