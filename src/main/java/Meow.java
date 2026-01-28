import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Meow {
    public static void main(String[] args) throws IOException {
        Brain meowmeow = new Brain();
        Scanner sc = new Scanner(System.in);

        Path dataDir = Paths.get("ip/data");
        Path filePath = Paths.get("ip/data/meow.txt");

        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
            Files.createFile(filePath);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        while (sc.hasNext()) {
            String text = sc.nextLine();
            try {
                meowmeow.thinking(text);
            } catch (InvalidCommandException | InvalidMarkingException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}

class Brain {
    String intro = "Hello! I'm Meow\n" + "What can I do for you?\n";
    String outro = "Bye. Hope to see you again soon!\n";

    public Brain() {
        System.out.println(intro);
    }

    public void thinking(String text) throws InvalidCommandException, InvalidMarkingException, IOException {
        String[] splittedText = text.split(" ");

        if (text.equals("bye")) {
            System.out.println(outro);
            return;
        }

        if (text.equals("list")) {
            Task.getTaskList();
            return;
        }

        if (splittedText[0].equals("mark")) {
            Task.markDone(Integer.parseInt(splittedText[1]));
            return;
        }

        if (splittedText[0].equals("unmark")) {
            Task.markUndone(Integer.parseInt(splittedText[1]));
            return;
        }

        if (splittedText[0].equals("delete")) {
            Task.delete(Integer.parseInt(splittedText[1]));
            return;
        }

        if (splittedText[0].equals("todo")) {
            splittedText = text.split("todo ");
            Task task = new ToDo(splittedText[1]);
            return;
        }

        if (splittedText[0].equals("deadline")) {
            splittedText = text.split("deadline ");
            Task task = new Deadline(splittedText[1].split(" /by "));
            return;
        }
        if (splittedText[0].equals("event")) {
            splittedText = text.split("event ");
            splittedText = splittedText[1].split(" /from ");
            String eventName = splittedText[0];
            splittedText = splittedText[1].split(" /to ");
            Task task = new Event(eventName, splittedText[0], splittedText[1]);
            return;
        }

        throw new InvalidCommandException();

    }
}

class ToDo extends Task {
    public ToDo(String taskName) throws InvalidCommandException, IOException {
        super(taskName);
        System.out.println("Added: " + this.toString());
        System.out.println(Task.getListSize() + " tasks in list.");
    }
}

class Deadline extends Task {
    String deadline = " ";
    public Deadline (String[] taskName) throws InvalidCommandException, IOException {
        super(taskName[0]);
        this.deadline = taskName[1];
        if (this.deadline == null) throw new NullDateException();
        System.out.println("Added: " + this.toString());
        System.out.println(Task.getListSize() + " tasks in list.");
    }

    @Override
    public String toString() {
        String doneStatus = "[D][ ] ";
        if (this.status()) doneStatus = "[D][X] ";
        return doneStatus + this.getTaskName() + " (by: " + deadline +")";
    }
}

class Event extends Task {
    String from;
    String to;
    public Event (String taskName, String from, String to) throws InvalidCommandException, IOException {
        super(taskName);
        this.from = from;
        this.to = to;
        if (this.from == null || this.to == null) throw new NullDateException();

        System.out.println("Added: " + this.toString());
        System.out.println(Task.getListSize() + " tasks in list.");
    }

    @Override
    public String toString() {
        String doneStatus = "[E][ ] ";
        if (this.status()) doneStatus = "[E][X] ";
        return doneStatus + this.getTaskName() + " (from: " + from + " to: " + to + ")";
    }
}

class Task {
    private String taskName;
    private static ArrayList<Task> list = new ArrayList<>();
    private boolean done = false;

    public Task (String taskName) throws NullTaskDescriptionException, IOException {
        this.taskName = taskName;
        if (this.taskName == null) {
            throw new NullTaskDescriptionException();
        }
        list.add(this);
        FileWriter fw = new FileWriter("ip/data/meow.txt", true);
        fw.write(this.toString() + System.lineSeparator());
        fw.close();
    }

    public static void markDone(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.done = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        FileOperator.markOperation(taskIndex, task.toString());
    }

    public static void markUndone(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        task.done = false;
        System.out.println("I've marked this task as not done yet:");
        System.out.println(task.toString());
        FileOperator.markOperation(taskIndex, task.toString());
    }

    public static void delete(int taskIndex) throws InvalidMarkingException, IOException {
        if (taskIndex > list.size()) throw new InvalidMarkingException();
        Task task = list.get(taskIndex - 1);
        list.remove(taskIndex - 1);
        System.out.println("I've eaten this task:");
        System.out.println(task.toString());
        System.out.println(Task.getListSize() + " tasks in list.");
        FileOperator.delOperation(taskIndex);
    }

    public static int getListSize() {
        return list.size();
    }

    public static void getTaskList() {
        int count = 1;
        System.out.println("Tasks in List: ");

        if (list.isEmpty()) System.out.println("Nothing to see here...");

        for (Task x : list) {
            System.out.println(count + ". " + x.toString());
            count++;
        }
    }

    public boolean status() {
        return this.done;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String doneStatus = "[T][ ] ";
        if (this.done) doneStatus = "[T][X] ";
        return doneStatus + this.taskName;
    }
}

class FileOperator {
    public static void markOperation(int index, String text) throws IOException {
        Scanner sc = new Scanner(new FileReader("ip/data/meow.txt"));
        FileWriter fw = new FileWriter("ip/data/temp.txt", true);
        int count = 0;

        while (sc.hasNext()) {
            String stringLine = sc.nextLine();
            if (count + 1 == index) {
                stringLine = text;
            }
            count+= 1;
            fw.write(stringLine + System.lineSeparator());
        }
        sc.close();
        fw.close();

        Files.delete(Paths.get("ip/data/meow.txt"));
        Files.move(Paths.get("ip/data/temp.txt"), Paths.get("ip/data/meow.txt"));
    }

    public static void delOperation(int index) throws IOException {
        Scanner sc = new Scanner(new FileReader("ip/data/meow.txt"));
        FileWriter fw = new FileWriter("ip/data/temp.txt", true);
        int count = 0;

        while (sc.hasNext()) {
            String stringLine = sc.nextLine();
            if (count + 1 == index) {
                count+= 1;
                continue;
            }
            count+= 1;
            fw.write(stringLine + System.lineSeparator());
        }
        sc.close();
        fw.close();

        Files.delete(Paths.get("ip/data/meow.txt"));
        Files.move(Paths.get("ip/data/temp.txt"), Paths.get("ip/data/meow.txt"));
    }

}



class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("invalid command!");
    }

    public InvalidCommandException(String msg) {
        super(msg);
    }
}

class NullTaskDescriptionException extends InvalidCommandException {
    public NullTaskDescriptionException() {
        super("task description is empty!");
    }
}

class NullDateException extends InvalidCommandException {
    public NullDateException() {
        super("Date description should not be empty!");
    }
}

class InvalidMarkingException extends Exception {
    public InvalidMarkingException(){
        super("Task do not exists.");
    }
}
