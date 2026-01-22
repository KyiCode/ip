import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Meow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String intro = "Hello! I'm Meow\n" + "What can I do for you?\n";
        String outro = "Bye. Hope to see you again soon!\n";

        System.out.println(intro);

        while (sc.hasNext()) {
            String text = sc.nextLine();
            String[] splittedText = text.split(" ");

            if (text.equals("bye")) {
                System.out.println(outro);
                break;
            }

            if (text.equals("list")) {
                Task.getTaskList();
                continue;
            }

            if (splittedText[0].equals("mark")) {
                Task.markDone(Integer.parseInt(splittedText[1]));
                continue;
            }

            if (splittedText[0].equals("unmark")) {
                Task.markUndone(Integer.parseInt(splittedText[1]));
                continue;
            }

            if (splittedText[0].equals("todo")) {
                splittedText = text.split("todo ");
                Task task = new ToDo(splittedText[1]);
            }

            if (splittedText[0].equals("deadline")) {
                splittedText = text.split("deadline ");
                Task task = new Deadline(splittedText[1].split(" /by "));
            }
            if (splittedText[0].equals("event")) {
                splittedText = text.split("event ");
                splittedText = splittedText[1].split(" /from ");
                String eventName = splittedText[0];
                splittedText = splittedText[1].split(" /to ");
                Task task = new Event(eventName, splittedText[0], splittedText[1]);
            }

        }
    }
}
class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
        System.out.println("Added: " + this.toString());
        System.out.println(Task.getListSize() + " tasks in list.");
    }
}

class Deadline extends Task {
    String deadline = " ";
    public Deadline (String[] taskName) {
        super(taskName[0]);
        this.deadline = taskName[1];
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
    public Event (String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
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

    public Task (String taskName) {
        this.taskName = taskName;
        list.add(this);
    }

    public static void markDone(int taskIndex) {
        Task task = list.get(taskIndex - 1);
        task.done = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public static void markUndone(int taskIndex) {
        Task task = list.get(taskIndex - 1);
        task.done = false;
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static int getListSize() {
        return list.size();
    }

    public static void getTaskList() {
        int count = 1;
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
