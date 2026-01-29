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

        if (text.equals("bye")) {
            System.out.println(outro);
            return;
        }

        if (text.equals("list")) {
            Task.getTaskList();
            //FileOperator.iterateList(filePath);
            return;
        }

        if (splittedText[0].equals("mark")) {
            Task task = Task.markDone(Integer.parseInt(splittedText[1]));
            FileOperator.markOperation(filePath, task);
            return;
        }

        if (splittedText[0].equals("unmark")) {
            Task task = Task.markUndone(Integer.parseInt(splittedText[1]));
            FileOperator.markOperation(filePath, task);
            return;
        }

        if (splittedText[0].equals("delete")) {
            Task.delete(Integer.parseInt(splittedText[1]));
            return;
        }

        if (splittedText[0].equals("todo")) {
            splittedText = text.split("todo ");
            Task task = new ToDo(splittedText[1]);
            FileOperator.append(filePath, task);
            return;
        }

        if (splittedText[0].equals("deadline")) {
            splittedText = text.split("deadline ");
            Task task = new Deadline(splittedText[1].split(" /by "));
            FileOperator.append(filePath, task);
            return;
        }
        if (splittedText[0].equals("event")) {
            splittedText = text.split("event ");
            splittedText = splittedText[1].split(" /from ");
            String eventName = splittedText[0];
            splittedText = splittedText[1].split(" /to ");
            Task task = new Event(eventName, splittedText[0], splittedText[1]);
            FileOperator.append(filePath, task);
            return;
        }

        throw new InvalidCommandException();

    }
}