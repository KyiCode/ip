import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Meow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String intro =
                "____________________________________________________________\n"
                        + "Hello! I'm Meow\n"
                        + "What can I do for you?\n"
                        + "____________________________________________________________\n";
        String outro =
                "____________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________";

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


            Task task = new Task(text);
        }
    }
}

class Task {
    private String taskName;
    private static ArrayList<Task> list = new ArrayList<>();
    private boolean done = false;

    public Task (String taskName) {
        this.taskName = taskName;
        list.add(this);
        System.out.println("added: " + taskName);
    }

    public static void markDone(int taskIndex) {
        Task task = list.get(taskIndex - 1);
        task.done = true;
        System.out.println("Nice! I've marked this task as done: \n");
        System.out.println(task.toString());
    }

    public static void markUndone(int taskIndex) {
        Task task = list.get(taskIndex - 1);
        task.done = false;
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static void getTaskList() {
        int count = 1;
        for (Task x : list) {
            System.out.println(count + ". " + x.toString());
            count++;
        }
    }

    @Override
    public String toString() {
        String doneStatus = "[ ] ";
        if (this.done) doneStatus = "[X] ";
        return doneStatus + this.taskName;
    }
}
